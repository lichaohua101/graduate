package com.entor.web;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entor.entity.User;
import com.entor.mapper.UserMapper;
import com.entor.service.IUserService;

import cn.hutool.json.JSONObject;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangfm
 * @since 2019-10-14
 */
/*@RestController
@RequestMapping("/user")*/
@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	private static List<User> list;
	
	//注销
	@RequestMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login";
	}
	//用户首页
	@RequestMapping("/index")
	public String index(Map<String, Object>map) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		map.put("username", user.getUsername());
		return "index";
	}
	//登录
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	//登录检查
	@RequestMapping("/loginCheck")
	public String loginCheck(String username,String password,Map<String, Object>map) {
		System.out.println("明文密码"+password);
		//加密，加盐，防止编译的密码相同而被破解
		SimpleHash hash = new SimpleHash("md5", password, "123",2);
		String enpassword = hash.toHex();
		System.out.println("经过md5方式加密并经过2次盐“123”的密文密码"+enpassword);
		UsernamePasswordToken token = new UsernamePasswordToken(username, enpassword);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return "redirect:/index";
		}catch(Exception e) {
			map.put("msg", "账号或者密码错误");
			return "forward:/login";
		}
	}
	//跳转的注册页面
	@RequestMapping("/userRegistration")
	public String userRegistration() {
		return "/UserRegistration";
	}
	@RequestMapping("/AdminRegistration")
	public String AdminRegistration() {
		return "/AdminRegistration";
	}
	//添加用户
	@RequestMapping("/addUser")
	public String addUser(User user) {
		System.out.println("注册用户："+user);
		userService.add(user);
		return "/login";
	}
	//校验用户名是否存在
	@RequestMapping("/checkUsername")
	@ResponseBody
	public int checkUsername(HttpServletRequest request,HttpServletResponse response, Map<String, Object>map) throws IOException {
			String  username  = request.getParameter("username");
			User user = userService.queryUserByUsername(username);
			int msg = 1;
			System.out.println(username);
			if (user==null) {
				msg= 0;
			}
			return msg;
	}
}
 