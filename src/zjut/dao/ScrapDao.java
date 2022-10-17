package zjut.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zjut.po.ScraprecordEntity;

import java.util.List;

public class ScrapDao extends BaseHibernateDao implements IScrapDao {
    private Log log = LogFactory.getLog(ScrapDao.class);

    public void save(ScraprecordEntity entity) {
        log.debug("saving scraprecord instance");
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

    public void update(ScraprecordEntity entity) {
        log.debug("updating scraprecord instance");
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


    public void delete(ScraprecordEntity entity) {
        log.debug("deleting scraprecord instance");
        Transaction tran = null;
        Session session = null;
        try {
            session = getSession();
            tran = session.beginTransaction();
            getSession().delete(entity);
            tran.commit();
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            log.error("delete failed", re);
            throw re;
        } finally {
            session.close();
        }
    }


    public List<ScraprecordEntity> FindByHql(String hql) {
        String queryString = hql;
        Query queryObject = getSession().createQuery(queryString);
        return queryObject.list();
    }
}
