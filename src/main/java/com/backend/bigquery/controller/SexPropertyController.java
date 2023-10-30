package com.backend.bigquery.controller;

import com.backend.bigquery.entity.SexProperty;
import com.backend.bigquery.service.SexPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/sex")
public class SexPropertyController {
    private final SexPropertyService sexPropertyService;
    @Autowired
    public SexPropertyController(SexPropertyService sexPropertyService) {
        this.sexPropertyService = sexPropertyService;
    }
    @GetMapping
    public SexProperty getSexProperty(@RequestParam String sex) {
        return sexPropertyService.getSexProperty(sex);
    }
}
