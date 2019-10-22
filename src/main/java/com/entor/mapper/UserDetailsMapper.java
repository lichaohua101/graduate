package com.entor.mapper;

import com.entor.entity.UserDetails;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wangfm
 * @since 2019-10-22
 */
@Mapper
public interface UserDetailsMapper extends BaseMapper<UserDetails> {
	
	@Select("SELECT ud.* FROM `user` u,user_details ud WHERE u.id=ud.Uid AND u.id=#{uid} ORDER BY ud.id")
	public List<UserDetails> queryAllByUid(int uid);
}