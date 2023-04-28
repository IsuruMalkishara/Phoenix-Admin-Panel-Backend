package com.oxcentra.phoenix.adminpanel.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    private String userName;
    private String password;
}
