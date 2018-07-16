package com.zhang.tximpl.service;

import com.zhang.tximpl.dao.DButils;
import com.zhang.tximpl.dao.UserDao;
import com.zhang.tximpl.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseClass{

    @Autowired
    private UserDao userDao;

    //@Transactional
    public void createUser(User user) {
       try{
           DButils.beginTransaction();
           userDao.insert( user );
           th();
           DButils.commitTransaction();
       }catch (Exception e){
           DButils.rollbackTransaction();
       }
    }

    private void th() {
        throw new RuntimeException("出错了");
    }
}
