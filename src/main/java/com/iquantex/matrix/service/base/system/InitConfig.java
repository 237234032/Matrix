package com.iquantex.matrix.service.base.system;

import com.iquantex.matrix.cache.CacheClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description :程序启动事加载
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/3/20 18:16
 */
@Component
public class InitConfig implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(InitConfig.class);
    @Resource
    private DataBaseService dataBaseService;

    @Override
    public void run(String... args) throws Exception {
        log.info("[SYSTEM] : service init ...");
        log.info("[SYSTEM] : Database init ...");
        dataBaseService.init();
        log.info("[SYSTEM] : Database init finished.");
        log.info("[SYSTEM] : cache service init ...");
        CacheClient.getInstance().init();
        log.info("[SYSTEM] : cache service init finished.");
        log.info("[SYSTEM] : service init finished.");
    }
}
