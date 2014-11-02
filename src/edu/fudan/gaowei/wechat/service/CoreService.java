package edu.fudan.gaowei.wechat.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



import edu.fudan.gaowei.wechat.db.dao.pojo.Activity;
import edu.fudan.gaowei.wechat.db.dao.pojo.Cusername;
import edu.fudan.gaowei.wechat.db.dao.pojo.Dialog;
import edu.fudan.gaowei.wechat.db.service.ActivityService;
import edu.fudan.gaowei.wechat.db.service.CusernameService;
import edu.fudan.gaowei.wechat.db.service.DialogService;

import edu.fudan.gaowei.wechat.resp.TextMessage;
import edu.fudan.gaowei.wechat.util.MessageUtil;
import edu.fudan.gaowei.wechat.util.SpringApplicationContextUtil;

public class CoreService {

	
	

	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @param servletContext 
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		
		String respMessage = null;
		try {
			//System.out.println("post");
			
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
            
			String createTime = requestMap.get("CreateTime");
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String txt = requestMap.get("Content");
				
				
				saveDialogInCore(fromUserName,txt,createTime);
				
				if(txt.startsWith("#我要K歌#"))
				{
					respContent = buttonOneSwitch("#我要K歌#","1",fromUserName,txt,createTime);
					
				}
				else if(txt.startsWith("#我要摄影#"))
				{
					respContent = buttonOneSwitch("#我要摄影#","2",fromUserName,txt,createTime);
				}
				else if(txt.startsWith("#我要桌游#"))
				{
					respContent = buttonOneSwitch("#我要桌游#","3",fromUserName,txt,createTime);
				}
				else if(txt.startsWith("#我要密室#"))
				{
					respContent = buttonOneSwitch("#我要密室#","4",fromUserName,txt,createTime);
				}
				else {
				String response = SimsimiTools.getSimsimiContentByNiuren(txt);
				respContent = response;
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！暂时无法识别！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！暂时无法识别！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！暂时无法识别！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！暂时无法识别！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！您可以回复文本信息调戏面杀小秘书！(╯‵□′)╯︵┻━┻";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					respContent = "不＞(￣ε￣ = ￣3￣)<要 ";
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("11")) {
						respContent = "根据以下格式回复，即可把您的愿望传达给组委会！\n"+"#我要K歌# 天神本周四要去StarMoon！";
					} else if (eventKey.equals("12")) {
						respContent = "根据以下格式回复，即可把您的愿望传达给组委会！\n"+"#我要摄影# 天神本周四要去临江公园！";
					} else if (eventKey.equals("13")) {
						respContent = "根据以下格式回复，即可把您的愿望传达给组委会！\n"+"#我要桌游# 天神本周四要去避风塘！";
					} else if (eventKey.equals("14")) {
						respContent = "根据以下格式回复，即可把您的愿望传达给组委会！\n"+"#我要密室# 天神本周四要去密室！";
					} else if (eventKey.equals("21")) {
						respContent = "照片墙！具体功能即将上线，敬请期待！";
					} else if (eventKey.equals("22")) {
						respContent = "疯了吗，组委会哪有什么黑历史！小秘书，拖出去打！";
					} else if (eventKey.equals("23")) {
						respContent = "积分榜！具体功能即将上线，敬请期待！";
					} else if (eventKey.equals("31")) {
						respContent = "委员风采！具体功能即将上线，敬请期待！";
					} else if (eventKey.equals("32")) {
						respContent = "优秀选手！具体功能即将上线，敬请期待！";
					} else if (eventKey.equals("33")) {
						respContent = "关注组委会粉丝俱乐部微信群，加入我们！";
					}
					else if(eventKey.equals("34")) {
						respContent = "出谋划策！具体功能即将上线，敬请期待！";
					}
					//respContent+="具体功能即将上线，敬请期待！";
					
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
		
		
	}
	private static String buttonOneSwitch(String service, String type,
			String fromUserName, String txt, String createTime) {
		// TODO Auto-generated method stub
		String respContent ="";
		String keyWord = txt.replaceAll("^"+service, "").trim();  
		System.out.println(keyWord);
		saveActivityInCore(fromUserName,type,service.replaceAll("#",""),txt,createTime);
		Cusername c = getRoleByOpenId(fromUserName);
		if(c==null){
		
		    respContent = "您的愿望:"+keyWord+"已存入数据库";
		}
		
		else{
			respContent = c.getRole()+"的愿望:"+keyWord+"已存入数据库";
		}
		return respContent;
	}
	private static Cusername getRoleByOpenId(String fromUserName) {
		// TODO Auto-generated method stub
		Cusername c = null;
		CusernameService bDao = (CusernameService)SpringApplicationContextUtil.getBean("CusernameService");		
		c  = bDao.getByOpenId(fromUserName);
		
		return c;
	}
	public static void saveActivityInCore(String fromUserName, String type,String typeName,String txt, String createTime)
	{
		ActivityService aDao = (ActivityService)SpringApplicationContextUtil.getBean("ActivityService");
		Activity a = new Activity();
		a.setOpenid(fromUserName);
		a.setType(type);
		a.setTypename(typeName);
		a.setContent(txt);
		a.setTime(Timestamp.valueOf(formatTime(createTime)));
		
		aDao.saveActivity(a);
	}
	
	public static void saveDialogInCore(String fromUserName, String txt, String createTime)
	{
		DialogService aDao = (DialogService)SpringApplicationContextUtil.getBean("DialogService");
		Dialog a = new Dialog();
		a.setOpenid(fromUserName);
		a.setContent(txt);
		a.setTime(Timestamp.valueOf(formatTime(createTime)));
		aDao.saveDialog(a);
	}
	
	public static String formatTime(String createTime) {  
	    // 将微信传入的CreateTime转换成long类型，再乘以1000  
	    long msgCreateTime = Long.parseLong(createTime) * 1000L;  
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    return format.format(new Date(msgCreateTime));  
	}  
}
