package com.backend.bigquery.service;

import com.backend.bigquery.entity.Timesheets;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimesheetsService {
    private final BigQuery bigQuery;
    @Autowired
    public TimesheetsService(BigQuery bigQuery) {
        this.bigQuery = bigQuery;
    }

    @Value("${spring.cloud.gcp.bigquery.project-id}")
    private String projectId;
    @Value("${spring.cloud.gcp.bigquery.dataset-name}")
    private String datasetName;
    @Value("Factory_Workers")
    private String tableName;

    public Timesheets getTimesheets(long id, Date from, Date to) {
        Timesheets timesheets;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String query = "SELECT COUNT(*) FROM (SELECT DISTINCT event_date, event_week_in_series, event_day_in_series, event_weekday_num, event_weekday_name"
                + String.format(" FROM `%s.%s.%s`", projectId, datasetName, tableName)
                + String.format(" WHERE (sub_ID = %d) and (event_date BETWEEN '%s' AND '%s')) as T", id, format.format(from), format.format(to));
        System.out.println(query);
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();
        try {
            for(FieldValueList row: bigQuery.query(queryJobConfiguration).iterateAll()) {
                timesheets = new Timesheets(id
                        , row.get(0).getLongValue()
                );
                return timesheets;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        return null;
    }
}