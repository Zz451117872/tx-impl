package com.zhang.tximpl.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DButils {

    public static String username = "root";
    public static String password = "com.gtk.com.xx";
    public static String url = "jdbc:mysql://localhost:3306/tx-impl";
    public static String driverClassName = "com.mysql.jdbc.Driver";
    public static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

    static {
        try{
            Class.forName( driverClassName );
        }catch (Exception e){
            System.out.println("数据库启动类加载失败");
        }
    }

    public static Connection getConnection( ){
        try{
            Connection connection = DriverManager.getConnection( url ,username , password );
            return connection;
        }catch (Exception e){
            System.out.println("数据库启动类加载失败");
            return null;
        }
    }

    public static Connection getConn(){
        Connection connection = connectionThreadLocal.get();
        if( connection != null ){
            return connection;
        }
        connection = getConnection( );
        if( connection == null ){
            throw new RuntimeException("不能获取连接");
        }
        connectionThreadLocal.set( connection );
        return connection;
    }

    public static void beginTransaction(){
        Connection connection = getConn();
        if( connection != null ){
            try{
                connection.setAutoCommit( false );
            }catch (Exception e){
                throw new RuntimeException("设置非自动提交失败");
            }
        }
    }

    public static void commitTransaction(){
        Connection connection = getConn();
        if( connection != null ){
            try{
                connection.commit();
                connection.close();
            }catch (Exception e){
                throw new RuntimeException("提交更新时出错");
            }
        }
    }

    public static void rollbackTransaction(){
        Connection connection = getConn();
        if( connection != null ){
            try{
                connection.rollback();
                connection.close();
            }catch (Exception e){
                throw new RuntimeException("回滚动作时失败");
            }
        }
    }

    public static void main(String[] args){
        Connection connection = DButils.getConnection();
        System.out.println( "connection:"+ connection );
    }
}
