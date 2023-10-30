package com.backend.bigquery.service;

import com.backend.bigquery.entity.SexProperty;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SexPropertyService {
    private final BigQuery bigQuery;
    @Autowired
    public SexPropertyService(BigQuery bigQuery) {
        this.bigQuery = bigQuery;
    }

    @Value("${spring.cloud.gcp.bigquery.project-id}")
    private String projectId;
    @Value("${spring.cloud.gcp.bigquery.dataset-name}")
    private String datasetName;
    @Value("Factory_Workers")
    private String tableName;

    public SexProperty getSexProperty(String sex) {
        SexProperty sexProperty;
        String query = "SELECT AVG(CAST(T.actual_efficacy_h AS float64)) as actual_efficacy, AVG(CAST(T.sub_colls_same_sex_prtn AS float64)) as sub_colls_same_sex_prtn" +
                " FROM (SELECT DISTINCT sub_ID, actual_efficacy_h, sub_colls_same_sex_prtn" +
                String.format(" FROM `%s.%s.%s` WHERE sub_sex = '%s' and " , projectId, datasetName, tableName, sex) +
                "behav_comptype_h = 'Efficacy' and SAFE_CAST(sub_colls_same_sex_prtn as float64) IS NOT NULL) as T ";
        System.out.println(query);
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();
        try {
            for(FieldValueList row: bigQuery.query(queryJobConfiguration).iterateAll()) {
                sexProperty = new SexProperty(sex
                        , row.get("actual_efficacy").getDoubleValue()
                        , row.get("sub_colls_same_sex_prtn").getDoubleValue()
                );
                return sexProperty;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return null;
    }
}
