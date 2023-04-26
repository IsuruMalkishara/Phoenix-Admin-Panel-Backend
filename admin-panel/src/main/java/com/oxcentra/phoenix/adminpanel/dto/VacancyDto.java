package com.oxcentra.phoenix.adminpanel.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {
    private Integer id;

    private String title;

    private String employer;

    private String category;

    private String type;

    private String modality;

    private String postedDate;

    private String salaryRange;

    private String description;

    private int numOfRequests;
}
