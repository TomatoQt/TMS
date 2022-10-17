package zjut.dao;

import zjut.po.BuyrecordEntity;

import java.util.List;

public interface IBuyDao {
    public void save(BuyrecordEntity entity);
    public void update(BuyrecordEntity entity);
    public void delete(BuyrecordEntity entity);
    public List FindByStaff(String hql);
    public List FindByTool(String hql);
    public List FindByHql(String hql);
}
