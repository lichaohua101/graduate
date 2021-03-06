package com.entor.service;

import com.entor.entity.Collegeclass;
import com.entor.entity.User;
import com.entor.entity.UserDetails;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangfm
 * @since 2019-10-21
 */
public interface ICollegeclassService extends IService<Collegeclass> {
	
	//查询有什么学院
	public List<Collegeclass> queryCollege();
	//查询学院有什么班级
	public List<Collegeclass> queryClass(String college);
	//班级的全部学生
	public List<UserDetails> queryAllClassStudents(int id);
	
}
