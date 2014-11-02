package edu.fudan.gaowei.wechat.db.service.impl;



import edu.fudan.gaowei.wechat.db.dao.impl.DialogDAO;

import edu.fudan.gaowei.wechat.db.dao.pojo.Dialog;

import edu.fudan.gaowei.wechat.db.service.DialogService;





public class DialogServiceImpl implements DialogService{

	private DialogDAO dialogDaoImpl;
	
	

	



	public void saveDialog(Dialog transientInstance) {
		// TODO Auto-generated method stub
		dialogDaoImpl.save(transientInstance);
		return ;
	}







	public DialogDAO getDialogDaoImpl() {
		return dialogDaoImpl;
	}







	public void setDialogDaoImpl(DialogDAO dialogDaoImpl) {
		this.dialogDaoImpl = dialogDaoImpl;
	}

}
