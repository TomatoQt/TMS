package zjut.service;

import zjut.dao.IScrapDao;
import zjut.po.ScraprecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;

import java.util.List;

public class ScrapService implements IScrapService{
    private IScrapDao dao = null;
    //申请
    public boolean apply(ScraprecordEntity entity) {
        dao.save(entity);
        return true;
    }
    //初审
    public boolean examine(ScraprecordEntity entity) {
        dao.update(entity);
        return true;
    }
    //终审
    public boolean final_examine(ScraprecordEntity entity) {
        dao.update(entity);
        return true;
    }

    //删除
    public boolean delete(ScraprecordEntity entity) {
        dao.delete(entity);
        return true;
    }
    //员工查询
    public List FindByStaff(StaffEntity entity) {
        String hql ="";
        //高级员工 自己的提交查询
        if(entity.getAuthority() == 1) {
            hql = "from ScraprecordEntity as user where applicantId= '"+entity.getId() + "'";
        }

        //监管员 同一部门未审核
        else if(entity.getAuthority() == 2) {
            hql = "from ScraprecordEntity as user where dept_id= '"+entity.getDeptId() + "'" +
                    " and result = 'wait'";
        }
        //经理 同一部门未审核
        else if(entity.getAuthority() == 3) {
            hql = "from ScraprecordEntity as user where dept_id= '"+entity.getDeptId() + "'" +
                    " and result = 'pass1'";
        }
        return dao.FindByHql(hql);
    }

    //获取通过审核的信息
    public List FindByTool(ToolEntity entity) {
        String hql = "from ScraprecordEntity as user where toolId='"+entity.getId()+"'" +
                "and result = 'pass2'";
        return dao.FindByHql(hql);
    }

    //通过id查找
    public ScraprecordEntity FindById(String id) {
        String hql = "from ScraprecordEntity where id = '"+id+"'";
        List x = (dao.FindByHql(hql));
        if(x.isEmpty()) return null;
        return (ScraprecordEntity)x.get(0);
    }



    public void setDao(IScrapDao dao) {
        this.dao = dao;
    }
}