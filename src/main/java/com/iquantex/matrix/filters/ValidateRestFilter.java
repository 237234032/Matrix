package com.iquantex.matrix.filters;

import com.alibaba.fastjson.JSON;
import com.diqiumofang.tool.ClassInfo;
import com.diqiumofang.tool.HttpEx;
import com.diqiumofang.tool.StringEx;
import com.iquantex.matrix.constant.Constant;
import com.iquantex.matrix.constant.RegexpConstant;
import com.iquantex.matrix.service.base.CacheService;
import com.iquantex.matrix.utils.ApplicationAutoConfig;
import com.iquantex.matrix.utils.Results;
import com.iquantex.matrix.utils.SpringBootContextUtil;
import org.jasig.cas.client.util.AbstractConfigurationFilter;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @Description :设置ip过滤，后期可以追加其他过滤
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/3/21 下午9:41
 */

@PropertySource("classpath:application.properties")
public class ValidateRestFilter extends AbstractConfigurationFilter {

    final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ValidateRestFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        request.setCharacterEncoding(Constant.ENCODING);
        Results ret = null;
        HttpEx httpEx = new HttpEx(request,response);
        try
        {
            log.debug("[FILTER] : ip : {} url : [{}]{}",httpEx.getRemoteIp(), httpEx.getMethod(),httpEx.getRequestURL());
             this.validateIp(httpEx);
            // this.validateToken(httpEx);
            chain.doFilter(request, response);
        }
        catch(Exception e)
        {
            ret = Results.createSystemErrorResult(e);
            PrintWriter outPrint = response.getWriter();
            outPrint.print(JSON.toJSONString(ret,true));
            outPrint.close();
        }

        if(ret != null)log.info("[FILTER] : error : {}",JSON.toJSONString(ret,true));
    }

    /**
     * 验证token存在
     * @param httpEx
     * @throws Exception
     */
    private void validateToken(HttpEx httpEx) throws  Exception
    {
        String func = httpEx.getRequestURL().replaceAll(httpEx.getWebPath(),"");
        if(func.matches(RegexpConstant.FILTER_PASS_URL)) return;

        String token = httpEx.getRequestHeader("signature");

        ApplicationAutoConfig config = getConfig();
        if(StringEx.isEmpty(config)) throw  this.getInjectBeanFailureRessult(" inject ApplicationAutoConfig failed ");
        String defaultPassSigurature = config.getDefaultPassSigurature();
        String method = httpEx.getMethod();

        if("OPTIONS".equals(method))  return;
        if(StringEx.isEmpty(token)) throw this.getSignatureEmptyErrorResult("signature is empty in header");
        if(defaultPassSigurature.equals(token)) return;

        Object bean = SpringBootContextUtil.getBean(CacheService.class);
        if(StringEx.isEmpty(bean)) return;
        CacheService service = (CacheService)bean;
        Object userByToken = service.getUserByToken(token);
        System.out.println("fileter : token : "+ token + " || user :" + userByToken);
        if(StringEx.isEmpty(userByToken)) throw  this.getSignatureValidateErrorResult("signature validate error or is unused, please relogin");
    }

    /**
     * 验证 ip 是否通过
     * @param httpEx
     * @return
     * @throws IOException
     */
    private void validateIp(HttpEx httpEx) throws Exception
    {
        String ip = httpEx.getRemoteIp();

        if(!this.validateIP(ip))
            throw this.getIpDeniedAccessErrorResult("Ip denied access");
    }

    private boolean validateIP(String ip) throws  Exception
    {
        ApplicationAutoConfig config = getConfig();
        if(StringEx.isEmpty(config)) throw  this.getInjectBeanFailureRessult(" inject ApplicationAutoConfig failed ");
        String accessIps = config.getAccessIps();
        if(StringEx.isEmpty(accessIps)) return true;
        if(ip.contains(accessIps)) return true;
        return false;
    }

    private Exception getIpDeniedAccessErrorResult(String errorDesc){
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position,errorDesc);
    }

    private Exception getSignatureEmptyErrorResult(String errorDesc){
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position,errorDesc);
    }

    private Exception getSignatureValidateErrorResult(String errorDesc){
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position,errorDesc);
    }

    private Exception getInjectBeanFailureRessult(String errorDesc){
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position,errorDesc);
    }


    private ApplicationAutoConfig getConfig ()
    {
        Object bean = SpringBootContextUtil.getBean(ApplicationAutoConfig.class);
        if(!StringEx.isEmpty(bean)) return (ApplicationAutoConfig)bean;

        Properties properties = SpringBootContextUtil.getProperties();
        if(StringEx.isEmpty(properties)) return null;

        ApplicationAutoConfig applicationAutoConfig = new ApplicationAutoConfig();

        applicationAutoConfig.setAccessIps(properties.getProperty("ACCESS_IPS"));
        applicationAutoConfig.setCasServerLoginUrl(properties.getProperty("cas.server.login.url"));
        applicationAutoConfig.setCasServerLogoutUrl(properties.getProperty("cas.server.logout.url"));
        applicationAutoConfig.setCasServerUrl(properties.getProperty("cas.server.url"));
        applicationAutoConfig.setDefaultPassSigurature(properties.getProperty("default.pass.sigurature"));
        applicationAutoConfig.setJdbcDatabase(properties.getProperty("jdbc.database"));
        applicationAutoConfig.setServerUrl(properties.getProperty("server.url"));
        applicationAutoConfig.setDingCorpid(properties.getProperty("ding.corpid"));
        applicationAutoConfig.setDingSecret(properties.getProperty("ding.secret"));
        return applicationAutoConfig;
    }


    @Override
    public void init(FilterConfig filterConfig) {}
    @Override
    public void destroy() {}
}