package com.backend.bigquery.service;

import com.backend.bigquery.entity.Worker;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

    public Worker getWorkerActualEfficacy(long id) {
        Worker worker;
        String query = "SELECT sub.sub_ID, sub.sub_health_h,sub.sub_sociality_h,COUNT(*) AS un_efficacy_date, efficacy_date,actual_efficacy"
                + String.format(" FROM `%s.%s.%s`", projectId, datasetName, tableName)
                + " AS sub INNER JOIN ( SELECT sub_ID, COUNT(*) AS efficacy_date, ROUND(SUM(SAFE_CAST(actual_efficacy_h AS FLOAT64)), 2) AS actual_efficacy"
                + String.format(" FROM `%s.%s.%s`", projectId, datasetName, tableName)
                + " WHERE behav_comptype_h = 'Efficacy' GROUP BY sub_ID ) AS Efficacy_Table ON sub.sub_ID = Efficacy_Table.sub_ID"
                + String.format(" WHERE sub.sub_ID = %d ", id)
                + " AND sub.behav_comptype_h NOT IN ('Efficacy','Presence')"
                + " GROUP BY sub.sub_ID,sub.sub_health_h,sub.sub_sociality_h,actual_efficacy,efficacy_date ORDER BY sub.sub_ID";
        QueryJobConfiguration queryJobConfiguration2 = QueryJobConfiguration.newBuilder(query).build();
        System.out.println(query);
        try {
            for(FieldValueList row: bigQuery.query(queryJobConfiguration2).iterateAll()) {
                worker = new Worker(id
                        , row.get("sub_health_h").getDoubleValue()
                        , row.get("sub_sociality_h").getDoubleValue()
                        , row.get("un_efficacy_date").getLongValue()
                        , row.get("efficacy_date").getLongValue()
                        , row.get("actual_efficacy").getDoubleValue()
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