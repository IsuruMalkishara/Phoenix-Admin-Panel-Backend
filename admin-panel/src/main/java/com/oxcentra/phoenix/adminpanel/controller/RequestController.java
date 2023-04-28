package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.JobseekerRequest;
import com.oxcentra.phoenix.adminpanel.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RequestController {
   @Autowired
   private RequestService requestService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/request/{id}")
    public @ResponseBody
    List<JobseekerRequest> getAllRequestByVacancyId(@PathVariable Integer id) {
        log.info(String.valueOf(id));
        return requestService.getAllRequestByVacancyId(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/request/{id}")
    public @ResponseBody
    Boolean deleteRequestById(@PathVariable Integer id) {
        log.info(String.valueOf(id));
        return requestService.deleteRequestById(id);
    }
}
