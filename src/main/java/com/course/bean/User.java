package com.course.bean;

import lombok.Data;
//使用lombok插件的@Data注解可以省略getset方法
@Data
public class User {
	private String userName;
	private String passWord;
	private String name;
	private String age;
	private String sex;
}
