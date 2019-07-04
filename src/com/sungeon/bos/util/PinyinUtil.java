package com.sungeon.bos.util;

import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {

	private HanyuPinyinOutputFormat format = null;
	private String[] pinyin;

	public PinyinUtil() {
		format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pinyin = null;
	}

	/**
	 * 转换单个字符
	 * 
	 * @param c
	 * @return
	 */
	public String getCharacterPinYin(char c) {
		try {
			pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		// 如果c不是汉字，toHanyuPinyinStringArray会返回null
		if (pinyin == null)
			return String.valueOf(c);
		// 只取一个发音，如果是多音字，仅取第一个发音
		return pinyin[0];
	}

	/**
	 * 转换一个字符串
	 * 
	 * @param str
	 * @return
	 */
	public String getStringPinYin(String str) {
		StringBuilder sb = new StringBuilder();
		String tempPinyin = null;
		for (int i = 0; i < str.length(); ++i) {
			tempPinyin = getCharacterPinYin(str.charAt(i));
			if (tempPinyin == null) {
				// 如果str.charAt(i)非汉字，则保持原样
				sb.append(str.charAt(i));
			} else {
				sb.append(tempPinyin.charAt(0));
			}
		}
		return sb.toString();
	}

	/**
	 * 拼出QueryName
	 * 
	 * @param jsonStr
	 *            JSON格式的
	 * @return HashMap
	 */
	public HashMap<Integer, Object> getQueryName(String jsonStr) {
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
		JSONArray json = JSONArray.parseArray(jsonStr);
		for (int i = 0; i < json.size(); i++) {
			int id = json.getJSONObject(i).getIntValue("id");
			String name = json.getJSONObject(i).getString("name");
			String code = json.getJSONObject(i).getString("code");
			String s = getStringPinYin(name);
			String string = "";
			for (int j = 0; j < name.length(); j++) {
				string = string + getCharacterPinYin(name.charAt(j));
			}
			string = code + name + s + string;
			map.put(id, string);
		}
		return map;
	}

}
