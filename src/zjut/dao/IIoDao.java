package zjut.dao;

import zjut.po.IorecordEntity;

import java.util.List;

public interface IIoDao {
    public void save(IorecordEntity entity);
    public void update(IorecordEntity entity);
    public void delete(IorecordEntity entity);
    public List FindByHql(String hql);
    public List<String> getUnavailableToolId();//返回不可用的某toolTypeId下的工夹具id列表
}
