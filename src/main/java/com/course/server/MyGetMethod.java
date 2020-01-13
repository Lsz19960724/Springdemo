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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="/",description="这是我全部的get方法")
public class MyGetMethod {
	//����getCookiesû�й�ϵ��һ���Ƿ���·��һ���Ƿ�����
	@RequestMapping(value="/getCookies",method=RequestMethod.GET)
	@ApiOperation(value="通过这个方法可以获取到cookies")
	public String getCookies(HttpServletResponse response) {
		//HttpserverletRequest 装请求信息的类
		//HttpserverletResponse 装响应信息的类
		Cookie cookie = new Cookie("login", "true");
		response.addCookie(cookie);
		return "恭喜你获得cookies信息";
	}
	/**
	 * 
	 *这是一个需要携带cookies信息才能访问的get请求
	 */
	@RequestMapping(value="/getwithcookies",method=RequestMethod.GET)
	@ApiOperation(value="这是一个需要携带cookies信息才能访问的get请求")
	public String getWithCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (Objects.isNull(cookies)) {
			return"你必须携带cookies信息";
		}
		for(Cookie cookie:cookies) {
			if (cookie.getName().equals("login")&&cookie.getValue().equals("true")) {
				return "这是一个需要携带cookies信息才能访问的get请求";
			}
			
		}
		return"恭喜你访问get请求成功";
	}
	/**
	 * 开发一个需要携带参数才能访问的get请求
	 * 第一种实现方法url:key=value&key=value
	 * 
	 * 
	 */
	@RequestMapping(value="/getwithparam",method=RequestMethod.GET)
	@ApiOperation(value="需要携带参数才能访问的get请求方法1")
	public Map<String, Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
		Map<String, Integer> mylist=new HashMap<>();
		mylist.put("鞋",400);
		mylist.put("面",1);
		return mylist;
	}
	/**
	 * 第二种需要携带参数访问你的get请求
	 * url:ip:port/getwithparam/10/20
	 * 
	 */
	@RequestMapping(value="/getwithparam/{start}/{end}")
	@ApiOperation(value="需要携带参数才能访问的get请求方法2")
	public Map myGetList(@PathVariable Integer start,@PathVariable Integer end) {
		Map<String, Integer> mylist=new HashMap<>();
		mylist.put("鞋",400);
		mylist.put("面",1);
		return mylist;
		
	}
}
