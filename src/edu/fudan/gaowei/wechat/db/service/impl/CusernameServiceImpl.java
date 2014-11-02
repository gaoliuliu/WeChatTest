package edu.fudan.gaowei.wechat.db.service.impl;


import edu.fudan.gaowei.wechat.db.dao.impl.CusernameDAO;
import edu.fudan.gaowei.wechat.db.dao.pojo.Cusername;
import edu.fudan.gaowei.wechat.db.service.CusernameService;

public class CusernameServiceImpl implements CusernameService {

	private CusernameDAO cusernameDaoImpl;

	public CusernameDAO getCusernameDaoImpl() {
		return cusernameDaoImpl;
	}

	public void setCusernameDaoImpl(CusernameDAO cusernameDaoImpl) {
		this.cusernameDaoImpl = cusernameDaoImpl;
	}

	
	/* (non-Javadoc)
	 * @see edu.fudan.gaowei.wechat.db.service.impl.CusernameService#getByOpenId(java.lang.String)
	 */
	public Cusername getByOpenId(String openid)
	{
		return cusernameDaoImpl.findById(openid);
		
	}
	
	
}
