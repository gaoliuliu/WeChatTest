package edu.fudan.gaowei.wechat.db.dao.pojo;

/**
 * Cusername entity. @author MyEclipse Persistence Tools
 */
public class Cusername extends AbstractCusername implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Cusername() {
	}

	/** minimal constructor */
	public Cusername(String openid) {
		super(openid);
	}

	/** full constructor */
	public Cusername(String openid, String nickname, String role) {
		super(openid, nickname, role);
	}

}
