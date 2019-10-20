package com.entor.web;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.entity.User;
import com.entor.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


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
	private Subject subject ;
	
	//注销(admin,user)
	@RequestMapping("/logout")
	public String logout() {
		subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login";
	}
	//登录(admin,user)
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	//登录检查(admin,user)
	@RequestMapping("/loginCheck")
	public String loginCheck(String username,String password,Map<String, Object>map) {
		System.out.println("明文密码"+password);
		//加密，加盐，防止编译的密码相同而被破解
		SimpleHash hash = new SimpleHash("md5", password, "123",2);
		String enpassword = hash.toHex();
		System.out.println("经过md5方式加密并经过2次盐“123”的密文密码"+enpassword);
		UsernamePasswordToken token = new UsernamePasswordToken(username, enpassword);
		subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return "redirect:/userQueryByPage";
		}catch(Exception e) {
			map.put("msg", "账号或者密码错误");
			return "forward:/login";
		}
	}
	//跳转的注册页面(user)
	@RequestMapping("/userRegistration")
	public String userRegistration() {
		return "/UserRegistration";
	}
	//跳转的注册页面(admin)
	@RequestMapping("/AdminRegistration")
	public String AdminRegistration() {
		return "/AdminRegistration";
	}
	//添加用户(admin,user)
	@RequestMapping("/addUser")
	public String addUser(User user) {
		subject = SecurityUtils.getSubject();
		User user2 = (User)subject.getPrincipal();
		SimpleHash hash = new SimpleHash("md5", user.getPassword(), "123",2);
		String enpassword = hash.toHex();
		user.setPassword(enpassword);
		userService.add(user);
		System.out.println("注册成功："+user);
		if (user2==null) {
			return "login";
		}else {
			return "redirect:/userQueryByPage";
		}
	}
	//校验用户名是否存在(admin,user)
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
	//用户分页（admin）
	@RequestMapping("/userQueryByPage")
	public String userQueryByPage(Model model) {
		subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		
		List<User> list = userService.queryAll();
		for (User u : list) { 
			System.out.println(u);
		}
		model.addAttribute("username", user.getUsername());
		model.addAttribute("list", list);
		return "/adminUser";
	}
	/*public PageInfo<User> userQueryByPage(@PathVariable int pageNum,@PathVariable int pageSize){
		PageHelper.startPage(pageNum,pageSize);
		//查询数据出来，立即用PageInfo<User> 进行分配，不然会有未知错误
		List<User> list = userService.queryAll();
		PageInfo<User> lInfo = new PageInfo<>(list);
		return lInfo;
	}*/
	
	//删除用户（user）adminAddUser  updateUserById
//	@RequiresPermissions("sys_delete")
	@RequestMapping("/deleteUserById")
	public String deleteUserById(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		userService.deleteUserById(id);
		System.out.println("删除的Id"+id);
		return "redirect:/userQueryByPage";
	}
	//跳转修改用户界面
//	@RequiresPermissions("queryById")
	@RequestMapping("/queryUserById")
	public String queryUserById(HttpServletRequest request,Map<String, Object>map) {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userService.queryById(id);
		System.out.println("查找用户"+user);
		map.put("user", user);
		return "updateUser";
	}
	//修改用户
//	@RequiresPermissions("sys_update")
	@RequestMapping("/updateUserById")
	public String updateUserById(User user) {
		SimpleHash hash = new SimpleHash("md5", user.getPassword(), "123",2);
		user.setPassword(hash.toHex());
		userService.update(user);
		System.out.println("修改为"+user);
		return "redirect:/userQueryByPage";
	}
}
 