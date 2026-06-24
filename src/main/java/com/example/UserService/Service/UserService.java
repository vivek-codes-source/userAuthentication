package com.example.UserService.Service;

import com.example.UserService.Repository.UserRepository;
import com.example.UserService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User GetDetail(long id) {
        return userRepository.findById(id).get();

    }

}
