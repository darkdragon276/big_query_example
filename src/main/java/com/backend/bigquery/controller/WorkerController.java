package com.backend.bigquery.controller;

import com.backend.bigquery.entity.Worker;
import com.backend.bigquery.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/worker")
public class WorkerController {
    private final WorkerService workerService;
    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }
    @GetMapping
    public Worker getWorkerWithId(@RequestParam long id) {
        return workerService.getWorker(id);
    }

    @GetMapping(value = "firstName")
    public List<Worker> getWorkerWithFirstName(@RequestParam String firstName) {
        return workerService.getWorkerListWithFirstName(firstName);
    }

    @GetMapping(value = "lastName")
    public List<Worker> getWorkerWithLastName(@RequestParam String lastName) {
        return workerService.getWorkerListWithLastName(lastName);
    }

    @GetMapping(value = "unEfficacyDate")
    public Worker getWorkerWithLastName(@RequestParam long id) {
        return workerService.getWorkerUnefficacyDate(id);
    }
}
