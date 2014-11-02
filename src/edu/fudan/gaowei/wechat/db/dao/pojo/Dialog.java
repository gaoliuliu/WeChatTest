package edu.fudan.gaowei.wechat.db.dao.pojo;

import java.sql.Timestamp;


/**
 * Dialog entity. @author MyEclipse Persistence Tools
 */
public class Dialog extends AbstractDialog implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Dialog() {
	}

	/** full constructor */
	public Dialog(String openid, String content, Timestamp time) {
		super(openid, content, time);
	}

}
