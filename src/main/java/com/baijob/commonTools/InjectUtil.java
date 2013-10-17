package com.baijob.commonTools;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性注入工具类
 * @author xiaoleilu
 *
 */
public class InjectUtil {
	private static Logger log = LoggerFactory.getLogger(InjectUtil.class);
	
	private InjectUtil() {
	}
	
	/**
	 * 注入Request参数<br>
	 * 使用模型名称
	 * @param model 模型
	 * @param request 请求对象
	 * @param isWithModelName 参数是否包含模型名称
	 */
	public static void injectFromRequest(Object model, HttpServletRequest request, boolean isWithModelName) {
		injectFromRequest(model, model.getClass().getSimpleName(), request, isWithModelName);
	}
	
	/**
	 * 注入Request参数
	 * @param model 模型
	 * @param modelName 模型名称
	 * @param request 请求对象
	 * @param isWithModelName 参数是否包含模型名称
	 */
	public static void injectFromRequest(Object model, String modelName, HttpServletRequest request, boolean isWithModelName) {
		Method[] methods = model.getClass().getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (! methodName.startsWith("set")) {
				continue;
			}
			
			Class<?>[] types = method.getParameterTypes();
			if (types.length != 1)	{
				continue;
			}
			
			String fieldName = StrUtil.lowerFirst(methodName.substring(3));
			String paramName = isWithModelName ? (modelName + StrUtil.DOT + fieldName) : fieldName;
			String value = request.getParameter(paramName);
			if (value == null) {
				continue;
			}
			
			try {
				method.invoke(model, ClassUtil.parse(types[0], value));
			} catch (Exception e) {
				log.error("Inject ["+paramName+"] error!", e);
			}
		}
	}
	
	/**
	 * 注入Request参数
	 * @param model 模型
	 * @param modelName 模型名称
	 * @param request 请求对象
	 */
	public static void injectFromMap(Object model, String modelName, Map<?, ?> map) {
		Method[] methods = model.getClass().getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (! methodName.startsWith("set")) {
				continue;
			}
			
			Class<?>[] types = method.getParameterTypes();
			if (types.length != 1)	{
				continue;
			}
			
			String fieldName = StrUtil.lowerFirst(methodName.substring(3));
			Object value = map.get(fieldName);
			if (value == null) {
				continue;
			}
			
			try {
				method.invoke(model, ClassUtil.parse(types[0], value));
			} catch (Exception e) {
				log.error("Inject ["+fieldName+"] error!", e);
			}
		}
	}
}

