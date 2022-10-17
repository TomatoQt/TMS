package zjut.service;

import zjut.dao.IDeptDao;
import zjut.po.DeptEntity;

import java.util.List;

public class DeptService implements IDeptService{
    private IDeptDao deptDao = null;

    @Override
    public List getAllDept() {//得到所有dept
        return deptDao.findAll();
    }

    @Override
    public void newDept(DeptEntity entity) {
        deptDao.save(entity);
    }

    @Override
    public void editDept(DeptEntity entity) {
        deptDao.update(entity);
    }

    @Override
    public void deleteDept(DeptEntity entity) {
        deptDao.delete(entity);
    }

    @Override
    public DeptEntity findById(String id) {
        return deptDao.findById(id);
    }

    public void setDeptDao(IDeptDao deptDao) {
        this.deptDao = deptDao;
    }
}
