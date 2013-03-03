package com.baijob.commonTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 * 集合相关工具类，包括数组
 * @author luxiaolei@baijob.com
 *
 */
public class CollectionUtil {
	/**
	 * 以 conjunction 为分隔符将集合转换为字符串
	 * @param <T>
	 * @param collection 集合
	 * @param conjunction 分隔符
	 * @return
	 */
	public static <T> String join(Iterable<T> collection, String conjunction) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (T item : collection) {
			if (isFirst){
				isFirst = false;
			}else{
				sb.append(conjunction);
			}
			sb.append(item);
		}
		return sb.toString();
	}
	
	/**
	 * 以 conjunction 为分隔符将数组转换为字符串
	 * @param <T>
	 * @param array 数组
	 * @param conjunction 分隔符
	 * @return
	 */
	public static <T> String join(T[] array, String conjunction) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (T item : array) {
			if (isFirst){
				isFirst = false;
			}else{
				sb.append(conjunction);
			}
			sb.append(item);
		}
		return sb.toString();
	}
	
	/**
	 * 将Set排序（根据Entry的值）
	 * @param set 被排序的Set
	 * @return 排序后的Set
	 */
	public static List<Entry<Long, Long>> sortEntrySetToList(Set<Entry<Long, Long>> set){
		List<Entry<Long, Long>> list = new LinkedList<Map.Entry<Long,Long>>(set);
		Collections.sort(list, new Comparator<Entry<Long, Long>>() {

			@Override
			public int compare(Entry<Long, Long> o1, Entry<Long, Long> o2) {
				if(o1.getValue() > o2.getValue()) return 1;
				if(o1.getValue() < o2.getValue()) return -1;
				return 0;
			}
		});
		return list;
	}
	
	/**
	 * 切取部分数据
	 * @param <T>
	 * @param surplusAlaDatas 原数据
	 * @param partSize 每部分数据的长度
	 * @return 切取出的数据或null
	 */
	public static <T> List<T> popPart(Stack<T> surplusAlaDatas, int partSize){
		if(surplusAlaDatas == null || surplusAlaDatas.size() <= 0) return null;
		
		List<T> currentAlaDatas = new ArrayList<T>();
		int size = surplusAlaDatas.size();
		//切割
		if(size > partSize){
			for(int i = 0; i < partSize; i++){
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		}else{
			for(int i = 0; i < size; i++){
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		}
		return currentAlaDatas;
	}
	
	/**
	 * 新建一个HashMap
	 * @return
	 */
	public static <T, K> HashMap<T, K> newHashMap(){
		return new HashMap<T, K>();
	}
	
	/**
	 * 新建一个HashSet
	 * @return
	 */
	public static <T> HashSet<T> newHashSet(){
		return new HashSet<T>();
	}
	
	/**
	 * 新建一个ArrayList
	 * @return
	 */
	public static <T> ArrayList<T> newArrayList(){
		return new ArrayList<T>();
	}
}
