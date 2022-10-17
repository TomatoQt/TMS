package zjut.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zjut.po.BuyrecordEntity;

import java.util.List;

public class BuyDao extends BaseHibernateDao implements IBuyDao {
    private Log log = LogFactory.getLog(BuyDao.class);

    public void save(BuyrecordEntity entity) {
        log.debug("saving buyrecord instance");
        Transaction tran = null;
        Session session = null;
        try {
            session = getSession();
            tran = session.beginTransaction();
            session.save(entity);
            tran.commit();
            log.debug("save successful");
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            log.error("save failed", re);
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(BuyrecordEntity entity) {
        log.debug("updating buyrecord instance");
        Transaction tran = null;
        Session session = null;
        try {
            session = getSession();
            tran = session.beginTransaction();
            session.update(entity);
            getSession().update(entity);
            tran.commit();
            log.debug("update successful");
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            log.error("update failed", re);
            throw re;
        } finally {
            session.close();
        }
    }


    public void delete(BuyrecordEntity entity) {
        log.debug("deleting buyrecord instance");
        Transaction tran = null;
        Session session = null;
        try {
            session = getSession();
            tran = session.beginTransaction();
            getSession().delete(entity);
            tran.commit();
            log.debug("delete successful");
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            log.error("delete failed", re);
            throw re;
        } finally {
            session.close();
        }
    }


    public List<BuyrecordEntity> FindByStaff(String hql) {
        String queryString = hql;
        Query queryObject = getSession().createQuery(queryString);
        return queryObject.list();
    }


    public List FindByTool(String hql) {
        String queryString = hql;
        Query queryObject = getSession().createQuery(queryString);
        return queryObject.list();
    }

    public List FindByHql(String hql) {
        String queryString = hql;
        Query queryObject = getSession().createQuery(queryString);
        return queryObject.list();
    }
}