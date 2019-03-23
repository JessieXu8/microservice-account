package com.oocl.ita.microservicezuulclient.services;

import com.oocl.ita.microservicezuulclient.utils.CacheUtil;
import com.oocl.ita.microservicezuulclient.utils.StatusUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;

@Service("loginService")
public class LoginService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    private String app_secret = "86db1e5b9cc0d727612a8af80ee0eb7a";
    private String app_id = "wx43a6e59333e94db9";
    private String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    private String error_code = "errcode";  // 0 成功  // -1 系统繁忙，稍后再试 // 40029 code 无效  //  45011 频率限制，每个用户每分钟100次
    private String errro_message = "errmsg";
    private String open_id = "openid";
    private String session_key = "session_key";
    String union_id = "unionid";

    public boolean checkLogin(String loginStatus) {
        String value = CacheUtil.getInstance().get(loginStatus);
        return StringUtils.isNotEmpty(value);
    }

    public String weChatLogin(String code) {
        String url = code2SessionUrl.replace("APPID", app_id).replace("SECRET", app_secret).replace("JSCODE", code);
        JSONObject resultJSON = JSONObject.fromObject(restTemplate.getForObject(url, String.class));
        if (resultJSON.get(error_code) != null && (int) resultJSON.getLong(error_code) > 0) {
            return "error" + "#" + resultJSON.get(errro_message);
        } else {
            String openId = resultJSON.getString(open_id);
            String sessionKey = resultJSON.getString(session_key);
//            String unionId = resultJSON.getString(union_id);  // may be used later
            String loginStatus = StatusUtil.getLoginStatus(openId, sessionKey);
            userService.createIfNotExist(openId);
            CacheUtil.getInstance().put(loginStatus, openId, sessionKey);
            return loginStatus;
        }
    }

    public String getUserIdBySessionKey(String trd_session) {
        return CacheUtil.getInstance().getOpenId(trd_session);
    }
}
