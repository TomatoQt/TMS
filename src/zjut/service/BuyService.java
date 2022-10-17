package zjut.service;

import zjut.dao.IBuyDao;
import zjut.dao.IToolDao;
import zjut.dao.IToolTypeDao;
import zjut.po.BuyrecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;

import java.util.Date;
import java.util.List;

public class BuyService implements IBuyService{
    private IBuyDao dao = null;
    private IToolDao toolDao = null;
    private IToolTypeDao toolTypeDao = null;
    //申请
    public boolean apply(BuyrecordEntity entity) {
        dao.save(entity);
        return true;
    }
    //初审
    public boolean examine(BuyrecordEntity entity) {
        dao.update(entity);
        return true;
    }
    //终审
    public boolean final_examine(BuyrecordEntity entity) {
        dao.update(entity);
        return true;
    }

    //删除
    public boolean delete(BuyrecordEntity entity) {
        dao.delete(entity);
        return true;
    }
    //员工查询
    public List FindByStaff(StaffEntity entity) {
        String hql ="";
        //II级员工 自己的提交查询
        if(entity.getAuthority() == 1) {
            hql = "from BuyrecordEntity as user where staffId= '"+entity.getId() + "'";
        }

        //监管员 同一部门未审核
        else if(entity.getAuthority() == 2) {
            hql = "from BuyrecordEntity as user where dept_id= '"+entity.getDeptId() + "'" +
                    " and result = 'wait'";
        }
        //经理 同一部门未审核
        else if(entity.getAuthority() == 3) {
            hql = "from BuyrecordEntity as user where dept_id= '"+entity.getDeptId() + "'" +
                    " and result = 'pass1'";
        }
        return dao.FindByStaff(hql);
    }

//    //获取通过审核的信息
//    public List FindByTool(ToolEntity entity) {
//        String hql = "from BuyrecordEntity as user where toolId='"+entity.getId()+"'" +
//                "and result = 'pass2'";
//        return dao.FindByStaff(hql);
//    }

    public void setDao(IBuyDao dao) {
        this.dao = dao;
    }

    public BuyrecordEntity FindById(String BuyrecordId){
        String hql = "from BuyrecordEntity where id='"+BuyrecordId+"'";
        return (BuyrecordEntity)dao.FindByHql(hql).get(0);
    }

    public boolean buyTools(BuyrecordEntity entity){
        int number = entity.getQuantity();
        String toolTypeId = entity.getToolTypeId();
        boolean result = false;

        try {
            for (int i=0;i<number;i++){
                ToolEntity tool = new ToolEntity();
                //TODO 这个寿命怎么定，默认100? 要不寿命输入把，还要看寿命是什么单位
                tool.setLife(100);
                tool.setDeptId(entity.getDeptId());
                tool.setInTime(new Date());
                tool.setStatus("OK");//OK表示可用在库中
                tool.setBuyRecordId(entity.getId());
                tool.setTypeId(toolTypeId);
                tool.setName(toolTypeDao.getById(toolTypeId).getName());
                toolDao.save(tool);
                System.out.println("tool++");
            }
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            result = false;
        }

        return result;
    }

    public void setToolDao(IToolDao toolDao) {
        this.toolDao = toolDao;
    }

    public IToolTypeDao getToolTypeDao() {
        return toolTypeDao;
    }

    public void setToolTypeDao(IToolTypeDao toolTypeDao) {
        this.toolTypeDao = toolTypeDao;
    }
}