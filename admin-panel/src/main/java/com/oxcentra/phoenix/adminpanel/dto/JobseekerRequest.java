package com.oxcentra.phoenix.adminpanel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobseekerRequest {
    private Integer id;

    private Vacancy vacancy;

    private JobSeeker jobSeeker;
}
