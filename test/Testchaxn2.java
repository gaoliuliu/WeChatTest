import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import edu.fudan.gaowei.wechat.db.dao.pojo.Cusername;
import edu.fudan.gaowei.wechat.db.service.CusernameService;

import junit.framework.TestCase;


public class Testchaxn2 extends TestCase{
	@Test
	public void Test3()
	{
		//System.out.println("¿ªÊ¼");
	
		ApplicationContext ctx  = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		
		CusernameService aDao = (CusernameService)ctx.getBean("CusernameService");
		String openid = "oiI2fjk8sTDcvIXzDFjWu0WJBfmU";
		
	
		
		Cusername c  = aDao.getByOpenId(openid);
		System.out.println(c.getNickname());
		
		
	}
}
