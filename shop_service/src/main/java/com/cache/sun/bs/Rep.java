package com.cache.sun.bs;

import java.io.Serializable;

/**
 * 响应数据，code为200表示成功，默认500或其他值表示失败。
 */
public class Rep<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认成功代码。
	 */
	public static final String CODE_SUC = "200";
	/**
	 * 默认错误代码。
	 */
	public static final String CODE_ERR = "500";

	/**
	 * 响应编码：200表示成功，其他值表示失败。
	 */
	private String code = "200";
	/**
	 * 响应消息内容。
	 */
	private String message = "success";
	/**
	 * 响应数据。
	 */
	private T data;

	public Rep<T> ok() {
		return this;
	}

	public Rep<T> ok(T data) {
		this.setData(data);
		return this;
	}

	public Rep<T> error() {
		this.code = CODE_ERR;
		this.message = "系统出错，请联系管理员！";
		return this;
	}

	public Rep<T> error(String code) {
		this.code = code;
		this.message = "系统出错，请联系管理员！";
		return this;
	}

	public Rep<T> error(String code, String message) {
		this.code = code;
		this.message = message;
		return this;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
