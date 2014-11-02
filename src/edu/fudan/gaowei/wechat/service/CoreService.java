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
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @param servletContext 
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		
		String respMessage = null;
		try {
			//System.out.println("post");
			
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";

			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
            
			String createTime = requestMap.get("CreateTime");
			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String txt = requestMap.get("Content");
				
				
				saveDialogInCore(fromUserName,txt,createTime);
				
				if(txt.startsWith("#��ҪK��#"))
				{
					respContent = buttonOneSwitch("#��ҪK��#","1",fromUserName,txt,createTime);
					
				}
				else if(txt.startsWith("#��Ҫ��Ӱ#"))
				{
					respContent = buttonOneSwitch("#��Ҫ��Ӱ#","2",fromUserName,txt,createTime);
				}
				else if(txt.startsWith("#��Ҫ����#"))
				{
					respContent = buttonOneSwitch("#��Ҫ����#","3",fromUserName,txt,createTime);
				}
				else if(txt.startsWith("#��Ҫ����#"))
				{
					respContent = buttonOneSwitch("#��Ҫ����#","4",fromUserName,txt,createTime);
				}
				else {
				String response = SimsimiTools.getSimsimiContentByNiuren(txt);
				respContent = response;
				}
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ����ʱ�޷�ʶ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ����ʱ�޷�ʶ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ����ʱ�޷�ʶ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵�����Ƶ��Ϣ����ʱ�޷�ʶ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע�������Իظ��ı���Ϣ��Ϸ��ɱС���飡(�s�F����)�s��ߩ���";
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
					respContent = "����(���ţ� = ��3��)<Ҫ ";
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("11")) {
						respContent = "�������¸�ʽ�ظ������ɰ�����Ը���������ί�ᣡ\n"+"#��ҪK��# ��������ҪȥStarMoon��";
					} else if (eventKey.equals("12")) {
						respContent = "�������¸�ʽ�ظ������ɰ�����Ը���������ί�ᣡ\n"+"#��Ҫ��Ӱ# ��������Ҫȥ�ٽ���԰��";
					} else if (eventKey.equals("13")) {
						respContent = "�������¸�ʽ�ظ������ɰ�����Ը���������ί�ᣡ\n"+"#��Ҫ����# ��������Ҫȥ�ܷ�����";
					} else if (eventKey.equals("14")) {
						respContent = "�������¸�ʽ�ظ������ɰ�����Ը���������ί�ᣡ\n"+"#��Ҫ����# ��������Ҫȥ���ң�";
					} else if (eventKey.equals("21")) {
						respContent = "��Ƭǽ�����幦�ܼ������ߣ������ڴ���";
					} else if (eventKey.equals("22")) {
						respContent = "��������ί������ʲô����ʷ��С���飬�ϳ�ȥ��";
					} else if (eventKey.equals("23")) {
						respContent = "���ְ񣡾��幦�ܼ������ߣ������ڴ���";
					} else if (eventKey.equals("31")) {
						respContent = "ίԱ��ɣ����幦�ܼ������ߣ������ڴ���";
					} else if (eventKey.equals("32")) {
						respContent = "����ѡ�֣����幦�ܼ������ߣ������ڴ���";
					} else if (eventKey.equals("33")) {
						respContent = "��ע��ί���˿���ֲ�΢��Ⱥ���������ǣ�";
					}
					else if(eventKey.equals("34")) {
						respContent = "��ı���ߣ����幦�ܼ������ߣ������ڴ���";
					}
					//respContent+="���幦�ܼ������ߣ������ڴ���";
					
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
		
		    respContent = "����Ը��:"+keyWord+"�Ѵ������ݿ�";
		}
		
		else{
			respContent = c.getRole()+"��Ը��:"+keyWord+"�Ѵ������ݿ�";
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
	    // ��΢�Ŵ����CreateTimeת����long���ͣ��ٳ���1000  
	    long msgCreateTime = Long.parseLong(createTime) * 1000L;  
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    return format.format(new Date(msgCreateTime));  
	}  
}
