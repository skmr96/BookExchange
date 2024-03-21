package com.bookexchange.controller;


import com.bookexchange.domain.User;
import com.bookexchange.service.KafKaProducerService;
import com.bookexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    @Autowired
    private UserService userService;

    @Autowired
    private KafKaProducerService producerService;

    @PostMapping("api/v1/users")
    public ResponseEntity<User> addUser(@RequestBody User userPayload)
    {
        // Validate User data
        User createdUser = userService.addUser(userPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("api/v1/users/{userId}/verifyUser")
    public ResponseEntity verifyUser(@PathVariable Long userId)
    {
        // isValidUser
        String userIdStr = String.valueOf(userId);
        producerService.sendMessage(userIdStr);
        //return "An email has been sent to your mail address. Please follow to verify";
        return ResponseEntity.status(HttpStatus.OK).body("An email has been sent to your mail address. Please follow to verify");
    }

    @PatchMapping ("api/v1/users/{userId}/confirmUser")
    public ResponseEntity confirmUser(@PathVariable Long userId)
    {
        // isValidUser
        userService.updateVerificationStatus(userId, true);
        //return "User has been successfully verified";
        return ResponseEntity.status(HttpStatus.OK).body("User has been successfully verified");

    }

}
