package com.example.UserService.Service;

import com.example.UserService.Repository.SessionRepository;
import com.example.UserService.Repository.UserRepository;
import com.example.UserService.models.Session;
import com.example.UserService.models.SessionStatus;
import com.example.UserService.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
   private SessionRepository sessionRepository;
    @Autowired
    private SecretKey secret;

    public User signUp(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            User saveduser = userRepository.save(user);
            return saveduser;
        }
        return userOptional.get();
    }
    public Pair<User, MultiValueMap<String,String>> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();

        if (!bCryptPasswordEncoder.matches(password, user.getPassword()))  {
            return null;
        }

//        String message = "{\n" +
//                "   \"email\": \"anurag@scaler.com\",\n" +
//                "   \"roles\": [\n" +
//                "      \"instructor\",\n" +
//                "      \"buddy\"\n" +
//                "   ],\n" +
//                "   \"expirationDate\": \"2ndApril2024\"\n" +
//                "}";
//
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        Map<String,Object> jwtData = new HashMap<>();
        jwtData.put("email",user.getEmail());
        jwtData.put("roles",user.getRoles());
        long nowInMillis = System.currentTimeMillis();
        jwtData.put("expiryTime",new Date(nowInMillis+100000000));
        jwtData.put("createdAt",new Date(nowInMillis));

        MacAlgorithm algorithm = Jwts.SIG.HS256;
        SecretKey secret = algorithm.key().build();

        String token = Jwts.builder().claims(jwtData).signWith(secret).compact();



            Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setUser(user);
        session.setToken(token);
        session.setExpiringAt(new Date(nowInMillis+10000));
        sessionRepository.save(session);






        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE,token);
        return new Pair<User, MultiValueMap<String,String>>(user,headers);

    }
    public Boolean validateToken(String token, Long id) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token,id);

        if(optionalSession.isEmpty()) {
            System.out.println("No Token or User found");
            return false;
        }

        Session session = optionalSession.get();
        String storedToken = session.getToken();

        JwtParser jwtParser = Jwts.parser().verifyWith(secret).build();
        Claims claims = jwtParser.parseSignedClaims(storedToken).getPayload();
        System.out.println(claims);

        long nowInMillis = System.currentTimeMillis();
        long tokenExpiry = (Long)claims.get("expiryTime");

        if(nowInMillis > tokenExpiry) {
            System.out.println(nowInMillis);
            System.out.println(tokenExpiry);
            System.out.println("Token has expired");
            return false;
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) {
            return false;
        }

        String email = optionalUser.get().getEmail();

        if(!email.equals(claims.get("email"))) {
            System.out.println(email);
            System.out.println(claims.get("email"));
            System.out.println("User doesn't match");
            return false;
        }

        return true;
    }


}