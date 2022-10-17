package zjut.service;

import zjut.dao.IToolTypeDao;
import zjut.po.TooltypeEntity;

import java.util.List;

public class ToolTypeService implements IToolTypeService{
    private IToolTypeDao toolTypeDao = null;
    //创建
    public boolean create(TooltypeEntity entity) {
        toolTypeDao.create(entity);
        return true;
    }
    //修改
    public boolean update(TooltypeEntity entity) {
        toolTypeDao.update(entity);
        return true;
    }
    //删除
    public boolean delete(TooltypeEntity entity) {
        toolTypeDao.delete(entity);
        return true;
    }

    //获取全部夹具
    public List findAll() {
        String hql = "from TooltypeEntity";
        return toolTypeDao.findByHql(hql);
    }

    public void setToolTypeDao(IToolTypeDao toolTypeDao) {
        this.toolTypeDao = toolTypeDao;
    }

    public TooltypeEntity findByToolTypeId(String toolTypeId){
        return toolTypeDao.getById(toolTypeId);
    }
}