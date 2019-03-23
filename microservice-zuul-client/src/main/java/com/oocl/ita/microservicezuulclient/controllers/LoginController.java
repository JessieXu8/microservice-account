package com.oocl.ita.microservicezuulclient.controllers;

import com.oocl.ita.microservicezuulclient.dto.Response;
import com.oocl.ita.microservicezuulclient.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response weChatLogin(@RequestParam Map<String, String> params) {
        String loginStatus = params.get("trd_session");
        Response response = new Response();
        if(loginStatus != null) {
            boolean isLogin = loginService.checkLogin(loginStatus);
            response.setMsg(isLogin ? "Login" : "noLogin");
            response.setSuccess(isLogin);
        } else {
            String code = params.get("code");
            String result = loginService.weChatLogin(code);
            boolean isSuccess = !result.startsWith("error");
            response.setSuccess(isSuccess);
            Map<String, Object> map = new HashMap<>();
            if(isSuccess){
                map.put("trd_session", result);
            }else {
                map.put("error_msg",result.substring(6));
            }
            response.setObj(map);
        }
        return response;
    }
}
