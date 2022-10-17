package zjut.dao;

import zjut.po.TooltypeEntity;

import java.util.List;

public interface IToolTypeDao {
    public void create(TooltypeEntity entity);
    public void update(TooltypeEntity entity);
    public void delete(TooltypeEntity entity);
    public List findByHql(String hql);
    public TooltypeEntity getById(String toolTypeId);
}