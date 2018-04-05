package org.quzb.common.utils;

import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

/**
 * 工具类
 * 
 * @author admin
 */
public class ToolUtils {

	/**
	 * 英文字母、数字、下划线正则表达式
	 */
	private static final String EN_NUM_REG = "^[a-zA-Z]\\w+$";

	/**
	 * 是否以字母为首，且只允许输入字母、数字、下划线
	 * 
	 * @param chars
	 * @return
	 */
	public static boolean isCharOrNum(String chars) {
		if (StringUtils.isBlank(chars)) {
			return false;
		}
		return Pattern.matches(EN_NUM_REG, chars);
	}

	/**
	 * 通过文件名获取文件后缀，包含最后的那个点
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return null;
		} else if (StringUtils.lastIndexOf(fileName, ".") < 0) {
			return null;
		} else {
			return StringUtils.substring(fileName, StringUtils.lastIndexOf(fileName, "."));
		}
	}

	/**
	 * 生成唯一字符
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> String builder(Class<T> clazz) {
		if (clazz == null)
			return null;
		return md5Hex(clazz.getName() + UUID.randomUUID().toString().replaceAll("-", "")).substring(8, 24);
	}

	/**
	 * 加盐MD5
	 * 
	 * @author daniel
	 * @time 2016-6-11 下午8:45:04
	 * @param password
	 * @return
	 */
	public static String encodePass(String password) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		String salt = sb.toString();
		password = md5Hex(password + salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = password.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
		}
		return new String(cs);
	}

	/**
	 * 校验加盐后是否和原文一致
	 * 
	 * @param password
	 * @param md5Password
	 * @return
	 */
	public static boolean verifyPass(String password, String md5Password) {
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5Password.charAt(i);
			cs1[i / 3 * 2 + 1] = md5Password.charAt(i + 2);
			cs2[i / 3] = md5Password.charAt(i + 1);
		}
		String salt = new String(cs2);
		return md5Hex(password + salt).equals(new String(cs1));
	}

	/**
	 * 获取十六进制字符串形式的MD5摘要
	 */
	private static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}
}
