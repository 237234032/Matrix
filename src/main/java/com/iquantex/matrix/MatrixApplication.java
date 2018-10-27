package com.iquantex.matrix;

import com.iquantex.matrix.utils.SpringBootContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Description :启动服务
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 上午10:00
 */
@SpringBootApplication
@EnableTransactionManagement/**如果mybatis中service实现类中加入事务注解，需要此处添加该注解 **/
@MapperScan(basePackages = "com.iquantex.matrix.mapper") /**不建议在mapper.java上加注解@Mapper**/
public class MatrixApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

        ConfigurableApplicationContext app = SpringApplication.run(MatrixApplication.class, args);
        /**将app注入到上下文util中**/
        SpringBootContextUtil.setConfigurableApplicationContext(app);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        try
        {
            InputStream in = MatrixApplication.class.getClassLoader().getResourceAsStream("application.properties");

            Properties properties = new Properties();
            properties.load(in);
            SpringBootContextUtil.setProperties(properties);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
         return application.sources(MatrixApplication.class);
    }


}

