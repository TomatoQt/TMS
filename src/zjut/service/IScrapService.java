package zjut.service;

import zjut.po.RepairrecordEntity;
import zjut.po.ScraprecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;

import java.util.List;

public interface IScrapService {
    public boolean apply(ScraprecordEntity entity);
    public boolean examine(ScraprecordEntity entity);
    public boolean final_examine(ScraprecordEntity entity);
    public boolean delete(ScraprecordEntity entity);
    public List FindByStaff(StaffEntity entity);
    public List FindByTool(ToolEntity entity);
    public ScraprecordEntity FindById(String id);
}
