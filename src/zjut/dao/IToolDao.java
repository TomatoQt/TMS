package zjut.dao;

import zjut.po.ToolEntity;

import java.util.ArrayList;
import java.util.List;

public interface IToolDao {
    public void save(ToolEntity transientInstance);
    public void delete(ToolEntity transientInstance);
    public void update(ToolEntity transientInstance);
    public List findByHql(String hql);
    public ToolEntity findById(String id);
    public List<ToolEntity> findByDeptAndStatus(String deptId, int status);
    public ArrayList<ToolEntity> getAllTool();
}
