package zjut.service;

import zjut.po.BuyrecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;

import java.util.List;

public interface IBuyService {
    public boolean apply(BuyrecordEntity entity);
    public boolean examine(BuyrecordEntity entity);
    public boolean final_examine(BuyrecordEntity entity);
    public boolean delete(BuyrecordEntity entity);
    public List FindByStaff(StaffEntity entity);
//    public List FindByTool(ToolEntity entity);
    public BuyrecordEntity FindById(String BuyrecordId);
    public boolean buyTools(BuyrecordEntity entity);
}