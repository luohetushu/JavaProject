package com.project.basic;

/**
 * JDBC：Java Database Connectivity Java 数据库连接
 * java.sql.*
 */
public class BasicJDBC {

    /**
     * JDBC 数据库访问形式：
     *     1、JDBC-ODBC 桥连接：程序 -> JDBC -> ODBC -> 数据库
     *     2、JDBC 本地连接：程序 -> JDBC -> 本地数据库
     *     3、JDBC 网络连接：程序 -> JDBC -> 网络数据库（通过特定的网路协议连接：根据 ip 与端口号）
     *     4、JDBC 协议连接：通过编写特定协议实现数据库连接
     * JDBC 设计实际是工厂类的处理机制，java.sql.DriverManager 就是一个工厂，不同的数据库厂商根据 JDBC 提供的标准（接口）实现各自的数据库处理操作
     */

}
