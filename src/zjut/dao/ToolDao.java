package zjut.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import zjut.po.ToolEntity;

import java.util.ArrayList;
import java.util.List;

public class ToolDao extends BaseHibernateDao implements IToolDao{
    private Log log= LogFactory.getLog(ToolDao.class);

    public void save(ToolEntity entity) {
        log.debug("saving tool instance");
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

    public void delete(ToolEntity entity) {
        log.debug("delete tool instance");
        Transaction tran = null;
        Session session = null;
        try {
            session = getSession();
            tran = session.beginTransaction();
            session.delete(entity);
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

    public void update(ToolEntity entity) {
        log.debug("update tool instance");
        Transaction tran = null;
        Session session = null;
        try {
            session = getSession();
            tran = session.beginTransaction();
            session.update(entity);
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


    public List findByHql(String hql) {
        String queryString = hql;
        org.hibernate.Query queryObject = getSession().createQuery(queryString);
        return queryObject.list();
    }

    public ArrayList<ToolEntity> getAllTool() {
        log.debug("getting all tools");
        String hql="from ToolEntity";
        Transaction tran=null;
        Session session=null;
        ArrayList<ToolEntity> toolArrayList=new ArrayList<ToolEntity>();
        try{
            session=getSession();
            tran=session.beginTransaction();
            Query query=session.createQuery(hql);
            List<ToolEntity> list=query.list();//转换可能有问题，需要测试
            toolArrayList.addAll(list);
        }catch (RuntimeException re){
            if (tran!=null) tran.rollback();
            throw re;
        }finally {
            assert session != null;
            session.close();
        }

        return toolArrayList;
    }


	public ToolEntity findById(String id) {
		String hql = "from ToolEntity t where t.id = ?";
		Query<ToolEntity> query = getSession().createQuery(hql, ToolEntity.class);
		query.setParameter(0, id);
		ToolEntity toolEntity = query.list().get(0);
		return toolEntity;
	}

	public List<ToolEntity> findByDeptAndStatus(String deptId, int status) {
		String hql = "from ToolEntity t where t.deptId = ? and t.status = ? ";
		Query<ToolEntity> query = getSession().createQuery(hql, ToolEntity.class);
		query.setParameter(0, deptId);
		query.setParameter(1, status);
		List<ToolEntity> list = query.list();
		return list;
	}

}
