package com.backend.bigquery.service;

import com.backend.bigquery.entity.Worker;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerService {
    private final BigQuery bigQuery;
    @Autowired
    public WorkerService(BigQuery bigQuery) {
        this.bigQuery = bigQuery;
    }

    @Value("${spring.cloud.gcp.bigquery.project-id}")
    private String projectId;
    @Value("${spring.cloud.gcp.bigquery.dataset-name}")
    private String datasetName;
    @Value("Factory_Workers")
    private String tableName;

    public Worker getWorker(long id) {
        Worker worker;
        String query = "SELECT sub_ID, sub_fname, sub_lname, sub_age, sub_sex, sub_shift, sub_team, sub_role"
                + String.format(" FROM `%s.%s.%s`", projectId, datasetName, tableName)
                + String.format(" WHERE sub_ID = %d LIMIT 1", id);
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();
        try {
            for(FieldValueList row: bigQuery.query(queryJobConfiguration).iterateAll()) {
                worker = new Worker(row.get("sub_ID").getLongValue()
                        , row.get("sub_fname").getStringValue()
                        , row.get("sub_lname").getStringValue()
                        , row.get("sub_age").getLongValue()
                        , row.get("sub_sex").getStringValue()
                        , row.get("sub_shift").getStringValue()
                        , row.get("sub_team").getStringValue()
                        , row.get("sub_role").getStringValue()
                );
                return worker;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Worker> getWorkerListWithFirstName(String firstName) {
        List<Worker> workerList = new ArrayList<Worker>();
        String query = "SELECT DISTINCT sub_ID, sub_fname, sub_lname, sub_age, sub_sex, sub_shift, sub_team, sub_role"
                + String.format(" FROM `%s.%s.%s`", projectId, datasetName, tableName)
                + String.format(" WHERE sub_fname = '%s'", firstName);
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();
        try {
            for(FieldValueList row: bigQuery.query(queryJobConfiguration).iterateAll()) {
                workerList.add(new Worker(row.get("sub_ID").getLongValue()
                        , row.get("sub_fname").getStringValue()
                        , row.get("sub_lname").getStringValue()
                        , row.get("sub_age").getLongValue()
                        , row.get("sub_sex").getStringValue()
                        , row.get("sub_shift").getStringValue()
                        , row.get("sub_team").getStringValue()
                        , row.get("sub_role").getStringValue()
                ));
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        return workerList;
    }

    public List<Worker> getWorkerListWithLastName(String lastName) {
        List<Worker> workerList = new ArrayList<Worker>();
        String query = "SELECT DISTINCT sub_ID, sub_fname, sub_lname, sub_age, sub_sex, sub_shift, sub_team, sub_role"
                + String.format(" FROM `%s.%s.%s`", projectId, datasetName, tableName)
                + String.format(" WHERE sub_lname = '%s'", lastName);
        System.out.println(query);
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();
        try {
            for(FieldValueList row: bigQuery.query(queryJobConfiguration).iterateAll()) {
                workerList.add(new Worker(row.get("sub_ID").getLongValue()
                        , row.get("sub_fname").getStringValue()
                        , row.get("sub_lname").getStringValue()
                        , row.get("sub_age").getLongValue()
                        , row.get("sub_sex").getStringValue()
                        , row.get("sub_shift").getStringValue()
                        , row.get("sub_team").getStringValue()
                        , row.get("sub_role").getStringValue()
                ));
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return workerList;
    }
}