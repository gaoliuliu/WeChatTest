




import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.Date;


import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import edu.fudan.gaowei.wechat.db.dao.pojo.Activity;
import edu.fudan.gaowei.wechat.db.dao.pojo.Cusername;
import edu.fudan.gaowei.wechat.db.service.ActivityService;
import edu.fudan.gaowei.wechat.db.service.CusernameService;






public class Testchaxun extends TestCase{

	public void Test1()
	{
		System.out.println("开始");
		ApplicationContext ctx  = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		//QueryModuleResultService mDao = (QueryModuleResultService)ctx.getBean("queryModuleResultServiceImpl");
		//QueryInputService iService = (QueryInputService)ctx.getBean("queryInputServiceImpl");
		//String moduleId = "C0001";
		//List<String> s1 = new ArrayList<String>();
		//s1.add("policyno@01234565778898");
		//s1.add("modno@90132132134144");
		
		//List<String> s2 = new ArrayList<String>();
		//s2.add("policyno@01234565778899");
		//s2.add("modno@90132132134145");
		ActivityService aDao = (ActivityService)ctx.getBean("ActivityService");
		Activity a = new Activity();
		a.setOpenid("2131312312");
		a.setType("1");
		a.setTypename("ceshi");
		a.setContent("32423423423");
		Date date = new Date();//获得系统时间.
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        //String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.
        Timestamp newdate = Timestamp.valueOf(nowTime);//把时间转换
		a.setTime(newdate);
		//List<List<String>> ne = new ArrayList<List<String>>();
		//ne.add(s1);
		//ne.add(s2);
		aDao.saveActivity(a);
		//List<String> moduleInput = iService.getModuleInput(moduleId);
		
		//List<String> moduleResult = mDao.getModuleResult(ne, moduleId);
		//System.out.println(moduleResult);
		//System.out.println(moduleInput);
		
		CusernameService bDao = (CusernameService)ctx.getBean("CusernameService");
		String openid = "oiI2fjk8sTDcvIXzDFjWu0WJBfmU";
		
	
		
		Cusername c  = bDao.getByOpenId(openid);
		System.out.println(c.getNickname());
		
	}
	
	
	
	public void Test3()
	{
		System.out.println("开始");
		//ApplicationContext ctx  = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		//QueryModuleResultService mDao = (QueryModuleResultService)ctx.getBean("queryModuleResultServiceImpl");
		//QueryInputService iService = (QueryInputService)ctx.getBean("queryInputServiceImpl");
		//String moduleId = "C0001";
		//List<String> s1 = new ArrayList<String>();
		//s1.add("policyno@01234565778898");
		//s1.add("modno@90132132134144");
		
		//List<String> s2 = new ArrayList<String>();
		//s2.add("policyno@01234565778899");
		//s2.add("modno@90132132134145");
		//CusernameService aDao = (CusernameService)ctx.getBean("CusernameService");
		//String openid = "oiI2fjk8sTDcvIXzDFjWu0WJBfmU";
		
	
		
		//Cusername c  = aDao.getByOpenId(openid);
		//System.out.println(c.getNickname());
		
		
	}
}
