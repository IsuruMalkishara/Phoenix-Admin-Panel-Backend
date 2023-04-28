package com.oxcentra.phoenix.adminpanel.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private String userName;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;
    private String profilePicture;
    private String userType;
}
