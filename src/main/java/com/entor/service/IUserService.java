package com.entor.service;

import com.entor.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangfm
 * @since 2019-10-14
 */
public interface IUserService extends IService<User> {
	/**
	 * 根据用户名查找对象
	 * @param username
	 * @return
	 */
	public User queryUserByUsername(String username);
	/**
	 * 用户注册
	 * @param user
	 */
	public void add(User user);
}
