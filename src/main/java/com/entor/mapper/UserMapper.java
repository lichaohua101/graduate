package com.entor.mapper;

import com.entor.entity.User;

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
 * @since 2019-10-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
	/**
	 * 自定义方法查询所有用户
	 * @return
	 */
	@Select("select * from user order by id")
	public List<User> queryAll();
}