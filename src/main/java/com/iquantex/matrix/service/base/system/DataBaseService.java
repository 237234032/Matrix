package com.iquantex.matrix.service.base.system;

import com.diqiumofang.tool.CalendarUtil;
import com.diqiumofang.tool.StringEx;
import com.iquantex.matrix.constant.TableConstant;
import com.iquantex.matrix.utils.ApplicationAutoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @Description :数据初始化service
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/8 下午2:37
 */
@Service
public class DataBaseService
{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private ApplicationAutoConfig applicationAutoConfig;

    public void init()
    {
        try {
            this.createDatabase();
            this.createTables();
            String ym = getYm();
            this.createTables(ym);
            this.initData();

        } catch (SQLException e) {
            log.info("[DATABASE] error : {}", e);
        }
    }

    private String createDatabase()  throws  SQLException
    {

        String schema = applicationAutoConfig.getJdbcDatabase();

        StringBuffer ddl = new StringBuffer();
        ddl.append(" create database if not exists " + schema);
        ddl.append(" DEFAULT CHARSET utf8 COLLATE utf8_general_ci;");

        this.executeDDL(ddl.toString());

        log.info("[DATABASE] : touch database {}", schema);
        return schema;
    }

    private void initData()
    {
    }

    private void createTables() throws SQLException
    {
        //bookDemo
        this.createBookTable();
    }

    private String createBookTable() throws SQLException
    {
        String tablename = TableConstant.TABLE_BOOK;

        StringBuffer ddl = new StringBuffer();
        ddl.append(" create table if not exists " + tablename + " (\n");
        ddl.append(" `uuid` varchar(36) DEFAULT '',\n");
        ddl.append(" `book_name` varchar(36) DEFAULT '',\n");
        ddl.append(" `book_price` double(11,2) DEFAULT 0,\n");
        ddl.append(" `createtime` varchar(20) DEFAULT '',\n");

//        ddl.append(" unique key `" + tablename + "_key_index` (`increase_key`),\n");
        ddl.append(" PRIMARY KEY (`uuid`)\n");
        ddl.append(" );");

        this.executeDDL(ddl.toString());
        log.info("[TABLE] : touch table {}", tablename);
        return tablename;
    }

    private void executeDDL(String sql) throws  SQLException
    {
        this.validateSql(sql);
        //log.debug("[JDBC] : {}",sql);
        this.jdbcTemplate.execute(sql);
    }


    public String getYm()
    {
        String[] str = CalendarUtil.getShortString().split("\\-|\\ |\\:");
        return str[0] + str[1];
    }

    private void createTables(String ym)
    {
    }


    private void validateSql(String sql) throws SQLException
    {
        if(StringEx.isEmpty(sql)) throw new SQLException("sql is empty");
    }
}
