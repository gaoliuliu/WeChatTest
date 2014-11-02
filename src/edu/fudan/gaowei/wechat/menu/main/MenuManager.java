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
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx51c4377187920084";
		// 第三方用户唯一凭证密钥
		String appSecret = "72409676bf37ac76a3e6573729526b3b";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("我要K歌");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("我要摄影");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("我要桌游");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("我要密室");
		btn14.setType("click");
		btn14.setKey("14");

		ViewButton  btn21 = new ViewButton();
		btn21.setName("照片墙");
		btn21.setType("view");
		btn21.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat4.html");

		CommonButton btn22 = new CommonButton();
		btn22.setName("黑历史");
		btn22.setType("click");
		btn22.setKey("22");

		ViewButton  btn23 = new ViewButton();
		btn23.setName("积分榜");
		btn23.setType("view");
		btn23.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat3.html");

	


		ViewButton  btn31 = new ViewButton();
		btn31.setName("委员风采");
		btn31.setType("view");
		btn31.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat5.html");

		ViewButton  btn32 = new ViewButton();
		btn32.setName("优秀选手");
		btn32.setType("view");
		btn32.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat1.html");

		ViewButton btn33 = new ViewButton();
		btn33.setName("加入我们");
		btn33.setType("view");
		btn33.setUrl("http://highjoin010.vicp.cc/WeChatTest/page/wechat2.html");

		CommonButton btn34 = new CommonButton();
		btn34.setName("出谋划策");
		btn34.setType("click");
		btn34.setKey("34");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("最新活动");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("往期比赛");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23,});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn34 });

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}