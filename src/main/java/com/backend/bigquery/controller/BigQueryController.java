package com.backend.bigquery.controller;

import com.google.cloud.bigquery.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/bigquery")
public class BigQueryController {
//    private BigQueryService bigQueryService;
//
//    @Autowired
//    public void BigQueryService(BigQueryService bigQueryService) { this.bigQueryService = bigQueryService;}

    @GetMapping
    public void getMapping() {
        String query =
                "SELECT name FROM `bigquery-public-data.usa_names.usa_1910_2013`"
                        + " WHERE state = \"TX\""
                        + " LIMIT 100";
        String projectId = "bigquery-402309";
        String datasetName  = "market";
        String tableName  = "Factory_Workers";

        try {
            BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

            TableResult results = bigquery.query(QueryJobConfiguration.of(query));

            System.out.println("Query total rows performed successfully." + results.toString());
        } catch (BigQueryException | InterruptedException e) {
            System.out.println("Failed to retrieve dataset or tables. \n" + e.toString());
        };
    }
}
