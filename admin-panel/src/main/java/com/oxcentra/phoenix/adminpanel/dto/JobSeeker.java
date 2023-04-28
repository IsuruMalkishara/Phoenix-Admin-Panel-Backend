package com.oxcentra.phoenix.adminpanel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker {
    private int id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String cv;

    private String password;

    private String profilePicture;

    private String college;

    private String degree;

    private List<Certification> certification;

    private String country;

    private String district;

    private String experience;

    private List<Languages> languages;

    private String linkdin;

    private String position;

    private List<Skills> skills;

    private List<CertificateImage> certificate;
}
