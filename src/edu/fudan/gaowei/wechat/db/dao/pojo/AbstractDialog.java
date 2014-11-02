package edu.fudan.gaowei.wechat.db.dao.pojo;

import java.sql.Timestamp;

/**
 * AbstractDialog entity provides the base persistence definition of the Dialog
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDialog implements java.io.Serializable {

	// Fields

	private Integer seqId;
	private String openid;
	private String content;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public AbstractDialog() {
	}

	/** full constructor */
	public AbstractDialog(String openid, String content, Timestamp time) {
		this.openid = openid;
		this.content = content;
		this.time = time;
	}

	// Property accessors

	public Integer getSeqId() {
		return this.seqId;
	}

	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}