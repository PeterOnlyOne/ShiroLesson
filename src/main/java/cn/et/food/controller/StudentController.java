package cn.et.food.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.et.food.entity.Result;
import cn.et.food.entity.Student;
import cn.et.food.service.StudentService;
import cn.et.food.utils.PageTools;

@Controller
public class StudentController {
	@Autowired
	StudentService service;
	/**
	 * ��ѯѧ��
	 * @param sname
	 * @param page 
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryStudent")
	public PageTools queryStudent(String sname,Integer page,Integer rows){
		return service.queryStudent(sname,page,rows);
	}
	
	@ResponseBody
	@RequestMapping(value="/student/{sid}",method=RequestMethod.DELETE)
	public Result deleteStudent(@PathVariable Integer sid,Integer page,Integer rows){
		Result r=new Result();
		r.setCode(1);
		try {
			service.deleteStudent(sid);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
	
	@ResponseBody
	@RequestMapping(value="/student/{sid}",method=RequestMethod.PUT)
	public Result updateStudent(@PathVariable Integer sid,Integer page,Integer rows,Student student){
		student.setSid(sid);
		Result r=new Result();
		r.setCode(1);
		try {
			service.updateStudent(student);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
	
	@ResponseBody
	@RequestMapping(value="/student",method=RequestMethod.POST)
	public Result insertStudent(Student student,MultipartFile photo){
		Result r=new Result();
		r.setCode(1);
		try {
			String fileName=photo.getOriginalFilename();
			File destFile=new File("E:\\myImage\\"+fileName);
			photo.transferTo(destFile);
			
			service.saveStudent(student);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
}
