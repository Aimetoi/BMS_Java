package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: Aime-toi
 * @Project: DBUtil
 * @Version: 1.0
 * @Date: 2020-07-08 13:53
 * @Description:
 **/
public class DBUtil {

    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "db_bms";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "0103";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s",
                ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }
}
