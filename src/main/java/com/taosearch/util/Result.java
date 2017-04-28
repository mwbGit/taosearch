package com.taosearch.util;

public class Result {
	private String msg;
	private boolean flag;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Result(String msg, boolean flag) {
		super();
		this.msg = msg;
		this.flag = flag;
	}
	public Result() {
		super();
	}
	

}
