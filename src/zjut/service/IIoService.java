package zjut.service;

import zjut.exception.IorecordException;
import zjut.po.IorecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;

import java.util.List;

public interface IIoService {
    public boolean apply(IorecordEntity entity);
    public boolean examine(IorecordEntity entity);
    public boolean delete(IorecordEntity entity);
    public List FindByStaffAndIo(StaffEntity entity, int io);
    public List FindByTool(ToolEntity entity);
    public List<String> FindByOut(StaffEntity entity);
    public String getATool(String toolTypeId, String dept_id) throws IorecordException;//return toolId
    public IorecordEntity findByIOID(String ioid);
}
