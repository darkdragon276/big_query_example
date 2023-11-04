package com.backend.bigquery.controller;

import com.backend.bigquery.entity.EfficacyDayInWeek;
import com.backend.bigquery.entity.Timesheets;
import com.backend.bigquery.service.EfficacyDayInWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/dayinweek")
public class EfficacyDayInWeekController {
    private final EfficacyDayInWeekService efficacyDayInWeekService;
    @Autowired
    public EfficacyDayInWeekController(EfficacyDayInWeekService efficacyDayInWeekService) {
        this.efficacyDayInWeekService = efficacyDayInWeekService;
    }
    @GetMapping
    public List<EfficacyDayInWeek> getEfficacyDayInWeek() {
        return efficacyDayInWeekService.getEfficacyDayInWeekList();
    }
}
