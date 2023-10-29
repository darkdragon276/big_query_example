package com.backend.bigquery.service;

import com.backend.bigquery.entity.AgeProperty;
import com.backend.bigquery.entity.Worker;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AgePropertyService {
    private final BigQuery bigQuery;
    @Autowired
    public AgePropertyService(BigQuery bigQuery) {
        this.bigQuery = bigQuery;
    }

    @Value("${spring.cloud.gcp.bigquery.project-id}")
    private String projectId;
    @Value("${spring.cloud.gcp.bigquery.dataset-name}")
    private String datasetName;
    @Value("Factory_Workers")
    private String tableName;

    public AgeProperty getAgeProperty(long age) {
        AgeProperty ageProperty;
        String query = "SELECT AVG(T.sub_health_h) as health, AVG(T.sub_commitment_h) as commitment, AVG(T.sub_perceptiveness_h) as perceptiveness," +
                " AVG(T.sub_dexterity_h) as dexterity, AVG(T.sub_sociality_h) as sociality, AVG(T.sub_goodness_h) as goodness," +
                " AVG(T.sub_strength_h) as strength, AVG(T.sub_openmindedness_h) as openmindedness" +
                " FROM (SELECT DISTINCT sub_health_h, sub_commitment_h, sub_perceptiveness_h, sub_dexterity_h, sub_sociality_h, sub_goodness_h, sub_strength_h, sub_openmindedness_h" +
                String.format(" FROM `%s.%s.%s` WHERE sub_age = %d) as T ", projectId, datasetName, tableName, age);
        System.out.println(query);
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();
        try {
            for(FieldValueList row: bigQuery.query(queryJobConfiguration).iterateAll()) {
                ageProperty = new AgeProperty(age
                        , row.get("health").getDoubleValue()
                        , row.get("commitment").getDoubleValue()
                        , row.get("perceptiveness").getDoubleValue()
                        , row.get("dexterity").getDoubleValue()
                        , row.get("sociality").getDoubleValue()
                        , row.get("goodness").getDoubleValue()
                        , row.get("strength").getDoubleValue()
                        , row.get("openmindedness").getDoubleValue()
                        , 0
                );
                return ageProperty;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return null;
    }

    public AgeProperty getAgeActualEfficacy(long age) {
        AgeProperty ageProperty;
        String query = "SELECT AVG(CAST(T.actual_efficacy_h AS float64)) as actual_efficacy" +
                String.format(" FROM (SELECT actual_efficacy_h FROM `%s.%s.%s` WHERE behav_comptype_h = 'Efficacy' and sub_age = %d) as T", projectId, datasetName, tableName, age);
        System.out.println(query);
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();
        try {
            for(FieldValueList row: bigQuery.query(queryJobConfiguration).iterateAll()) {
                ageProperty = new AgeProperty(age
                        , 0
                        , 0
                        , 0
                        , 0
                        , 0
                        , 0
                        , 0
                        , 0
                        , row.get("actual_efficacy").getDoubleValue()
                );
                return ageProperty;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return null;
    }
}
