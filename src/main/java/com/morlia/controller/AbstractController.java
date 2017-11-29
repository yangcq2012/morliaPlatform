package com.morlia.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.morlia.entity.JsonResult;
import com.morlia.exception.UserNotFoundException;

public abstract class AbstractController {
	
	/**
	 * 在其它控制器方法执行出现异常的时候,执行
	 * 异常处理方法
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handlerException(Exception e) {
		e.printStackTrace();
		return new JsonResult(e);
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public Object handleUserNotFound(UserNotFoundException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}

}
