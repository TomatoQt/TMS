package zjut.service;

import zjut.dao.IRepairDao;
import zjut.po.RepairrecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;

import java.util.ArrayList;
import java.util.List;

public class RepairService implements IRepairService{
    private IRepairDao dao = null;
    //申请
    public boolean apply(RepairrecordEntity entity) {
        dao.save(entity);
        return true;
    }
    //初审
    public boolean examine(RepairrecordEntity entity) {
        dao.update(entity);
        return true;
    }
    //删除
    public boolean delete(RepairrecordEntity entity) {
        dao.delete(entity);
        return true;
    }
    //员工查询
    public List FindByStaff(StaffEntity entity) {
        String hql ="";
        //初级员工 自己的提交查询
        if(entity.getAuthority() == 0) {
            hql = "from RepairrecordEntity as user where applicantId= '"+entity.getId() + "'";
        }

        //高级员工 同一部门未审核
        else if(entity.getAuthority() == 1) {
            hql = "from RepairrecordEntity as user where dept_id= '"+entity.getDeptId() + "'" +
                    " and result = 'wait'";
        }
        return dao.FindByHql(hql);
    }

    //获取通过审核的信息
    public List FindByTool(ToolEntity entity) {
        String hql = "from RepairrecordEntity as user where toolId='"+entity.getId()+"'" +
                "and result = 'pass1'";
        return dao.FindByHql(hql);
    }

    public RepairrecordEntity FindById(String id) {
        String hql = "from RepairrecordEntity where id='"+id+"'";
        ArrayList<RepairrecordEntity> list =  (ArrayList<RepairrecordEntity>)dao.FindByHql(hql);
        return list.get(0);
    }


    public void setDao(IRepairDao dao) {
        this.dao = dao;
    }
}