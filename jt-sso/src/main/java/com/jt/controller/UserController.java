package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	
	/**
	 * 说明:
	 * 	由于JSONP跨域请求,所以使用JSONPObject封装数据
	 *	Url地址:http://sso.jt.com/user/check/{param}/{type}
	 *	参数说明:
	 *		1. param 用户校验的参数
	 *		2. type  校验的字段 1 username 2 phone 3 email
	 * 	返回值: SysResult 
	 */
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject checkUser(
				@PathVariable String param,
				@PathVariable Integer type,
				String callback) {
		
		boolean flag = userService.checkUser(param,type);
		return new JSONPObject(callback, SysResult.success(flag));
	}
	
	/**
	 * 利用ticket检索数据
	 * @param ticket
	 * @param callback
	 * @return
	 */
	@RequestMapping("/query/{ticket}")
	public JSONPObject findUserByTicket(@PathVariable String ticket,
			String callback) {
		
		String userJSON = jedisCluster.get(ticket);
		
		return new JSONPObject(callback, SysResult.success(userJSON));
	}
	
	
	
	
	
	
	
	
	
}
