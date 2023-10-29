package com.backend.bigquery.controller;

import com.backend.bigquery.entity.AgeProperty;
import com.backend.bigquery.service.AgePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/age")
public class AgePropertyController {
    private final AgePropertyService agePropertyService;
    @Autowired
    public AgePropertyController(AgePropertyService agePropertyService) {
        this.agePropertyService = agePropertyService;
    }
    @GetMapping
    public AgeProperty getAgeProperty(@RequestParam long age) {
        return agePropertyService.getAgeProperty(age);
    }
    @GetMapping(value = "/ageEfficacy")
    public AgeProperty getAgeEfficacy(@RequestParam long age) {
        return agePropertyService.getAgeActualEfficacy(age);
    }
}
