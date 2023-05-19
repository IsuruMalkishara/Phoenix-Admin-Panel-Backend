package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.JobCategory;
import com.oxcentra.phoenix.adminpanel.dto.JobModality;
import com.oxcentra.phoenix.adminpanel.dto.JobType;
import com.oxcentra.phoenix.adminpanel.service.ModalityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ModalityController {
    @Autowired
    private ModalityService modalityService;

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @GetMapping("/modality")
    public @ResponseBody
    List<JobModality> getAllJobModalities(){

        log.info(String.valueOf(modalityService.getAllJobModalities()));
        return modalityService.getAllJobModalities();
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @DeleteMapping("/modality/{id}")
    public @ResponseBody
    Boolean deleteModalityById(@PathVariable String id) {
        log.info(id);
        return modalityService.deleteModalityById(id);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PutMapping("/modality/{id}")
    public @ResponseBody
    Boolean updateModality(@RequestBody JobModality jobModality) {

        log.info(jobModality.getTitle());
        return modalityService.updateModality(jobModality);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PostMapping("/modality")
    public @ResponseBody
    Boolean addModality(@RequestBody JobModality jobModality) {

        log.info(jobModality.getTitle());
        return modalityService.addModality(jobModality);
    }
}
