package com.zhang.tximpl.dao;

import com.zhang.tximpl.domain.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDao {

    public void insert(User user) {
        Connection connection = DButils.getConn();
        if( connection == null ){
            throw new RuntimeException(" dao 层获取连接失败");
        }
        String sql = " insert into user(name,password,amount) values( ? ,? ,?)";
        try{
            PreparedStatement ps = connection.prepareStatement( sql );
            ps.setString( 1 , user.getName() );
            ps.setString( 2 , user.getPassword() );
            ps.setInt( 3 , user.getAmount() );
            if (! ps.execute() )
            {
                System.out.println( "成功插入用户：" + user );
            }else {
                System.out.println( "插入用户失败：" + user );
            }
        }catch (Exception e){
            throw new RuntimeException(" dao 层执行命令出错");
        }
    }
}
