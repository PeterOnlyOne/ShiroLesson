package cn.et.food.service;

import java.util.List;

import cn.et.food.entity.Emp;

public interface EmpService {

	public List<Emp> queryEmps(Integer id);
}