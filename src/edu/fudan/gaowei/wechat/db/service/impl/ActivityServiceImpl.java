package edu.fudan.gaowei.wechat.db.service.impl;



import edu.fudan.gaowei.wechat.db.dao.impl.ActivityDAO;
import edu.fudan.gaowei.wechat.db.dao.pojo.Activity;
import edu.fudan.gaowei.wechat.db.service.ActivityService;





public class ActivityServiceImpl implements ActivityService{

	private ActivityDAO activityDaoImpl;
	
	

	public ActivityDAO getActivityDaoImpl() {
		return activityDaoImpl;
	}

	/* (non-Javadoc)
	 * @see edu.fudan.gaowei.wechat.db.service.impl.ActivityService#setActivityDaoImpl(edu.fudan.gaowei.wechat.db.dao.impl.ActivityDAO)
	 */
	public void setActivityDaoImpl(ActivityDAO activityDaoImpl) {
		this.activityDaoImpl = activityDaoImpl;
	}




	public void saveActivity(Activity transientInstance) {
		// TODO Auto-generated method stub
		activityDaoImpl.save(transientInstance);
		return ;
	}

}
