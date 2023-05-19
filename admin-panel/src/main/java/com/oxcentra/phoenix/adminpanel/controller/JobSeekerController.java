package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.Employer;
import com.oxcentra.phoenix.adminpanel.dto.JobSeeker;
import com.oxcentra.phoenix.adminpanel.service.JobCategoryService;
import com.oxcentra.phoenix.adminpanel.service.JobSeekerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class JobSeekerController {
    @Autowired
    private JobSeekerService jobSeekerService;

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @GetMapping("/jobseeker")
    public @ResponseBody
    List<JobSeeker> getAllJobSeekers()  {
        return jobSeekerService.getAllJobSeekers();
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @GetMapping("/jobseeker/{id}")
    public @ResponseBody
    Optional<JobSeeker> getJobSeekerById(@PathVariable Integer id)  {
        log.info(String.valueOf(id));
        return jobSeekerService.getJobSeekerById(id);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @DeleteMapping("/jobseeker/{id}")
    public @ResponseBody
    Boolean deleteJobSeekerById(@PathVariable Integer id) {
        log.info(String.valueOf(id));
        return jobSeekerService.deleteJobSeekerById(id);
    }


    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PostMapping("/jobseeker/title")
    public @ResponseBody
    List<JobSeeker> searchJobSeekers(@RequestBody Map<String, String> requestData) {
        String title = requestData.get("title");
        log.info(title);
        return jobSeekerService.searchJobSeekers(title);
    }

}
