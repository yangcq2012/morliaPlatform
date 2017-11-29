package com.morlia.controller;

public class BaseController {
	
	public String ss;
	
	

	public BaseController(String ss) {
		super();
		this.ss = ss;
	}

	public BaseController() {
		super();
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	@Override
	public String toString() {
		return "BaseController [ss=" + ss + "]";
	}
	

}
