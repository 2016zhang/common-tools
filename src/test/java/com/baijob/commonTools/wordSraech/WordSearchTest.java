package com.baijob.commonTools.wordSraech;

import com.baijob.commonTools.wordSearch.Words;

public class WordSearchTest {
	public static void main(String[] args) {
		Words words = new Words();
		words.addWord("正文淘宝", 1);
		words.addWord("正文淘宝代购", 1);
		words.addWord("正文代▅购", 1);
		
		words.addWord("标题敏感词", 2);
		words.addWord("标题代购", 2);
		System.out.println(words);
		
		System.out.println(words.contains("正文淘宝", 1));
		System.out.println(words.contains("标题代购", 2));
		
		System.out.println(words.getFindedFirstWord("dvsdzvdg 色放个克勒斯风格克拉格勒正文淘宝第三方类库三如果；； ；个；", 1));
	}
}
