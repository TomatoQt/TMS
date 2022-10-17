package zjut.service;

import zjut.dao.DeptDao;
import zjut.dao.IDeptDao;
import zjut.po.DeptEntity;

import java.util.List;

public interface IDeptService {
    public List getAllDept();
    public void newDept(DeptEntity entity);
    public void editDept(DeptEntity entity);
    public void deleteDept(DeptEntity entity);
    public DeptEntity findById(String id);
}
