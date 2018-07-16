package com.zhang.tximpl.web;

import com.zhang.tximpl.domain.User;
import com.zhang.tximpl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/createUser")
    @ResponseBody
    public String createUser( User user){
        userService.createUser( user );
        return "success";
    }
}
