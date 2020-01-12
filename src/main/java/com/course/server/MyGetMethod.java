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
	//两个getCookies没有关系，一个是访问路径一个是方法名
	@RequestMapping(value="/getCookies",method=RequestMethod.GET)
	public String getCookies(HttpServletResponse response) {
		//HttpserverletRequest 装请求信息的类
		//HttpserverletResponse 装响应信息的类
		Cookie cookie = new Cookie("login", "true");
		response.addCookie(cookie);
		return "恭喜你获得cookies信息成功";
	}
	/**
	 * 
	 * 要求客户端携带cookies访问
	 */
	@RequestMapping(value="/getwithcookies",method=RequestMethod.GET)
	public String getWithCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (Objects.isNull(cookies)) {
			return"你必须携带cookies信息来";
		}
		for(Cookie cookie:cookies) {
			if (cookie.getName().equals("login")&&cookie.getValue().equals("true")) {
				return "这是一个需要携带cookies信息才能访问的get请求";
			}
			
		}
		return"你必须携带cookies信息来";
	}
	/**
	 * 开发一个需要携带参数才能当问的get请求
	 * 第一种实现方式url:key=value&key=value
	 * 
	 * 
	 */
	@RequestMapping(value="/getwithparam",method=RequestMethod.GET)
	public Map<String, Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
		Map<String, Integer> mylist=new HashMap<>();
		mylist.put("鞋",400);
		mylist.put("干脆面",1);
		return mylist;
	}
	/**
	 * 第二种需要携带参数访问的get方法
	 * url:ip:port/getwithparam/10/20
	 * 与第一种方式的区别在于请求方式不同
	 */
	@RequestMapping(value="/getwithparam/{start}/{end}")
	public Map myGetList(@PathVariable Integer start,@PathVariable Integer end) {
		Map<String, Integer> mylist=new HashMap<>();
		mylist.put("鞋",400);
		mylist.put("干脆面",1);
		return mylist;
		
	}
}
