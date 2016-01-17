package com.z.service;

import com.z.model.User;

public interface UserService extends BaseService<User> {

	/**
	 * 检测username是否存在
	 * @param username
	 * @return 根据username查database, 如果查不到, 返回true, 反之。
	 */
	public boolean checkUsername(String username);

	/**
	 * 根据username和password 检测字段是否存在
	 * @param username
	 * @return 如果查到, 返回true, 反之。
	 */
	public boolean checkUsernameAndPassword(String username, String password);
	
	/**
	 * 根据username和password 检测User是否存在
	 * @param username
	 * @return
	 */	
	public User getUserByUsernameAndPassword(String username, String password);
}
