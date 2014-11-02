package edu.fudan.gaowei.wechat.db.service;

import edu.fudan.gaowei.wechat.db.dao.pojo.Cusername;

public interface CusernameService {

	public abstract Cusername getByOpenId(String openid);

}