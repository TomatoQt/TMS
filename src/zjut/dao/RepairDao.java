package zjut.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zjut.po.RepairrecordEntity;
import zjut.po.StaffEntity;

import java.util.List;

public class RepairDao extends BaseHibernateDao implements IRepairDao{
    private Log log= LogFactory.getLog(RepairDao.class);

    public void save(RepairrecordEntity entity) {
        log.debug("saving repairrecord instance");
        Transaction tran=null;
        Session session=null;
        try{
            session=getSession();
            tran=session.beginTransaction();
            session.save(entity);
            tran.commit();
            log.debug("save successful");
        }catch (RuntimeException re){
            if(tran != null) tran.rollback();
            log.error("save failed", re);
            throw re;
        }finally {
            session.close();
        }
    }

    public void update(RepairrecordEntity entity) {
        log.debug("updating repairrecord instance");
        Transaction tran=null;
        Session session=null;
        try{
            session=getSession();
            tran=session.beginTransaction();
            session.update(entity);
            getSession().update(entity);
            tran.commit();
            log.debug("update successful");
        }catch (RuntimeException re){
            if(tran!=null) tran.rollback();
            log.error("update failed", re);
            throw re;
        }finally {
            session.close();
        }
    }

    public void delete(RepairrecordEntity entity) {
        log.debug("deleting repairrecord instance");
        Transaction tran=null;
        Session session=null;
        try{
            session=getSession();
            tran=session.beginTransaction();
            getSession().delete(entity);
            tran.commit();
            log.debug("delete successful");
        }catch (RuntimeException re){
            if(tran!=null) tran.rollback();
            log.error("delete failed", re);
            throw re;
        }finally {
            session.close();
        }
    }

    public List<RepairrecordEntity> FindByHql(String hql) {
        String queryString = hql;
        Query queryObject = getSession().createQuery(queryString);
        return queryObject.list();
    }

}