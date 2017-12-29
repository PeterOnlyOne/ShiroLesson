package cn.et.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.entity.Emp;
import cn.et.food.service.EmpService;

@Controller
public class EmpController {

	@Autowired
	EmpService service;
	@ResponseBody
	@RequestMapping("queryEmp")
	public List<Emp> queryEmp(Integer id){
		return service.queryEmps(id);
	}
}
