package com.entor.service;

import com.entor.entity.User;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangfm
 * @since 2019-10-14
 */
public interface IUserService{
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
	/**
	 * 查询所有用户
	 */
	public List<User>queryAll();
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUserById(int id);
	/**
	 * 查询用户
	 * @param id
	 * @return
	 */
	public User queryById(int id);
	/**
	 * 修改用户
	 * @param user
	 */
	public void update(User user);
}
