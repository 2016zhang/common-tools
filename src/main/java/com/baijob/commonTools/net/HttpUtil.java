package com.baijob.commonTools.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baijob.commonTools.IoUtil;
import com.baijob.commonTools.ReUtil;
import com.baijob.commonTools.StrUtil;

/**
 * Http请求工具类
 * 
 * @author xiaoleilu
 */
public class HttpUtil {
	private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

	/** 未知的标识 */
	public final static String UNKNOW = "unknown";

	/**
	 * 编码字符为 application/x-www-form-urlencoded
	 * 
	 * @param content 被编码内容
	 * @return 编码后的字符
	 */
	public static String encode(String content, String charset) {
		if (StrUtil.isBlank(content)) return content;

		String encodeContnt = null;
		try {
			encodeContnt = URLEncoder.encode(content, charset);
		} catch (UnsupportedEncodingException e) {
			log.error("Unsupported encoding: {}", charset);
		}
		return encodeContnt;
	}

	/**
	 * 解码application/x-www-form-urlencoded字符
	 * 
	 * @param content 被编码内容
	 * @return 编码后的字符
	 */
	public static String decode(String content, String charset) {
		if (StrUtil.isBlank(content)) return content;
		String encodeContnt = null;
		try {
			encodeContnt = URLDecoder.decode(content, charset);
		} catch (UnsupportedEncodingException e) {
			log.error("Unsupported encoding: {}", charset);
		}
		return encodeContnt;
	}

	/**
	 * 格式化URL链接
	 * 
	 * @param url 需要格式化的URL
	 * @return 格式化后的URL，如果提供了null或者空串，返回null
	 */
	public static String formatUrl(String url) {
		if (StrUtil.isBlank(url)) return null;
		if (url.startsWith("http://") || url.startsWith("https://")) return url;
		return "http://" + url;
	}

	/**
	 * 获取客户端IP
	 * 
	 * @param request 请求对象
	 * @return IP地址
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (isUnknow(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isUnknow(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isUnknow(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (isUnknow(ip)) {
			ip = request.getRemoteAddr();
		}
		// 多级反向代理检测
		if (ip != null && ip.indexOf(",") > 0) {
			ip = ip.trim().split(",")[0];
		}
		return ip;
	}

	/**
	 * 发送get请求
	 * 
	 * @param urlString 网址
	 * @param customCharset 自定义请求字符集，如果字符集获取不到，使用此字符集
	 * @return 返回内容，如果只检查状态码，正常只返回 ""，不正常返回 null
	 * @throws IOException
	 */
	public static String get(String urlString, String customCharset, boolean isPassCodeError) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		
		if(conn.getResponseCode() != 200) {
			if(! isPassCodeError) {
				throw new IOException("Status code not 200!");
			}
		}
		
		/* 获取内容 */
		String charset = getCharsetFromConn(conn);
		String content = IoUtil.getString(conn.getInputStream(), StrUtil.isBlank(charset) ? customCharset : charset);
		conn.disconnect();

		return content;
	}
	
	/**
	 * 获得远程String
	 * @param url 请求的url
	 * @param customCharset 自定义的字符集
	 * @return
	 * @throws IOException
	 */
	public static String downloadString(String url, String customCharset) throws IOException {
		InputStream inputStream = new URL(url).openStream();
		return IoUtil.getString(inputStream, customCharset);
	}
	
	//----------------------------------------------------------------------------------------- Private method start
	/**
	 * 从Http连接的头信息中获得字符集
	 * 
	 * @param conn HTTP连接对象
	 * @return 字符集
	 */
	private static String getCharsetFromConn(HttpURLConnection conn) {
		String charset = conn.getContentEncoding();
		if (charset == null || "".equals(charset.trim())) {
			String contentType = conn.getContentType();
			charset = ReUtil.get("charset=(.*)", contentType, 1);
		}
		return charset;
	}

	/**
	 * 检测给定字符串是否为未知，多用于检测HTTP请求相关<br/>
	 * 
	 * @param checkString 被检测的字符串
	 * @return 是否未知
	 */
	private static boolean isUnknow(String checkString) {
		return StrUtil.isBlank(checkString) || UNKNOW.equalsIgnoreCase(checkString);
	}
	//----------------------------------------------------------------------------------------- Private method start end
}
