package zjut.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zjut.po.TooltypeEntity;

import java.util.List;

public class ToolTypeDao extends BaseHibernateDao implements IToolTypeDao{
    private Log log= LogFactory.getLog(ToolTypeDao.class);
    //创建工夹具类别
    public void create(TooltypeEntity entity) {
        System.out.println("name:"+entity.getName());
        System.out.println("desc:"+entity.getDescription());
        log.debug("saving staff instance");
        Transaction tran=null;
        Session session=null;
        try{
            session=getSession();
            tran=session.beginTransaction();
            System.out.println("about to save");
            session.save(entity);
            tran.commit();
            System.out.println("save complete");
            log.debug("save successful");
        }catch (RuntimeException re){
            if (tran != null) tran.rollback();;
            log.error("save failed", re);
            throw re;
        }finally {
            session.close();
        }
    }
    //修改工夹具类别
    public void update(TooltypeEntity entity) {
        log.debug("updating tooltype instance");
        Transaction tran=null;
        Session session=null;
        try{
            session=getSession();
            tran=session.beginTransaction();
            session.update(entity);
            tran.commit();
            log.debug("update successful");
        }catch (RuntimeException re){
            tran.rollback();;
            log.error("update failed", re);
            throw re;
        }finally {
            session.close();
        }
    }
    //删除工夹具类别
    public void delete(TooltypeEntity entity) {
        log.debug("deleting tooltype instance");
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

    public List findByHql(String hql) {
        String queryString = hql;
        return getSession().createQuery(queryString).list();
    }

    public TooltypeEntity getById(String toolTypeId){
        String hql = "from TooltypeEntity where id='"+toolTypeId+"'";
        return (TooltypeEntity) findByHql(hql).get(0);
    }
}