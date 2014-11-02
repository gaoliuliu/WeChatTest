package edu.fudan.gaowei.wechat.menu.main;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fudan.gaowei.wechat.menu.pojo.AccessToken;
import edu.fudan.gaowei.wechat.menu.pojo.Button;
import edu.fudan.gaowei.wechat.menu.pojo.CommonButton;
import edu.fudan.gaowei.wechat.menu.pojo.ComplexButton;
import edu.fudan.gaowei.wechat.menu.pojo.Menu;
import edu.fudan.gaowei.wechat.menu.pojo.ViewButton;
import edu.fudan.gaowei.wechat.menu.util.WeixinUtil;

/**
 * �˵���������
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "wx51c4377187920084";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "72409676bf37ac76a3e6573729526b3b";

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// ���ýӿڴ����˵�
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// �жϲ˵��������
			if (0 == result)
				log.info("�˵������ɹ���");
			else
				log.info("�˵�����ʧ�ܣ������룺" + result);
		}
	}

	/**
	 * ��װ�˵�����
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("��ҪK��");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("��Ҫ��Ӱ");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("��Ҫ����");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("��Ҫ����");
		btn14.setType("click");
		btn14.setKey("14");

		ViewButton  btn21 = new ViewButton();
		btn21.setName("��Ƭǽ");
		btn21.setType("view");
		btn21.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat4.html");

		CommonButton btn22 = new CommonButton();
		btn22.setName("����ʷ");
		btn22.setType("click");
		btn22.setKey("22");

		ViewButton  btn23 = new ViewButton();
		btn23.setName("���ְ�");
		btn23.setType("view");
		btn23.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat3.html");

	


		ViewButton  btn31 = new ViewButton();
		btn31.setName("ίԱ���");
		btn31.setType("view");
		btn31.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat5.html");

		ViewButton  btn32 = new ViewButton();
		btn32.setName("����ѡ��");
		btn32.setType("view");
		btn32.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat1.html");

		ViewButton btn33 = new ViewButton();
		btn33.setName("��������");
		btn33.setType("view");
		btn33.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat2.html");

		CommonButton btn34 = new CommonButton();
		btn34.setName("��ı����");
		btn34.setType("click");
		btn34.setKey("34");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("���»");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("���ڱ���");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23,});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("����");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn34 });

		/**
		 * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br>
		 * 
		 * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br>
		 * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}