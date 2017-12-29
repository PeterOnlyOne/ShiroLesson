package cn.et.food.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.StudentMapper;
import cn.et.food.entity.Student;
import cn.et.food.entity.StudentExample;
import cn.et.food.service.StudentService;
import cn.et.food.utils.PageTools;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentMapper sm;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.StudentService#queryStudent(java.lang.String)
	 */
	public PageTools queryStudent(String sname,Integer page,Integer rows){
		if(sname==null){
			sname="";
		}
		//����sql����ѯ�ܼ�¼��
		StudentExample se = new StudentExample();
		se.createCriteria().andSnameLike("%"+sname+"%");
		int total=queryStudentCount(se);
		// limit ��ʼλ��,�ܼ�¼��
		PageTools pts=new PageTools(page, total, rows);
		RowBounds rb = new RowBounds(pts.getStartIndex()-1, rows);
		List<Student> students = sm.selectByExampleWithRowbounds(se, rb);
		pts.setRows(students);
		return pts;
	}
	
	public int queryStudentCount(StudentExample se){
		int total=(int) sm.countByExample(se);
		return total;
	}
	
	public void deleteStudent(Integer sid){
		sm.deleteByPrimaryKey(sid);
	}
	
	public void updateStudent(Student student){
		sm.updateByPrimaryKey(student);
	}
	
	public void saveStudent(Student student){
		sm.insert(student);
	}
}
