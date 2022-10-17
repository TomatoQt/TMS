package zjut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zjut.action.StaffAction;
import zjut.dao.IStaffDao;
import zjut.po.StaffEntity;

import java.util.List;

public class StaffService implements IStaffService{

    private IStaffDao staffDao;

    public void setStaffDao(IStaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public StaffEntity login(StaffEntity staff){
        String hql = "from StaffEntity as staff where id='" + staff.getId() + "'";
        System.out.println(hql);
    	List temp = staffDao.findByHql(hql);
    	if(temp.isEmpty()) {
    	    return null;
        }
        StaffEntity findById = (StaffEntity) temp.get(0);
    	if (!findById.getPassword().equals(staff.getPassword())) {
    	    findById.setAuthority(-1);
		}
        return findById;
    }

    public StaffEntity FindById(String id) {
        String hql = "from StaffEntity as staff where id='" + id + "'";
        List temp = staffDao.findByHql(hql);
        if(temp.isEmpty()) {
            return null;
        }
        return (StaffEntity) temp.get(0);

    }


    public boolean register(StaffEntity transientInstance) {
        return staffDao.save(transientInstance);
    }

    public boolean deleteStaff(StaffEntity transientInstance) {
        return staffDao.delete(transientInstance);
    }

    public boolean updateStaffInfo(StaffEntity transientInstance) {
        return staffDao.update(transientInstance);
    }

    public List<StaffEntity> findAll() {
        String hql = "from StaffEntity as user where user.authority<4";
        return staffDao.findByHql(hql);
    }
}
