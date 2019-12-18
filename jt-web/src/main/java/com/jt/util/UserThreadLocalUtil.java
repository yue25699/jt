package com.jt.util;

import com.jt.pojo.User;

public class UserThreadLocalUtil {
	
	private static 
	ThreadLocal<User> userThread = 
					new ThreadLocal<>();
	
	public static void set(User user) {
		
		userThread.set(user);
	}
	
	public static User get() {
		
		return userThread.get();
	}
	
	public static void remove() {
		
		//防止内存泄漏
		userThread.remove();
	}
}
