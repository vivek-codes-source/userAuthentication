package com.example.UserService.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

@Entity
@Setter
@Getter
public class Session extends BaseModel {
    private String token;

    private
    Date expiringAt;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
 private SessionStatus sessionStatus;
}