package com.xyc;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import org.junit.jupiter.api.Test;

public class Mysql {
    private static ConnectionSource source = ConnectionSourceHelper.getSimple("com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://127.0.0.1:3306/emp?characterEncoding=UTF-8&transformedBitIsBoolean=true&allowMultiQueries=true&serverTimezone=GMT&useSSL=false",
            "root", "Xyc.123.456");
    private static DBStyle mysql = new MySqlStyle();
    // sql语句放在classpagth的/sql 目录下
    private static SQLLoader loader = new ClasspathLoader("/sql");
    // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
    private static UnderlinedNameConversion nc = new UnderlinedNameConversion();

    // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况

    private static SQLManager sqlManager1 = new SQLManager(mysql, loader, source, nc, new Interceptor[]{});
    private static SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});

    public static SQLManager getSqlManager(int i) {
        if (i == 1) {
            return sqlManager1;
        }
        return sqlManager;
    }

    @Test
    public void generalEntity(){
        SQLManager sqlManager = getSqlManager(1);
        try {
            getSqlManager(1).genALL("com.xyc.temp", new GenConfig(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}