package zjut.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import zjut.po.DeptEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Queue;

public class DeptDao extends BaseHibernateDao implements IDeptDao{
    private Log log = LogFactory.getLog(DeptDao.class);

    @Override
    public void save(DeptEntity entity) {
        log.debug("saving Dept instance");
        Transaction tran=null;
        Session session=null;
        try{
            session=getSession();
            tran=session.beginTransaction();
            session.save(entity);
            tran.commit();
            log.debug("Dept saved");
        }catch (RuntimeException re){
            if (tran!=null) tran.rollback();
            log.error("save Dept failed");
            throw re;
        }finally {
            session.close();
        }
    }

    @Override
    public void delete(DeptEntity entity) {
        log.debug("deleting Dept instance");
        Transaction tran=null;
        Session session=null;
        try {
            session=getSession();
            tran=session.beginTransaction();
            session.save(entity);
            tran.commit();
            log.debug("Dept deleted");
        }catch (RuntimeException re){
            if (tran!=null) tran.rollback();
            log.error("delete Dept failed");
        }finally {
            session.close();
        }
    }

    @Override
    public void update(DeptEntity entity) {
        log.debug("updating Dept instance");
        Transaction tran=null;
        Session session=null;
        try {
            session=getSession();
            tran=session.beginTransaction();
            session.update(entity);
            tran.commit();
            log.debug("Dept updated");
        }catch (RuntimeException re){
            if (tran!=null) tran.rollback();
            log.error("update Dept failed");
        }finally {
            session.close();
        }
    }

    @Override
    public DeptEntity findById(String deptId) {
        String hql="from DeptEntity where id='"+deptId+"'";
        Query queryObject = getSession().createQuery(hql);
        return (DeptEntity)queryObject.list().get(0);
    }

    @Override
    public List<DeptEntity> findAll() {
        String hql="from DeptEntity";
        Query queryObject = getSession().createQuery(hql);
        return queryObject.list();
    }


}
