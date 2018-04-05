package org.quzb.common.vo;

import org.quzb.common.enums.ResultEnum;

/**
 * 返回前端的结果
 * 
 * @author admin
 */
public class ResultVo {

	private int code;//代码
	private String msg;//消息
	private Object data;//返回数据

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	private ResultVo(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	private ResultVo(ResultEnum result) {
		this(result.getCode(), result.getText(), null);
	}

	public static ResultVo ok() {
		return new ResultVo(ResultEnum.SUCCESS);
	}

	public static ResultVo ok(Object data) {
		ResultVo result = ok();
		result.data = data;
		return result;
	}

	public static ResultVo build(int code, String msg, Object data) {
		return new ResultVo(code, msg, data);
	}

	public static ResultVo build(int code, String msg) {
		return build(code, msg, null);
	}

	public static ResultVo build(ResultEnum result) {
		return new ResultVo(result);
	}

	public boolean isOk() {
		return this.getCode() == ResultEnum.SUCCESS.getCode();
	}
}
