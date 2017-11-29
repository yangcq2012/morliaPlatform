package com.morlia.interceptor;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.morlia.controller.BaseController;

public class SessionInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("token");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		System.out.println(handlerMethod.getBean().getClass());
		Field[] fields = handlerMethod.getBean().getClass().getDeclaredFields();
		Field ss = handlerMethod.getBean().getClass().getDeclaredField("ss");
		ss.setAccessible(true);
		ss.set(handlerMethod.getBean(), "123");
		/*if(username==null) {
			//response.sendRedirect("../index.html");
			return false;
		}*/		
		return true;
	}

}
