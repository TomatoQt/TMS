package zjut.dao;

import zjut.po.StaffEntity;

import java.util.ArrayList;

public interface IStaffDao {
    public boolean save(StaffEntity transientInstance);
    public boolean delete(StaffEntity transientInstance);
    public boolean update(StaffEntity transientInstance);
    public ArrayList<StaffEntity> findByHql(String hql);
    public StaffEntity findById(String hql);
    
}
