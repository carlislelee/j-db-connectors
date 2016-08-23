/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lizhaoxi
 */
public class MysqlDemo {

    public static Connection getConnection(String host, int port, String username, String password, String db) {

        String connStr = "jdbc:mysql://"
                + host + ":" + String.valueOf(port) + "/" + db
                + "?user=" + username + "&password=" + password
                + "&useUnicode=true&characterEncoding=UTF8";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(connStr);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement("");
            CallableStatement cstmt = conn.prepareCall("");

        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        String host = "10.119.121.237";
        int port  = 3306;
        String user = "tour";
        String passwd = "tour1234";
        String db = "adfenxi1";
        Connection conn = getConnection(host, port, user, passwd, db);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("show tables");
        System.out.println("result set size:"+rs.toString());
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        
    }
}
