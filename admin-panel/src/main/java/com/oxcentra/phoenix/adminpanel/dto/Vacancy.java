package com.oxcentra.phoenix.adminpanel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

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

    private String descriptionImg;

    private int numOfRequests;

    private Date expirationDate;
}
