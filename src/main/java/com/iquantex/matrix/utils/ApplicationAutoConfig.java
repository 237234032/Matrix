package com.iquantex.matrix.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description :
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/8 下午2:52
 */
@Component
@PropertySource("classpath:application.properties")
public class ApplicationAutoConfig {

    @Value("${jdbc.database}")
    private String jdbcDatabase;

    @Value("${ACCESS_IPS}")
    private String accessIps;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${cas.server.url}")
    private String casServerUrl;

    @Value("${cas.server.login.url}")
    private String casServerLoginUrl;

    @Value("${cas.server.logout.url}")
    private String casServerLogoutUrl;

    @Value("${default.pass.sigurature}")
    private String defaultPassSigurature;

    @Value("${ding.corpid}")
    private String dingCorpid;

    @Value("${ding.secret}")
    private String dingSecret;

    public String getDingCorpid() {
        return dingCorpid;
    }

    public void setDingCorpid(String dingCorpid) {
        this.dingCorpid = dingCorpid;
    }

    public String getDingSecret() {
        return dingSecret;
    }

    public void setDingSecret(String dingSecret) {
        this.dingSecret = dingSecret;
    }

    public String getDefaultPassSigurature() {
        return defaultPassSigurature;
    }

    public void setDefaultPassSigurature(String defaultPassSigurature) {
        this.defaultPassSigurature = defaultPassSigurature;
    }

    public String getCasServerLogoutUrl() {
        return casServerLogoutUrl;
    }

    public void setCasServerLogoutUrl(String casServerLogoutUrl) {
        this.casServerLogoutUrl = casServerLogoutUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getCasServerUrl() {
        return casServerUrl;
    }

    public void setCasServerUrl(String casServerUrl) {
        this.casServerUrl = casServerUrl;
    }

    public String getCasServerLoginUrl() {
        return casServerLoginUrl;
    }

    public void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public String getJdbcDatabase() {
        return jdbcDatabase;
    }

    public void setJdbcDatabase(String jdbcDatabase) {
        this.jdbcDatabase = jdbcDatabase;
    }

    public String getAccessIps() {
        return accessIps;
    }

    public void setAccessIps(String accessIps) {
        this.accessIps = accessIps;
    }
}
