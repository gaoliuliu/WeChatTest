package edu.fudan.gaowei.wechat.menu.util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fudan.gaowei.wechat.menu.pojo.CUser;

import edu.fudan.gaowei.wechat.thread.TokenThread;

public class CUserUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	
	public static String user_get_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	/**
	 * 获取用户
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static CUser getUserName(String openid) {
		//S//tring result = "";
		CUser user = null;

		// 拼装创建菜单的url
		String url = user_get_url.replace("ACCESS_TOKEN", TokenThread.accessToken.getToken()).replace("OPENID", openid);
		// 将菜单对象转换成json字符串
		//String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);

		// 如果请求成功
				if (null != jsonObject) {
					try {
						user = new CUser();
						user.setSubscribe(jsonObject.getString("subscribe"));
						user.setOpenid(jsonObject.getString("openid"));
						user.setNickname(jsonObject.getString("nickname"));
						user.setSex(jsonObject.getString("sex"));
						user.setLanguage(jsonObject.getString("language"));
						user.setCity(jsonObject.getString("city"));
						user.setProvince(jsonObject.getString("province"));
						user.setCountry(jsonObject.getString("country"));
						user.setHeadimgurl(jsonObject.getString("headimgurl"));
						user.setSubscribe_time(jsonObject.getString("subscribe_time"));
						
					} catch (JSONException e) {
						user = null;
						// 获取token失败
						log.error("获取user失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
					}
				}

		return user;
	}
}
