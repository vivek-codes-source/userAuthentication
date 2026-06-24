package com.example.UserService.Controller;

import com.example.UserService.Dtos.UserDto;
import com.example.UserService.Service.UserService;
import com.example.UserService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")   // <-- path variable
    public UserDto getUserDetails(@PathVariable Long id) {

        System.out.println(id);

        User user = userService.GetDetail(id);

        return getUserDto(user);
    }

    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
