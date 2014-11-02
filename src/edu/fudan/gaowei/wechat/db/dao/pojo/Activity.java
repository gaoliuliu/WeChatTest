package edu.fudan.gaowei.wechat.db.dao.pojo;

import java.sql.Timestamp;

/**
 * Activity entity. @author MyEclipse Persistence Tools
 */
public class Activity extends AbstractActivity implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Activity() {
	}

	/** full constructor */
	public Activity(String type, String typename, String openid,
			String content, Timestamp time) {
		super(type, typename, openid, content, time);
	}

}
