package nju.software.utils.mybatis;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by lulei on 16/3/2.
 */
public class MybatisExecuteSqlFormat extends SqlFormat{
    public String fomatSql(Invocation invocation, long time ) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();

        return getSql(configuration, boundSql, sqlId, time);

    }

    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time){
        String sql = pieceSql(configuration, boundSql);
        return String.format("USE TIME:%s(ms)\n sqlId:%s\n SQL:%s\n", time, sqlId, sql);
    }

    /**
     * 读取configuration, 拼接带参数的SQL
     * @param configuration
     * @param boundSql
     * @return
     */
    public static String pieceSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]", " ");

        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }

        return SqlFormat.format(sql);
    }

    /**
     * 将参数转为String形式
     * @param object
     * @return
     */
    private static String getParameterValue(Object object) {
        String value;
        if (object instanceof String) {
            value = "'" + object.toString() + "'";
        } else if (object instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (object != null) {
                value = object.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

}
