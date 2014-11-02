package edu.fudan.gaowei.wechat.db.dao.pojo;

/**
 * AbstractCusername entity provides the base persistence definition of the
 * Cusername entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCusername implements java.io.Serializable {

	// Fields

	private String openid;
	private String nickname;
	private String role;

	// Constructors

	/** default constructor */
	public AbstractCusername() {
	}

	/** minimal constructor */
	public AbstractCusername(String openid) {
		this.openid = openid;
	}

	/** full constructor */
	public AbstractCusername(String openid, String nickname, String role) {
		this.openid = openid;
		this.nickname = nickname;
		this.role = role;
	}

	// Property accessors

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}