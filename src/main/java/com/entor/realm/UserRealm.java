package com.entor.realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entor.entity.Permission;
import com.entor.entity.Role;
import com.entor.entity.User;
import com.entor.service.IPermissionService;
import com.entor.service.IRoleService;
import com.entor.service.IUserService;

public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired IPermissionService permissionService;
	@Autowired
	private SessionDAO sessionDao;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		// 这里做权限控制
		// 获取用户的登录帐号
		String username = pc.getPrimaryPrincipal().toString();
		// 根据帐号获取用户对象
		User user = userService.queryUserByUsername(username);
		// 根据角色编号获取角色对象
		Role role = roleService.selectById(user.getRid());
		// 根据角色编号查询所有权限对象
		Wrapper<Permission> wrapper = new EntityWrapper<>();
		wrapper.eq(Permission.ROLEID, role.getId());
		List<Permission> plList = permissionService.selectList(wrapper);
		// 把“权限对象”集合转换成“权限名称”字符串集合
		List<String> list = new ArrayList<String>();
		for (Permission p : plList) {
			list.add(p.getName());
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 添加角色名称
		info.addRole(role.getName());
		info.addStringPermissions(list);
		System.out.println("++++++++++++++++"+role);
		return info;
		
//		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 这里做登录控制
		UsernamePasswordToken t = (UsernamePasswordToken)token;
		//传递过来的帐号
		String username = t.getUsername();
		//传递过来的密码
		String password = new String(t.getPassword());
		//根据帐号获取对象
		User user = userService.queryUserByUsername(username);
		//校验
		if (user==null||!user.getPassword().equals(password)) {
			throw new AuthenticationException();
		}
		
		//清除当前用户已存在服务器的登录会话，自允许在一处登陆
		//获取服务器当前所有的会话对象
		//遍历查重复的对象
		Collection<Session> sessions = sessionDao.getActiveSessions();
		for (Session session : sessions) {
			//绘画中的用户名
			String uname =String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) ;
			if (uname.equals(username)) {
				//移除回话
				session.setTimeout(0);
				System.out.println("删除成功");
				break;
			}
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
		return info;
	}
}
