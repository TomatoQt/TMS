package zjut.dao;

import zjut.po.ScraprecordEntity;

import java.util.List;

public interface IScrapDao {
    public void save(ScraprecordEntity entity);
    public void update(ScraprecordEntity entity);
    public void delete(ScraprecordEntity entity);
    public List FindByHql(String hql);
}