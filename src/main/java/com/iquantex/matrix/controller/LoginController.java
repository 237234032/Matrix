package com.iquantex.matrix.controller;

import com.alibaba.fastjson.JSON;
import com.diqiumofang.tool.StringEx;
import com.iquantex.matrix.service.base.CacheService;
import com.iquantex.matrix.utils.ApplicationAutoConfig;
import com.iquantex.matrix.utils.Results;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Map;


/**
 * @author : zn-Cabigail(1163727353@qq.com)
 * @Description :登录的controller，配置了集成cas和前端交互的api
 * @date :  2018/4/9 上午11:30
 */
@Controller
public class LoginController {

    final static Logger log = org.slf4j.LoggerFactory.getLogger(LoginController.class);

    @Resource
    private CacheService cacheService;

    @Resource
    private ApplicationAutoConfig applicationAutoConfig;

    @RequestMapping(value = {"/", "/hello"})
    @ResponseBody
    public String hello() {
        return "hello world";
    }


    @RequestMapping("/_logout")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        try {
            String service = request.getParameter("backurl");
            if (StringEx.isEmpty(service)) throw new Exception("target backurl is empty");

            HttpSession session = request.getSession();
            String sessionid = session.getId();
            session.invalidate();

            Object userByToken = cacheService.getUserByToken(sessionid);
            if (!StringEx.isEmpty(userByToken)) {
                Map<String, Object> user = (Map<String, Object>) userByToken;
                String username = (String) user.get("loginName");
                cacheService.removeUserByToken(sessionid);
                cacheService.removeTokenByUsername(username);
            }

            return "redirect:" + applicationAutoConfig.getCasServerLogoutUrl() + "?service=" + URLEncoder.encode(service, "UTF-8");
        } catch (Exception e) {
            try {

                response.getWriter().write(JSON.toJSONString(Results.createSystemErrorResult(e),true));
            } catch (Exception e1) {
            }
            return null;
        }
    }

    @RequestMapping("/_getSigutature")
    public String getToken(HttpServletRequest request, HttpServletResponse response) {

        try {
            String remoteUser = request.getRemoteUser();
            String service = request.getParameter("backurl");
            if (StringEx.isEmpty(service)) throw new Exception("target backurl is empty");
            String sessionid = request.getSession().getId();

            cacheService.saveTokenByUsername(remoteUser, sessionid);
            System.out.println(" cacheService.saveTokenByUsername(remoteUser, sessionid) ： " + sessionid + " || get : " + cacheService.getTokenByUserName(remoteUser));
            //这里获取到根据用户name获取到用户信息，并缓存起来
            //User和Employee中相同的属性使用加前缀，如id，Map中存放User的id为uid,存放Employee的id为eid,不同的属性按各自实体类中字段名称
//            Map<String, Object> user = userService.selectByUserName(remoteUser);

            cacheService.saveUserByToken(sessionid, remoteUser);
            System.out.println(" cacheService.saveUserByToken(sessionid,user) :" + remoteUser + " || get : " + cacheService.getUserByToken(sessionid));
            return "redirect:" + service + "?signature=" + sessionid;
        } catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(Results.createSystemErrorResult(e),true));
            } catch (Exception e1) {
            }
            return null;
        }
    }

    //aouty：根据token从缓存中获取对应的用户员工信息
    @RequestMapping("/getUserEmployee/{token}")
    @ResponseBody
    public Results getUserEmployeeMapByToken(@PathVariable("token") String token) {
        Results result;
        try {
            if (StringEx.isEmpty(token)) throw new Exception("token is empty");
            Object user = cacheService.getUserByToken(token);
            if(StringEx.isEmpty(user)) throw new  Exception("user is empty");
            result = Results.createSuccessResult(user);
        } catch (Exception e) {
            result = Results.createSystemErrorResult(e);
        }
        return result;
    }

}
