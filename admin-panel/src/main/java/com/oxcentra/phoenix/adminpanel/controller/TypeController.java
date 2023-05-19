package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.JobType;
import com.oxcentra.phoenix.adminpanel.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.TypeSearchability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TypeController {
    @Autowired
    private TypeService typeService;

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @GetMapping("/type")
    public @ResponseBody
    List<JobType> getAllJobTypes(){

        log.info(String.valueOf(typeService.getAllJobTypes()));
        return typeService.getAllJobTypes();
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @DeleteMapping("/type/{id}")
    public @ResponseBody
    Boolean deleteTypeById(@PathVariable String id) {
        log.info(id);
        return typeService.deleteTypeById(id);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PutMapping("/type/{id}")
    public @ResponseBody
    Boolean updateType(@RequestBody JobType jobType) {

        log.info(jobType.getTitle());
        return typeService.updateType(jobType);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PostMapping("/type")
    public @ResponseBody
    Boolean addType(@RequestBody JobType jobType) {

        log.info(jobType.getTitle());
        return typeService.addType(jobType);
    }
}
