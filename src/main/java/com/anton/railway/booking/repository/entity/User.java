package com.anton.railway.booking.repository.entity;

import com.anton.railway.booking.repository.entity.enums.AccountStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDateTime dateJoined;
    private Long cardNumber;
    private String username;
    private String password;
    private AccountStatus accountStatus;
}
