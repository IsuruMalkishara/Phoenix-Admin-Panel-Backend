package com.oxcentra.phoenix.adminpanel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

    private int id;

    private String name;

    private String address;

    private String email;

    private String phone;

    private String logo;

    private String password;

    private Boolean verification;
}
