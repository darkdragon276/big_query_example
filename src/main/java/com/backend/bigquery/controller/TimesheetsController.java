package com.backend.bigquery.controller;

import com.backend.bigquery.entity.Timesheets;
import com.backend.bigquery.service.TimesheetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "api/timesheets")
public class TimesheetsController {
    private final TimesheetsService timesheetsService;
    @Autowired
    public TimesheetsController(TimesheetsService timesheetsService) {
        this.timesheetsService = timesheetsService;
    }
    @GetMapping
    public Timesheets getWorkerWithId(@RequestParam long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from
            , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to) {
        return timesheetsService.getTimesheets(id, from, to);
    }
}
