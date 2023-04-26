package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.JobType;
import com.oxcentra.phoenix.adminpanel.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.TypeSearchability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TypeController {
    @Autowired
    private TypeService typeService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/type")
    public @ResponseBody
    List<JobType> getAllJobTypes(){

        log.info(String.valueOf(typeService.getAllJobTypes()));
        return typeService.getAllJobTypes();
    }

}
