package com.entor.web;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entor.entity.Collegeclass;
import com.entor.entity.User;
import com.entor.entity.UserDetails;
import com.entor.service.ICollegeclassService;
import com.entor.service.IUserDetailsService;
import com.entor.service.IUserService;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangfm
 * @since 2019-10-21
 */
@Controller
public class CollegeclassController {
	
	@Autowired
	private ICollegeclassService collegeclassService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserDetailsService userDetailsService ;
	
	
	//学院班级管理(学院)
	@RequestMapping("/queryCollege")
	public String queryCollege(Map<String, Object>map) {
		List<Collegeclass> list = collegeclassService.queryCollege();
		for (Collegeclass c : list) {
			System.out.println(c);
		}
		map.put("list",list);
		return "classManagement";
	}
	//班级queryClass?className=信息学院
	@RequestMapping("/queryClass")
	public String queryClass(HttpServletRequest request, Map<String, Object>map) {
		String className = request.getParameter("className");
		System.out.println(className);
		List<Collegeclass> list = collegeclassService.queryClass(className);
		for (Collegeclass c : list) {
			System.out.println(c);
		}
		map.put("list1",list);
		return "classManagement";
	}
	//班级学生@{/queryClassStudent(classStudents=${(c.className)})}
	@RequestMapping("/queryClassStudent")
	public String queryClassStudent(HttpServletRequest request, Map<String, Object>map) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<UserDetails> list = collegeclassService.queryAllClassStudents(id);
		List<User> luser = new ArrayList<User>();
		for (UserDetails ud : list) {
			luser.add(userService.queryById(ud.getUid()));
		}
		for (User u : luser) {
			List<UserDetails> li = new ArrayList<>(); 
			li = userDetailsService.queryAllByUid(u.getId());
			u.setlUsers(li.get(li.size()-1));
		}
//		map.put("list2",list);
		map.put("luser",luser);
		for (User user : luser) {
			System.out.println(user);
		}
		return "classManagement";
	}
	
	
}
