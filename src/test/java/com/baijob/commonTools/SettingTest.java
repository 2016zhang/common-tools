package com.baijob.commonTools;

public class SettingTest {
	public static void main(String[] args) {
		Setting setting = new Setting("config/example.setting", "utf8", true);
		boolean bool = setting.getBool("bool.key");
		char char1 = setting.getChar("char.key");
		String string = setting.getString("string1");
		String string2 = setting.getString("string2");
		
		System.out.println(bool + ", " + char1 + ", " + string + ", " + string2);
		
		//当配置文件有改动调用此方法
		setting.reload();
	}
}
