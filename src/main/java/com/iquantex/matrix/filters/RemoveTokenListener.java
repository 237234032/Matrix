package com.iquantex.matrix.filters;


import com.diqiumofang.tool.StringEx;
import com.iquantex.matrix.service.base.CacheService;
import com.iquantex.matrix.utils.SpringBootContextUtil;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 * @author : zn-Cabigail(1163727353@qq.com)
 * @Description :监听cas的session清除，清除后要删除对应的token
 * @date :  2018/4/10 上午10:03
 */
public class RemoveTokenListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Object bean = SpringBootContextUtil.getBean(CacheService.class);
        if (StringEx.isEmpty(bean)) return;
        CacheService service = (CacheService) bean;

        String sessionid = httpSessionEvent.getSession().getId();
        Object userByToken = service.getUserByToken(sessionid);
        if (StringEx.isEmpty(userByToken)) return;

        String username = (String) ((Map<String, Object>) userByToken).get("loginName");
        service.removeTokenByUsername(username);
        service.removeUserByToken(sessionid);
    }
}
