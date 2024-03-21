package com.bookexchange.service;

import com.bookexchange.controller.UserController;
import com.bookexchange.domain.User;
import com.bookexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository userRepo;

    public User addUser(User user) {
        User createdUser = userRepo.save(user);
        LOGGER.log(Level.INFO,"User is Created");
        return createdUser;
    }

    public User getUser(Long userId) {
        return userRepo.findById(userId).get();
    }

    public User updateUser(User user) {
        userRepo.deleteById(user.getUserId());
        return userRepo.save(user);

    }

    public int getRewardPoints(Long userId) {
        return userRepo.getRewardPoints(userId);
    }

    public void updateRewardPoints(int userRP, Long userId) {
         userRepo.updateRewardPoints(userRP,userId);
    }

    public void updateVerificationStatus(Long userId, boolean isVerified) {
        userRepo.updateVerificationStatus(isVerified,userId);
    }
}
