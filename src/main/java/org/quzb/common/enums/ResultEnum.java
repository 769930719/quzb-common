package org.quzb.common.enums;

public enum ResultEnum {

	/**
	 * 操作成功
	 */
	SUCCESS(0, "成功"),

	/**
	 * 未进行登录
	 */
	UNAUTHORIZED(401, "未登录"),
	/**
	 * 用户无权限
	 */
	NO_AUTHOR(403, "用户无权限"),
	/**
	 * 系统发生500错误
	 */
	SYSTEM_ERROR(500, "系统出现错误");

	private int code;
	private String text;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private ResultEnum(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public static ResultEnum getByCode(int code) {
		ResultEnum[] res = values();
		for (ResultEnum re : res) {
			if (re.getCode() == code) {
				return re;
			}
		}
		return null;
	}
}
