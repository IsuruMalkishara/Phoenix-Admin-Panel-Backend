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
    private String name;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;
}
