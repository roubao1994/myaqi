package nju.software.utils.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * Created by lulei on 16/3/2.
 * SQL监控插件,可以监控SQL执行的时间以及SQL执行的语句
 * 在mybatis-config.xml中配置如下:
 * <plugin interceptor="nju.software.utils.mybatis.SqlPrintPlugin">
 * <property name="show_sql>true</property>
 * </plugin>
 */

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class SqlPrintPlugin implements Interceptor {
    // 配置文件中的<show_sql> property
    private static final String SHOW_SQL_PROP = "show_sql";

    private boolean showSql = false;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object resultValue = null;

        long start = System.currentTimeMillis();
        try {
            resultValue = invocation.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            long time = (end - start);
            if (showSql) {
                MybatisExecuteSqlFormat sqlFormat = new MybatisExecuteSqlFormat();
                System.out.println(sqlFormat.fomatSql(invocation, time));
            }
        }
        return resultValue;
    }

    /**
     * set showSql value from xlm configuration
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        if (properties == null) {
            return;
        }
        if (properties.containsKey(SHOW_SQL_PROP)) {
            String value = properties.getProperty(SHOW_SQL_PROP);
            if (Boolean.TRUE.toString().equals(value))
                showSql = true;
        }
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }
}
