package com.qh.modules.common.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库连接工具类
 * jdbc连接数据库的时候，需要使用数据库的sid_name，而不是数据库的services_name.
 * 而使用plsql连接数据库的时候，只需要数据库的services_name即可，所以修改连接字符串中的services_name 为sid_name.
 * jdbc连接数据库使用sid和service_name的区别
 * 格式一: Oracle JDBC Thin using a ServiceName:
 * jdbc:oracle:thin:@//10.90.130.99:1521/s_rptdb   （s_rptdb是oracle数据库的服务名servicename）
 * 格式二: Oracle JDBC Thin using an SID:
 * jdbc:oracle:thin:@10.90.130.99:1521:s_rptdb2   （s_rptdb2是oracle数据库的实例名SID）
 * 格式三：Oracle JDBC Thin using a TNSName: （不常用）
 * jdbc:oracle:thin:@<TNSName>
 *     Example: jdbc:oracle:thin:@GL       （tnsname要安装oracle客户端，在tnsnames.ora配置）
 * Support for TNSNames was added in the driver release 10.2.0.1
 *
 * Created by Administrator on 2018/8/10.
 */
public class DbUtil {
    /**
     * 数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//找到oracle驱动器所在的类
            String url="jdbc:oracle:thin:@//10.1.2.21:1521/ywkdb"; //URL地址
            String username="usr_kjxyqj";
            String password="kjxyqj2018";
            conn= DriverManager.getConnection(url, username, password);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     * @param connect Connection对象
     * @param pstmt PreparedStatement对象
     * @param rs ResultSet对象
     */
    public static void close(Connection connect, PreparedStatement pstmt, ResultSet rs){
        if(rs !=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pstmt !=null){
            try {
                pstmt.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ResultSet 转换成Map
     * @param rs
     * @return
     * @throws SQLException
     */
    public static Map<String, String> getResultMap(ResultSet rs)
            throws SQLException {
        Map<String, String> hm = new HashMap<String, String>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= count; i++) {
                String key = rsmd.getColumnLabel(i);
                String value = rs.getString(i);
                hm.put(key, value);
            }
        }
        return hm;
    }

    /**
     * 将ResultSet结果集转换为List
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static List convertList(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        while (rs.next()) {
            Map rowData = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }
}
