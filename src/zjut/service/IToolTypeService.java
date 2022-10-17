package zjut.service;

import zjut.po.TooltypeEntity;

import java.util.List;

public interface IToolTypeService {
    public boolean create(TooltypeEntity entity);
    public boolean update(TooltypeEntity entity);
    public boolean delete(TooltypeEntity entity);
    public List findAll();
    public TooltypeEntity findByToolTypeId(String toolTypeId);
}