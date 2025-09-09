package com.example.UserService.Controller;

import com.example.UserService.Dtos.LoginRequestDto;
import com.example.UserService.Dtos.SignupRequestDto;
import com.example.UserService.Dtos.UserDto;
import com.example.UserService.Service.AuthService;
import com.example.UserService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.antlr.v4.runtime.misc.Pair;

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
            Pair<User, MultiValueMap<String,String>> bodyWithHeaders = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            UserDto userDto = getUserDto(bodyWithHeaders.a);
            return new ResponseEntity<>(userDto, bodyWithHeaders.b, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
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
