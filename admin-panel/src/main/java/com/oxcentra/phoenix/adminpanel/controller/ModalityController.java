package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.JobModality;
import com.oxcentra.phoenix.adminpanel.dto.JobType;
import com.oxcentra.phoenix.adminpanel.service.ModalityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ModalityController {
    @Autowired
    private ModalityService modalityService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/modality")
    public @ResponseBody
    List<JobModality> getAllJobModalities(){

        log.info(String.valueOf(modalityService.getAllJobModalities()));
        return modalityService.getAllJobModalities();
    }
}
