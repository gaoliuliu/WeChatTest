package edu.fudan.gaowei.wechat.db.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.fudan.gaowei.wechat.db.dao.IDialogDAO;
import edu.fudan.gaowei.wechat.db.dao.pojo.Dialog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Dialog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.fudan.gaowei.wechat.db.dao.pojo.Dialog
 * @author MyEclipse Persistence Tools
 */

public class DialogDAO extends HibernateDaoSupport implements IDialogDAO {
	private static final Logger log = LoggerFactory.getLogger(DialogDAO.class);
	// property constants
	public static final String OPENID = "openid";
	public static final String CONTENT = "content";

	protected void initDao() {
		// do nothing
	}

	public void save(Dialog transientInstance) {
		log.debug("saving Dialog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Dialog persistentInstance) {
		log.debug("deleting Dialog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dialog findById(java.lang.Integer id) {
		log.debug("getting Dialog instance with id: " + id);
		try {
			Dialog instance = (Dialog) getHibernateTemplate().get(
					"edu.fudan.gaowei.wechat.db.dao.impl.Dialog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Dialog instance) {
		log.debug("finding Dialog instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Dialog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Dialog as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOpenid(Object openid) {
		return findByProperty(OPENID, openid);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Dialog instances");
		try {
			String queryString = "from Dialog";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Dialog merge(Dialog detachedInstance) {
		log.debug("merging Dialog instance");
		try {
			Dialog result = (Dialog) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Dialog instance) {
		log.debug("attaching dirty Dialog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dialog instance) {
		log.debug("attaching clean Dialog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IDialogDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IDialogDAO) ctx.getBean("DialogDAO");
	}
}