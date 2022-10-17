package zjut.service;

import zjut.po.RepairrecordEntity;
import zjut.po.ScraprecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;

import java.util.List;

public interface IRepairService {
    public boolean apply(RepairrecordEntity entity);
    public boolean examine(RepairrecordEntity entity);
    public boolean delete(RepairrecordEntity entity);
    public List FindByStaff(StaffEntity entity);
    public List FindByTool(ToolEntity entity);
    public RepairrecordEntity FindById(String id);
}