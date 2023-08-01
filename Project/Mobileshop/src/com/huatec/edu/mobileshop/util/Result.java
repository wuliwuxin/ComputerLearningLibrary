package com.huatec.edu.mobileshop.util;

import java.io.Serializable;

public class Result implements Serializable {
	private int status;//状态,成功：0，失败：1
	private String msg;//消息
	private Object data;//数据
	//get、set方法
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	//toString方法
	public String toString() {
		return "Result [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
}
