package com.baijob.commonTools;

import org.junit.Test;

public class ZhUtilTest {
	@Test
	public void zhUtilTest(){
		//全角To半角
		String str1 = ZhUtil.toDBC("处理的符号：，！    不处理的符号『』【】");
		System.out.println(str1);
		
		//半角To全角
		String str2 = ZhUtil.toSBC("处理的符号,.");
		System.out.println(str2);
		
		String s_str1 = "简体到繁体中文";
		ZhUtil.initS2T();
		String t_str1 = ZhUtil.toTraditional(s_str1);
		System.out.println(t_str1);
		
		String t_str2 = "簡體中文到繁體中文轉換這件事沒有捷徑，只能硬來。";
		ZhUtil.initS2T();
		String s_str2 = ZhUtil.toSimplified(t_str2);
		System.out.println(s_str2);
		
		//调用我是为了释放资源，要是不调那一堆替换的map可就一直自内存里呆着了，我可不管。
		ZhUtil.clean();
	}
}
