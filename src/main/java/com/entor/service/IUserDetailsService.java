package com.entor.service;

import com.entor.entity.UserDetails;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangfm
 * @since 2019-10-22
 */
public interface IUserDetailsService extends IService<UserDetails> {
	
	/**
	 * 查询一个用户的全部登录信息
	 * @param uid
	 * @return
	 */
	public List<UserDetails> queryAllByUid(int uid);
}
