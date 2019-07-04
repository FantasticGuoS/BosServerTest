package com.sungeon.bos.util;

import java.util.Arrays;
import java.util.Random;

/**
 * 随机生成验证码
 * 
 * @author GuoS
 * 
 */
public class RandomUtil {
	/**
	 * 验证码难度级别，H1只包含数字，H2包含数字和小写英文，H3包含数字和大小写英文
	 */
	public enum SecurityCodeLevel {
		H1, // 仅数字
		H2, // 数字+大写字母
		H3, // 数字+大写字母+小写字母
		H4 // 数字+大写字母+小写字母+常用字符
	};

	// 字符集合
	private static char[] CHAR_CODE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
			'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', '-', '_', '=', '+', '/', '!', '@', '#', '$', '%', '^', '&', '*' };

	/**
	 * 产生默认验证码，4位普通难度<br>
	 * 调用此方法和调用getSecurityCode(4, SecurityCodeLevel.H1, false)有一样的行为
	 * 
	 * @see #getSecurityCode(int, SecurityCodeLevel, boolean)
	 * @return 验证码
	 */
	public static String getSecurityCode4S() {
		return getSecurityCode(4, SecurityCodeLevel.H1, false);
	}

	/**
	 * 产生默认验证码，4位中等难度<br>
	 * 调用此方法和调用getSecurityCode(4, SecurityCodeLevel.H2, false)有一样的行为
	 * 
	 * @see #getSecurityCode(int, SecurityCodeLevel, boolean)
	 * @return 验证码
	 */
	public static String getSecurityCode4M() {
		return getSecurityCode(4, SecurityCodeLevel.H2, false);
	}

	/**
	 * 产生默认验证码，6位普通难度<br>
	 * 调用此方法和调用getSecurityCode(6, SecurityCodeLevel.H1, false)有一样的行为
	 * 
	 * @see #getSecurityCode(int, SecurityCodeLevel, boolean)
	 * @return 验证码
	 */
	public static String getSecurityCode6S() {
		return getSecurityCode(6, SecurityCodeLevel.H1, false);
	}

	/**
	 * 产生默认验证码，6位中等难度<br>
	 * 调用此方法和调用getSecurityCode(6, SecurityCodeLevel.H2, false)有一样的行为
	 * 
	 * @see #getSecurityCode(int, SecurityCodeLevel, boolean)
	 * @return 验证码
	 */
	public static String getSecurityCode6M() {
		return getSecurityCode(6, SecurityCodeLevel.H2, false);
	}

	/**
	 * 获取验证码，指定长度、难度、是否允许重复字符
	 * 
	 * @param length
	 *            长度
	 * @param level
	 *            难度(H1：仅数字，H2：数字+大写字母，H3：数字+大写字母+小写字母)
	 * @param isCanRepeat
	 *            是否允许重复字符
	 * @return 验证码
	 */
	public static String getSecurityCode(int length, SecurityCodeLevel level, boolean isCanRepeat) {
		// 随机抽取len个字符
		int len = length;
		char[] code;

		// 根据不同的难度截取字符数组
		switch (level) {
		case H1: {
			code = Arrays.copyOfRange(CHAR_CODE, 0, 10);
			break;
		}
		case H2: {
			code = Arrays.copyOfRange(CHAR_CODE, 0, 36);
			break;
		}
		case H3: {
			code = Arrays.copyOfRange(CHAR_CODE, 0, 61);
			break;
		}
		case H4: {
			code = Arrays.copyOfRange(CHAR_CODE, 0, CHAR_CODE.length);
			break;
		}
		default: {
			code = Arrays.copyOfRange(CHAR_CODE, 0, CHAR_CODE.length);
		}
		}

		// 字符集合长度
		int n = code.length;

		// 抛出运行时异常
		if (len > n && isCanRepeat == false) {
			throw new RuntimeException(String.format(
					"调用SecurityCode.getSecurityCode(%1$s,%2$s,%3$s)出现异常，" + "当isCanRepeat为%3$s时，传入参数%1$s不能大于%4$s", len,
					level, isCanRepeat, n));
		}
		// 存放抽取出来的字符
		char[] result = new char[len];
		// 判断能否出现重复的字符
		if (isCanRepeat) {
			for (int i = 0; i < result.length; i++) {
				// 索引 0 and n-1
				int r = (int) (Math.random() * n);

				// 将result中的第i个元素设置为codes[r]存放的数值
				result[i] = code[r];
			}
		} else {
			for (int i = 0; i < result.length; i++) {
				// 索引 0 and n-1
				int r = (int) (Math.random() * n);

				// 将result中的第i个元素设置为codes[r]存放的数值
				result[i] = code[r];

				// 必须确保不会再次抽取到那个字符，因为所有抽取的字符必须不相同。
				// 因此，这里用数组中的最后一个字符改写codes[r]，并将n减1
				code[r] = code[n - 1];
				n--;
			}
		}
		return String.valueOf(result);
	}

	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println("AppId：" + getSecurityCode(16, SecurityCodeLevel.H3, false));
		System.out.println("AppSecret：" + getSecurityCode(32, SecurityCodeLevel.H4, false));
	}

}