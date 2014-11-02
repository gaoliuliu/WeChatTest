package edu.fudan.gaowei.wechat.db.dao.impl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.fudan.gaowei.wechat.db.dao.ICusernameDAO;
import edu.fudan.gaowei.wechat.db.dao.pojo.Cusername;

/**
 * A data access object (DAO) providing persistence and search support for
 * Cusername entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.fudan.gaowei.wechat.db.dao.pojo.Cusername
 * @author MyEclipse Persistence Tools
 */

public class CusernameDAO extends HibernateDaoSupport implements ICusernameDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CusernameDAO.class);
	// property constants
	public static final String NICKNAME = "nickname";
	public static final String ROLE = "role";

	protected void initDao() {
		// do nothing
	}

	public void save(Cusername transientInstance) {
		log.debug("saving Cusername instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Cusername persistentInstance) {
		log.debug("deleting Cusername instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Cusername findById(java.lang.String id) {
		log.debug("getting Cusername instance with id: " + id);
		try {
			Cusername instance = (Cusername) getHibernateTemplate().get(
					"edu.fudan.gaowei.wechat.db.dao.pojo.Cusername", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Cusername instance) {
		log.debug("finding Cusername instance by example");
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
		log.debug("finding Cusername instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Cusername as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNickname(Object nickname) {
		return findByProperty(NICKNAME, nickname);
	}

	public List findByRole(Object role) {
		return findByProperty(ROLE, role);
	}

	public List findAll() {
		log.debug("finding all Cusername instances");
		try {
			String queryString = "from Cusername";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Cusername merge(Cusername detachedInstance) {
		log.debug("merging Cusername instance");
		try {
			Cusername result = (Cusername) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Cusername instance) {
		log.debug("attaching dirty Cusername instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cusername instance) {
		log.debug("attaching clean Cusername instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ICusernameDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ICusernameDAO) ctx.getBean("CusernameDAO");
	}
}