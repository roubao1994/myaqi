package nju.software.utils.mybatis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lulei on 16/3/2.
 * SQL 格式化工具, 将SQL语句中的"换行""制表符"替换为空格
 */
public class SqlFormat {
    private static Pattern SQL_PATTERN = Pattern.compile("\\s{2,}|\t|\r|\n");

    public static String format(String sql) {
        String result = "";
        if (null != sql && !sql.isEmpty()) {
            Matcher matcher = SQL_PATTERN.matcher(sql);
            result = matcher.replaceAll(" ");
        }
        return result;
    }
}
