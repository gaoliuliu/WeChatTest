package edu.fudan.gaowei.wechat.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContextUtil implements ApplicationContextAware{
	private static  ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
	    this.applicationContext = applicationContext;
	}
	public static  ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static  Object getBean(String id)
	{
		
		return applicationContext.getBean(id);
	}
	
	

}
