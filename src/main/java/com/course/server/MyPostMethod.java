package com.course.server;

import java.net.CookieStore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.bean.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/", description = "这是我全部的post请求")
@RequestMapping("/v1")
public class MyPostMethod {
	// 用来装cookies信息的变量
	// 模拟用户登录成功后获取cookies，然后在访问其他接口获取列表

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "登录接口，成功后获取cookies信息", httpMethod = "POST")
	// value="userName",required=true 前端对应的属性名和是否必须传入这个参数（true代表必须传）
	public String login(HttpServletResponse response,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "passWord", required = true) String passWord) {
		if (userName.equals("weimiao") && passWord.equals("nanzhao")) {
			Cookie cookie = new Cookie("login", "true");
			response.addCookie(cookie);
			return "登录成功";

		}
		return "用户名或者密码不正确";
	}

	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	@ApiOperation(value = "获取用户列表", httpMethod = "POST")
	public String getUserList(HttpServletRequest request, @RequestBody User u) {
		User user;
		// 获取cookies
		Cookie[] cookies = request.getCookies();
		// 验证cookies是否合法
		for (Cookie c : cookies) {
			if (c.getName().equals("login") && c.getValue().equals("true") && u.getUserName().equals( "weimiao")
					&& u.getPassWord().equals("nanzhao")) {
				user = new User();
				user.setName("chengbaishuang");
				user.setAge("18");
				user.setSex("man");
				return user.toString();
			}
		}
		return "参数不合法";

	}

}
