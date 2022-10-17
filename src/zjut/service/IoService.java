package zjut.service;

import zjut.dao.IIoDao;
import zjut.dao.IToolDao;
import zjut.exception.IorecordException;
import zjut.po.IorecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;
import zjut.utils.ToolDistribute;

import java.util.*;

public class IoService implements IIoService{
    private IIoDao dao = null;
    private IToolDao toolDao = null;

    public IoService(){
        System.out.println("create IoService");
    }

    public void setDao(IIoDao dao) {
        this.dao = dao;
    }

    public IorecordEntity findByIOID(String ioid){
        String hql = "from IorecordEntity where id='"+ioid+"'";
        ArrayList<IorecordEntity> list = (ArrayList<IorecordEntity>) dao.FindByHql(hql);
        return list.get(0);
    }

    //申请出入库
    public boolean apply(IorecordEntity entity) {
        dao.save(entity);
        return true;
    }
    //初审
    public boolean examine(IorecordEntity entity) {
        dao.update(entity);
        return true;
    }
    //删除
    public boolean delete(IorecordEntity entity) {
        dao.delete(entity);
        return true;
    }
    //员工查询
    public List FindByStaffAndIo(StaffEntity entity, int io) {
        String hql ="";
        //初级员工 自己的提交查询
        if(entity.getAuthority() == 0) {
            hql = "from IorecordEntity as user where applicantId= '"+entity.getId() + "'"
            + "and IO=" + io;
        }

        //监管员 同一部门未审核
        else if(entity.getAuthority() == 1) {
            hql = "from IorecordEntity as user where dept_id= '"+entity.getDeptId() + "'" +
                    " and result = 'wait' and IO=" + io;
        }
        return dao.FindByHql(hql);
    }

    //获取通过审核的信息
    public List FindByTool(ToolEntity entity) {
        String hql = "from IorecordEntity as user where toolId='"+entity.getId()+"'" +
                "and result = 'pass1'";
        return dao.FindByHql(hql);
    }

    //获取用户未还
    public List<String> FindByOut(StaffEntity entity) {
        ArrayList<String> list = new ArrayList<String>();
        Map<String, Integer> map =new HashMap<String, Integer>();
        String hql = "from IorecordEntity as user where applicantId='"+entity.getId()+"'" +
                "and result = 'pass1'";
        List<IorecordEntity> record = dao.FindByHql(hql);
        for(int i = 0; i < record.size(); i++) {
            String id = record.get(i).getToolId();
            int f = (record.get(i).getIo() == 1) ? 1 : -1;
            if(map.containsKey(id)) {
                int num = map.get(id);
                num += f;
                map.put(id, num);
            }
            else {
                map.put(id, f);
            }
        }
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        while(it.hasNext()) {
            String key = it.next();
            int v = map.get(key);
            if(v < 0) {
                list.add(key);
            }
        }
        return list;
    }

    public void setToolDao(IToolDao toolDao) {
        this.toolDao = toolDao;
    }

//    返回工具id
    public String getATool(String toolTypeId, String dept_id) throws IorecordException {
//        List<String> UnavailableToolIds = dao.getUnavailableToolId();//得到不可用工夹具id列表
        //        System.out.println("deptId="+dept_id+","+"typeId="+toolTypeId);

        String hql = "from ToolEntity where deptId='"+dept_id+"' and typeId='"+toolTypeId+"' and Status='OK'";
        List<ToolEntity> AllToolEntities = toolDao.findByHql(hql);
//        System.out.println("size of AllToolEntities:"+AllToolEntities.size());
        //            if (!UnavailableToolIds.contains(tool.getId())){//工具可用且所请求的工具类型id一致
        //            }
        //可用工夹具列表
        ArrayList<ToolEntity> AvailableToolEntities = new ArrayList<>(AllToolEntities);


        ToolDistribute distribute = new ToolDistribute(AvailableToolEntities);
        if (AvailableToolEntities.size()<=0){
            throw new IorecordException("无可用的工夹具!");
        }else {
            return distribute.getToolId();
        }
    }
}