package zjut.service;

import java.util.List;

import zjut.po.ToolEntity;

public interface IToolService {
	
	void addTool(ToolEntity tool);
	
	void removeTool(String id);
	
	void modifyTool(ToolEntity tool);

	ToolEntity findById(String id);

	ToolEntity loadByID(String id);
	
	List<ToolEntity> loadByStatusAndDept(String deptId, int status);
	
	List<ToolEntity> loadAll();

}
