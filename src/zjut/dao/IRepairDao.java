package zjut.dao;

import zjut.po.RepairrecordEntity;
import zjut.po.StaffEntity;

import java.util.List;

public interface IRepairDao {
    public void save(RepairrecordEntity entity);
    public void update(RepairrecordEntity entity);
    public void delete(RepairrecordEntity entity);
    public List FindByHql(String hql);
}