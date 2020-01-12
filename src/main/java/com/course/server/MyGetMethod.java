package com.course.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyGetMethod {
	//����getCookiesû�й�ϵ��һ���Ƿ���·��һ���Ƿ�����
	@RequestMapping(value="/getCookies",method=RequestMethod.GET)
	public String getCookies(HttpServletResponse response) {
		//HttpserverletRequest װ������Ϣ����
		//HttpserverletResponse װ��Ӧ��Ϣ����
		Cookie cookie = new Cookie("login", "true");
		response.addCookie(cookie);
		return "��ϲ����cookies��Ϣ�ɹ�";
	}
	/**
	 * 
	 * Ҫ��ͻ���Я��cookies����
	 */
	@RequestMapping(value="/getwithcookies",method=RequestMethod.GET)
	public String getWithCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (Objects.isNull(cookies)) {
			return"�����Я��cookies��Ϣ��";
		}
		for(Cookie cookie:cookies) {
			if (cookie.getName().equals("login")&&cookie.getValue().equals("true")) {
				return "����һ����ҪЯ��cookies��Ϣ���ܷ��ʵ�get����";
			}
			
		}
		return"�����Я��cookies��Ϣ��";
	}
	/**
	 * ����һ����ҪЯ���������ܵ��ʵ�get����
	 * ��һ��ʵ�ַ�ʽurl:key=value&key=value
	 * 
	 * 
	 */
	@RequestMapping(value="/getwithparam",method=RequestMethod.GET)
	public Map<String, Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
		Map<String, Integer> mylist=new HashMap<>();
		mylist.put("Ь",400);
		mylist.put("�ɴ���",1);
		return mylist;
	}
	/**
	 * �ڶ�����ҪЯ���������ʵ�get����
	 * url:ip:port/getwithparam/10/20
	 * ���һ�ַ�ʽ��������������ʽ��ͬ
	 */
	@RequestMapping(value="/getwithparam/{start}/{end}")
	public Map myGetList(@PathVariable Integer start,@PathVariable Integer end) {
		Map<String, Integer> mylist=new HashMap<>();
		mylist.put("Ь",400);
		mylist.put("�ɴ���",1);
		return mylist;
		
	}
}
