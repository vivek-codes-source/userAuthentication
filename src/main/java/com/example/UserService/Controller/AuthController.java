package com.example.UserService.Controller;

import ch.qos.logback.core.joran.sanity.Pair;
import com.example.UserService.Dtos.LoginRequestDto;
import com.example.UserService.Dtos.SignupRequestDto;
import com.example.UserService.Dtos.UserDto;
import com.example.UserService.Service.AuthService;
import com.example.UserService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {

           User user = authService.signUp(signupRequestDto.getEmail(), signupRequestDto.getPassword());
           UserDto userDto = getUserDto(user);
           return new ResponseEntity<>(userDto, HttpStatus.OK);



    }
    @PostMapping("/auth/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            User user = authService.signUp(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            UserDto userDto = getUserDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        }



//    @PostMapping("/auth/validate")
//    public ResponseEntity<Boolean> validateToken(@RequestBody ValidateRequestDto validateRequestDto) {
//        Boolean isValid = authService.validateToken(validateRequestDto.getToken(), validateRequestDto.getId());
//        return new ResponseEntity<>(isValid,HttpStatus.OK);
//    }
//
    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        //userDto.setRoles(user.getRoles());
        return userDto;
    }
}
