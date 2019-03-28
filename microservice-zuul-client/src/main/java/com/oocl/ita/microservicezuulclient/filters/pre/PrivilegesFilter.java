package com.oocl.ita.microservicezuulclient.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.oocl.ita.microservicezuulclient.controllers.LoginController;
import com.oocl.ita.microservicezuulclient.services.LoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class PrivilegesFilter extends ZuulFilter {

    @Autowired
    private LoginService loginService;

    private static String sessionKey = "trd_session";
    private static String user_id = "userId";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // url /login 的不过滤
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        return !("/login".equals(request.getRequestURI()) ||
                request.getRequestURI().contains("/type"));
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        Map<String, String[]> requestParams = request.getParameterMap();
        String[] trd_sessions = requestParams.get(sessionKey);
        Map<String, List<String>> newRequestParams ;
        if(trd_sessions == null || trd_sessions.length == 0){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            return null;
        } else {
            String userId = loginService.getUserIdBySessionKey(trd_sessions[0]);
            newRequestParams = addUserIdToContext(context, userId, requestParams);
            context.setRequestQueryParams(newRequestParams);
        }
        return null;
    }

    private Map<String, List<String>> addUserIdToContext(RequestContext context, String userId, Map<String, String[]> requestParams) {
        Map<String, List<String>> newRequestParams = new HashMap<>();
        if(StringUtils.isEmpty(userId)){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            return newRequestParams;
        }
        for(Map.Entry<String, String[]> entry : requestParams.entrySet()){
            if(entry.getKey().equals(sessionKey)) continue;
            newRequestParams.put(entry.getKey(), Arrays.asList(entry.getValue()));
        }
        newRequestParams.put(user_id, Collections.singletonList(userId));
        return newRequestParams;
    }
}
