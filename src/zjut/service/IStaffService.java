package zjut.service;

import zjut.po.StaffEntity;

import java.util.List;

public interface IStaffService {
    public StaffEntity login(StaffEntity staff);

    public boolean register(StaffEntity transientInstance);

    public boolean deleteStaff(StaffEntity transientInstance);

    public boolean updateStaffInfo(StaffEntity transientInstance);

    public List<StaffEntity>findAll();

    public StaffEntity FindById(String id);
}
