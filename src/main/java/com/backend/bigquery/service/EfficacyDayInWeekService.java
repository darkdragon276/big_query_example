package com.backend.bigquery.service;

import com.backend.bigquery.entity.EfficacyDayInWeek;
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
public class EfficacyDayInWeekService {
    private final BigQuery bigQuery;
    @Autowired
    public EfficacyDayInWeekService(BigQuery bigQuery) {
        this.bigQuery = bigQuery;
    }

    @Value("${spring.cloud.gcp.bigquery.project-id}")
    private String projectId;
    @Value("${spring.cloud.gcp.bigquery.dataset-name}")
    private String datasetName;
    @Value("Factory_Workers")
    private String tableName;

    public EfficacyDayInWeek getEfficacyDayInWeek(long dayInWeekNum) {
        EfficacyDayInWeek efficacyDayInWeek;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String query = "SELECT AVG(CAST(T.actual_efficacy_h AS float64)) as actual_efficacy" +
                String.format(" FROM (SELECT DISTINCT sub_ID, actual_efficacy_h FROM `%s.%s.%s` WHERE behav_comptype_h = 'Efficacy' and event_weekday_num = %d) as T", projectId, datasetName, tableName, dayInWeekNum);
        System.out.println(query);
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();
        try {
            for(FieldValueList row: bigQuery.query(queryJobConfiguration).iterateAll()) {
                efficacyDayInWeek = new EfficacyDayInWeek(dayInWeekNum
                        , row.get(0).getDoubleValue()
                );
                return efficacyDayInWeek;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        return null;
    }
}