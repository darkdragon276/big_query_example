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
import java.util.ArrayList;
import java.util.List;
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

    public List<EfficacyDayInWeek> getEfficacyDayInWeekList() {
        List<EfficacyDayInWeek> efficacyDayInWeekList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String query = "SELECT event_weekday_num, AVG(CAST(actual_efficacy_h AS float64)) AS actual_efficacy" +
                String.format(" FROM `%s.%s.%s` WHERE behav_comptype_h = 'Efficacy' GROUP BY event_weekday_num ORDER BY event_weekday_num", projectId, datasetName, tableName);
        System.out.println(query);
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();
        try {
            for (FieldValueList row : bigQuery.query(queryJobConfiguration).iterateAll()) {
                EfficacyDayInWeek efficacyDayInWeek = new EfficacyDayInWeek(
                    row.get("event_weekday_num").getLongValue(),
                    row.get(0).getDoubleValue()
                );
                efficacyDayInWeekList.add(efficacyDayInWeek);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        return efficacyDayInWeekList;
    }
}