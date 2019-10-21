package com.entor.mapper;

import com.entor.entity.Collegeclass;

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
 * @since 2019-10-21
 */
@Mapper
public interface CollegeclassMapper extends BaseMapper<Collegeclass> {
	/**
	 * 有什么学院
	 * @return
	 */
	@Select("select * FROM collegeclass GROUP BY college ORDER BY id")
	public List<Collegeclass> queryCollege();
	
	/**
	 * 学院里有什么班级
	 * @param college
	 * @return
	 */
	@Select("SELECT * FROM collegeclass where  college = ${college}")
	public List<Collegeclass> queryClass(String college);
}