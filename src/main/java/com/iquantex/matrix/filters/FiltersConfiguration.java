package com.iquantex.matrix.filters;

import com.iquantex.matrix.utils.ApplicationAutoConfig;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * @Description :过滤器配置项
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 上午11:26
 */
@Configuration
public class FiltersConfiguration
{

    @Resource
    private ApplicationAutoConfig applicationAutoConfig;
//    /**
//     * cas 的监听，必须第一位
//     * @return
//     */
//    @Bean
//    public ServletListenerRegistrationBean SingleSignOutHttpSessionListener()
//    {
//        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> eventListenerServletListenerRegistrationBean = new ServletListenerRegistrationBean<>( new SingleSignOutHttpSessionListener());
//        eventListenerServletListenerRegistrationBean.setEnabled(true);
//        eventListenerServletListenerRegistrationBean.setOrder(1);
//        return eventListenerServletListenerRegistrationBean;
//    }
//
//    @Bean
//    public ServletListenerRegistrationBean RemoveTokenListener()
//    {
//        ServletListenerRegistrationBean<RemoveTokenListener> eventListenerServletListenerRegistrationBean = new ServletListenerRegistrationBean<>( new RemoveTokenListener());
//        eventListenerServletListenerRegistrationBean.setEnabled(true);
//        eventListenerServletListenerRegistrationBean.setOrder(2);
//        return eventListenerServletListenerRegistrationBean;
//    }
//
//    /**
//     * 单点登出过滤器，用于监听cas server发出的logout请求，接收到了请求之后，会抹掉client端的相关信息(session,cookie),第二位
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean SingleSingOutFilter()
//    {
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean(new SingleSignOutFilter());
//        registrationBean.addInitParameter("casServerUrlPrefix",applicationAutoConfig.getCasServerUrl());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setEnabled(true);
//        registrationBean.setOrder(3);
//        return registrationBean;
//    }
//
//
//    /**
//     * 申请验证授权的过滤器，用于向server端发出授权验证，第三位
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean AuthenticationFilter()
//    {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new AuthenticationFilter());
//        filterFilterRegistrationBean.addInitParameter("casServerLoginUrl",applicationAutoConfig.getCasServerLoginUrl());
//        filterFilterRegistrationBean.addInitParameter("serverName",applicationAutoConfig.getServerUrl());
//        filterFilterRegistrationBean.addUrlPatterns("/_getSigutature");
//        filterFilterRegistrationBean.setOrder(4);
//        filterFilterRegistrationBean.setEnabled(true);
//        return filterFilterRegistrationBean;
//    }
//
//    /**
//     * 验证ticket的过滤器，用于向server 发出st验证，第四位
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean ValidationFilter()
//    {
//        Cas30ProxyReceivingTicketValidationFilter cas30ProxyReceivingTicketValidationFilter = new Cas30ProxyReceivingTicketValidationFilter();
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean(cas30ProxyReceivingTicketValidationFilter);
//        filterFilterRegistrationBean.addInitParameter("casServerUrlPrefix",applicationAutoConfig.getCasServerUrl());
//        filterFilterRegistrationBean.addInitParameter("serverName",applicationAutoConfig.getServerUrl());
//        filterFilterRegistrationBean.addInitParameter("redirectAfterValidation","true");
//        filterFilterRegistrationBean.addInitParameter("useSession","true");
//        filterFilterRegistrationBean.addInitParameter("authn_method","mfa-duo");
//        filterFilterRegistrationBean.addUrlPatterns("/_getSigutature");
//        filterFilterRegistrationBean.setOrder(5);
//        filterFilterRegistrationBean.setEnabled(true);
//        return filterFilterRegistrationBean;
//    }
//
//    /**
//     *该过滤器对HttpServletRequest请求包装， 可通过HttpServletRequest的getRemoteUser()方法获得登录用户的登录名,第五位
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean CASHttpServletRequestWrapperFilter()
//    {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new HttpServletRequestWrapperFilter());
//        filterFilterRegistrationBean.addInitParameter("casServerUrlPrefix",applicationAutoConfig.getCasServerUrl());
//        filterFilterRegistrationBean.addInitParameter("serverName",applicationAutoConfig.getServerUrl());
//        filterFilterRegistrationBean.addInitParameter("redirectAfterValidation","true");
//        filterFilterRegistrationBean.addInitParameter("useSession","true");
//        filterFilterRegistrationBean.addInitParameter("authn_method","mfa-duo");
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        filterFilterRegistrationBean.setOrder(6);
//        filterFilterRegistrationBean.setEnabled(true);
//        return filterFilterRegistrationBean;
//    }
//
//    /**
//     * 该过滤器使得可以通过org.jasig.cas.client.util.AssertionHolder来获取用户的登录名。
//     * 比如AssertionHolder.getAssertion().getPrincipal().getName()。
//     * 这个类把Assertion信息放在ThreadLocal变量中，这样应用程序不在web层也能够获取到当前登录信息  ,第六位
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean AssertionThreadLocalFilter()
//    {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new AssertionThreadLocalFilter());
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        filterFilterRegistrationBean.setOrder(7);
//        filterFilterRegistrationBean.setEnabled(true);
//        return filterFilterRegistrationBean;
//    }
//

    /**
     * 跨域过滤
     * @return
     */
    @Bean
    public FilterRegistrationBean CorsFilter()
    {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new com.iquantex.matrix.filters.CorsFilter());
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(8);
        filterFilterRegistrationBean.setEnabled(true);
        return filterFilterRegistrationBean;
    }
    /**
     * 该过滤器过滤ip
     * @return
     */
    @Bean
    public FilterRegistrationBean ValidateRestFilter()
    {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new ValidateRestFilter());
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(9);
        filterFilterRegistrationBean.setEnabled(true);
        return filterFilterRegistrationBean;
    }

}
