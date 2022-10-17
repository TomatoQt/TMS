package zjut.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import zjut.po.StaffEntity;

import java.util.ArrayList;
import java.util.List;


public class StaffDao extends BaseHibernateDao implements IStaffDao{
    private Log log= LogFactory.getLog(StaffDao.class);

    //管理员：添加用户
    public boolean save(StaffEntity staff){
        log.debug("saving staff instance");
        Transaction tran=null;
        Session session=null;
        boolean result=false;
        try{
            session=getSession();
            tran=session.beginTransaction();
            session.save(staff);
            tran.commit();
            result=true;
        }catch (RuntimeException re){
            result=false;
            if(tran!=null) tran.rollback();
            throw re;
        }finally {
            session.close();
        }
        return result;
    }


    //管理员：删除用户
    public boolean delete(StaffEntity staff){
        log.debug("deleting staff instance");
        Transaction tran=null;
        Session session=null;
        boolean result=false;
        try{
            session=getSession();
            tran=session.beginTransaction();
            session.delete(staff);
            tran.commit();
            result=true;
        }catch (RuntimeException re){
            result=false;
            if(tran!=null) tran.rollback();
            throw re;
        }finally {
            session.close();
        }
        return result;
    }

    //管理员：查找用户
    public ArrayList<StaffEntity> findByHql(String hql) {
        log.debug("finding staff instance");
        Transaction tran=null;
        Session session=null;
        ArrayList<StaffEntity> staffArrayList=null;
        try{
            session=getSession();
            tran=session.beginTransaction();
            staffArrayList=(ArrayList<StaffEntity>)session.createQuery(hql).list();
            tran.commit();
        }catch (RuntimeException re){
            if(tran!=null) tran.rollback();
            throw re;
        }finally {
            session.close();
        }
        return staffArrayList;
    }

    //管理员：修改用户信息
    public boolean update(StaffEntity staff){
        log.debug("updating staff instance");
        Transaction tran=null;
        Session session=null;
        boolean result=false;
        try{
            session=getSession();
            tran=session.beginTransaction();
            session.update(staff);
            tran.commit();
            result=true;
        }catch (RuntimeException re){
            result=false;
            if(tran!=null) tran.rollback();
            throw re;
        }finally {
            session.close();
        }
        return result;
    }


	public StaffEntity findById(String hql) {
        log.debug("finding staffById instance");
        Session session = null;
        try {
            session = getSession();
            Query<StaffEntity> createQuery = session.createQuery(hql);
            List<StaffEntity> list = createQuery.list();
            return list.size() > 0 ? list.get(0) : null;
        } finally {
            session.close();
        }
	}
}
