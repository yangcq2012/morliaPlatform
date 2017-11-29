package com.morlia.entity;

import java.io.Serializable;

public class JsonResult implements Serializable{
	private static final long serialVersionUID = 2107677679323811268L;
	private static final int ERROR = 0;
	private static final int SUCCESS = 1;
	
	private int state;
	private String message;//错误时的错误信息
	private Object data;//正确时的数据
	
	public JsonResult() {
		
	}
	
	public JsonResult(Object data) {
		this.state = SUCCESS;
		this.message = null;
		this.data = data;
	}
	
	public JsonResult(int state,Throwable e) {
		this.state = state;
		this.message = e.getMessage();
		this.data = null;
	}
	
	public JsonResult(Throwable e) {
		this.state = ERROR;
		this.message = e.getMessage();
		this.data = null;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
