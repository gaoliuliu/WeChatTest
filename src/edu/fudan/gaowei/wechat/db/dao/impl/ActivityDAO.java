package edu.fudan.gaowei.wechat.db.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.fudan.gaowei.wechat.db.dao.IActivityDAO;
import edu.fudan.gaowei.wechat.db.dao.pojo.Activity;

/**
 * A data access object (DAO) providing persistence and search support for
 * Activity entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.fudan.gaowei.wechat.db.dao.pojo.Activity
 * @author MyEclipse Persistence Tools
 */

public class ActivityDAO extends HibernateDaoSupport implements IActivityDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ActivityDAO.class);
	protected void initDao() {
		// do nothing
	}

	public void save(Activity transientInstance) {
		log.debug("saving Activity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Activity persistentInstance) {
		log.debug("deleting Activity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Activity findById(java.lang.Integer id) {
		log.debug("getting Activity instance with id: " + id);
		try {
			Activity instance = (Activity) getHibernateTemplate().get(
					"edu.fudan.gaowei.wechat.db.dao.pojo.Activity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Activity instance) {
		log.debug("finding Activity instance by example");
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
		log.debug("finding Activity instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Activity as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	

	public List findAll() {
		log.debug("finding all Activity instances");
		try {
			String queryString = "from Activity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Activity merge(Activity detachedInstance) {
		log.debug("merging Activity instance");
		try {
			Activity result = (Activity) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Activity instance) {
		log.debug("attaching dirty Activity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Activity instance) {
		log.debug("attaching clean Activity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IActivityDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IActivityDAO) ctx.getBean("ActivityDAO");
	}
}