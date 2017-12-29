package cn.et.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.DeptMapper;
import cn.et.food.entity.Dept;
import cn.et.food.entity.DeptExample;
import cn.et.food.entity.TreeNode;
import cn.et.food.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService{
	@Autowired
	DeptMapper dm;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.StudentService#queryStudent(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see cn.et.dept.service.impl.DeptService#queryStudent(java.lang.Integer)
	 */
	public List<TreeNode> queryTreeNodes(Integer pid){
		DeptExample de = new DeptExample();
		de.createCriteria().andPidEqualTo(pid);
		List<Dept> depts = dm.selectByExample(de);
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (Dept dept : depts) {
			TreeNode tn = new TreeNode();
			tn.setId(dept.getId());
			tn.setText(dept.getDname());
			//�жϵ�ǰ�ڵ��Ƿ񻹴����ӽڵ�
			if (queryTreeNodes(dept.getId()).size()==0) {
				tn.setState("open");
			}
			treeNodes.add(tn);
		}
		return treeNodes;
	}
}
