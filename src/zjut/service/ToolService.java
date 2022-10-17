package zjut.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zjut.dao.IToolDao;
import zjut.po.ToolEntity;
public class ToolService implements IToolService{

	private IToolDao toolDao;


	public void addTool(ToolEntity tool) {
		toolDao.save(tool);
	}


	public void removeTool(String id) {
		ToolEntity findById = toolDao.findById(id);
		toolDao.delete(findById);
	}


	public void modifyTool(ToolEntity tool) {
		toolDao.update(tool);
	}

	public ToolEntity findById(String id) {
		String hql="from ToolEntity where id='"+id+"'";
		return ((ArrayList<ToolEntity>)toolDao.findByHql(hql)).get(0);
	}


	public ToolEntity loadByID(String id) {
		ToolEntity findById = toolDao.findById(id);
		return findById;
	}


	public List<ToolEntity> loadByStatusAndDept(String deptId, int status) {
		List<ToolEntity> result = toolDao.findByDeptAndStatus(deptId, status);
		return result;
	}


	public List<ToolEntity> loadAll() {
		ArrayList<ToolEntity> allTool = toolDao.getAllTool();
		return allTool;
	}

	public void setToolDao(IToolDao toolDao) {
		this.toolDao = toolDao;
	}
}
