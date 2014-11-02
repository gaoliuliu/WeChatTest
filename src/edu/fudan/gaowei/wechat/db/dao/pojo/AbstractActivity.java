package edu.fudan.gaowei.wechat.db.dao.pojo;

import java.sql.Timestamp;

/**
 * AbstractActivity entity provides the base persistence definition of the
 * Activity entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractActivity implements java.io.Serializable {

	// Fields

	private Integer seqId;
	private String type;
	private String typename;
	private String openid;
	private String content;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public AbstractActivity() {
	}

	/** full constructor */
	public AbstractActivity(String type, String typename, String openid,
			String content, Timestamp time) {
		this.type = type;
		this.typename = typename;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
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