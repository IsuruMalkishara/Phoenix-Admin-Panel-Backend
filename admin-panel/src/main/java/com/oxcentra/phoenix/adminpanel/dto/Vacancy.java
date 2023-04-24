package com.oxcentra.phoenix.adminpanel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {

    private Integer id;

    private String title;

    private Employer employer;

    private JobCategory category;

    private JobType type;

    private JobModality modality;

    private String postedDate;

    private String salaryRange;

    private String description;

    private int numOfRequests;
}
