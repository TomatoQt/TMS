package zjut.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zjut.po.IorecordEntity;

import java.util.ArrayList;
import java.util.List;

public class IoDao extends BaseHibernateDao implements IIoDao {
    private Log log= LogFactory.getLog(IoDao.class);

    public void save(IorecordEntity entity) {
        log.debug("saving iorecord instance");
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

    public void update(IorecordEntity entity) {
        log.debug("updating iorecord instance");
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

    public void delete(IorecordEntity entity) {
        log.debug("deleting iorecord instance");
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

    public List<IorecordEntity> FindByHql(String hql) {
        String queryString = hql;
        Query queryObject = getSession().createQuery(queryString);
        return (List<IorecordEntity>)queryObject.list();
    }

    public List<String> getUnavailableToolId(){
        String queryString = "from IorecordEntity as record where record.io=0 and (record.result='wait' or record.result='pass1')";
        Query queryObject = getSession().createQuery(queryString);
        List<IorecordEntity> records = (List<IorecordEntity>)queryObject.list();
        List<String> toolIds = new ArrayList<>();
        for (IorecordEntity record : records){
            toolIds.add(record.getToolId());
        }
        return toolIds;
    }
}
