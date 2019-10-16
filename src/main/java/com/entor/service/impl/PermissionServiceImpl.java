package com.entor.service.impl;

import com.entor.entity.Permission;
import com.entor.mapper.PermissionMapper;
import com.entor.service.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangfm
 * @since 2019-10-14
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
	
}
