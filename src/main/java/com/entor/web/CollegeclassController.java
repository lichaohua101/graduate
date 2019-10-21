package com.entor.web;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entor.entity.Collegeclass;
import com.entor.service.ICollegeclassService;


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
	//班级
	@RequestMapping("/queryClass")
	public String queryClass(HttpServletRequest request, Map<String, Object>map) {
		String college = request.getParameter("className");
		
		List<Collegeclass> list = collegeclassService.queryCollege();
		for (Collegeclass c : list) {
			System.out.println(c);
		}
		map.put("list",list);
		return "classManagement";
	}
	
	
}
