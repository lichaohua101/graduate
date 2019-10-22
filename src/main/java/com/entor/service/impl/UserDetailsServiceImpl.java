package com.entor.service.impl;

import com.entor.entity.UserDetails;
import com.entor.mapper.UserDetailsMapper;
import com.entor.service.IUserDetailsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangfm
 * @since 2019-10-22
 */
@Service
public class UserDetailsServiceImpl extends ServiceImpl<UserDetailsMapper, UserDetails> implements IUserDetailsService {

	@Autowired
	private UserDetailsMapper userDetailsMapper;
	
	@Override
	public List<UserDetails> queryAllByUid(int uid) {
		return userDetailsMapper.queryAllByUid(uid);
	}
	
}
