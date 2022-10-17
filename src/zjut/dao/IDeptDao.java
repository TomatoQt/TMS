package zjut.dao;

import zjut.po.DeptEntity;

import java.util.List;

public interface IDeptDao {
    public void save(DeptEntity entity);
    public void delete(DeptEntity entity);
    public void update(DeptEntity entity);
    public DeptEntity findById(String deptId);
    public List findAll();
}
