package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.JobCategory;
import com.oxcentra.phoenix.adminpanel.dto.JobModality;
import com.oxcentra.phoenix.adminpanel.model.Admin;
import com.oxcentra.phoenix.adminpanel.service.JobCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class JobCategoryController {

    @Autowired
    private JobCategoryService jobCategoryService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/category")
    public @ResponseBody
    List<JobCategory> getAllJobCategories(){

        log.info(String.valueOf(jobCategoryService.getAllJobCategories()));
        return jobCategoryService.getAllJobCategories();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/category/{id}")
    public @ResponseBody
    Boolean deleteCategoryById(@PathVariable String id) {
        log.info(id);
        return jobCategoryService.deleteCategoryById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/category/{id}")
    public @ResponseBody
    Boolean updateCategory(@RequestBody JobCategory jobCategory) {

        log.info(jobCategory.getTitle());
        return jobCategoryService.updateCategory(jobCategory);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/category")
    public @ResponseBody
    Boolean addCategory(@RequestBody JobCategory jobCategory) {

        log.info(jobCategory.getTitle());
        return jobCategoryService.addCategory(jobCategory);
    }
}
