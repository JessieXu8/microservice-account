package com.ita.microserviceuserclient.microserviceuserclient.controllers;

import com.ita.microserviceuserclient.microserviceuserclient.entities.User;
import com.ita.microserviceuserclient.microserviceuserclient.po.UserInfo;
import com.ita.microserviceuserclient.microserviceuserclient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "")
    public ResponseEntity<UserInfo> getUserInfo(@RequestParam String openId) {
        HttpStatus httpStatus = HttpStatus.OK;
        UserInfo userInfo = userService.getUserInfo(openId);
        return new ResponseEntity<>(userInfo, httpStatus);
    }

    @GetMapping(path = "/{userId}")
    public User findUserById(@PathVariable String userId) {
        return userService.findUserById(userId);
    }

//    @PostMapping(path = "")
//    public ResponseEntity updateUserInfo(@RequestParam String trd_session, @RequestBody User user) {
//        HttpStatus httpStatus = HttpStatus.NO_CONTENT;
//        String openId = loginService.getOpenId(trd_session);
//        if(openId == null){
//            httpStatus = HttpStatus.UNAUTHORIZED;
//        }
//        userService.updateUserInfo(openId, user);
//        return new ResponseEntity(httpStatus);
//    }
}
