package com.entor.service.impl;

import com.entor.entity.Collegeclass;
import com.entor.mapper.CollegeclassMapper;
import com.entor.service.ICollegeclassService;
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
 * @since 2019-10-21
 */
@Service
public class CollegeclassServiceImpl extends ServiceImpl<CollegeclassMapper, Collegeclass> implements ICollegeclassService {
	
	@Autowired
	private CollegeclassMapper collegeclassMapper;
	
	@Override
	public List<Collegeclass> queryCollege() {
		return collegeclassMapper.queryCollege();
	}

	@Override
	public List<Collegeclass> queryClass(String college) {
		return collegeclassMapper.queryClass(college);
	}
	
}
