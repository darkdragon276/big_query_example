//package com.backend.bigquery.service;
//
//import com.google.cloud.bigquery.BigQuery;
//import com.google.cloud.bigquery.FieldValue;
//import com.google.cloud.bigquery.FieldValueList;
//import com.google.cloud.bigquery.QueryJobConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gcp.bigquery.core.BigQueryTemplate;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class BigQueryService {
//    @Autowired
//    BigQueryTemplate bigQueryTemplate;
//
//    @Autowired
//    BigQuery bigquery;
//
//    @Value("${spring.cloud.gcp.bigquery.datasetName}")
//    private String datasetName;
//
//    public String getDatasetName() throws IOException {
//        return this.bigQueryTemplate.getDatasetName();
//    }
//
//    public void runQuery() throws InterruptedException {
//        String query = "SELECT * FROM `bigquery-402309.market.Factory_Workers` LIMIT 100;";
//        QueryJobConfiguration queryConfig =
//                QueryJobConfiguration.newBuilder(query).build();
//
//        // Run the query using the BigQuery object
//        for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
//            for (FieldValue val : row) {
//                System.out.println(val);
//            }
//        }
//    }
//
////    public ListenableFuture<Job> writeDataToTable(MultipartFile file, String tableName) throws IOException {
////        return this.bigQueryTemplate.writeDataToTable(tableName, file.getInputStream(), FormatOptions.csv());
////    }
//
////    public ListenableFuture<Job> insertBigQueryTable(String csvData, String tableName) {
////        return this.bigQueryFileGateway.insertBigQueryTable(csvData.getBytes(), tableName);
////    }
////
////    public Page<Dataset> listDatasets(DatasetListOption options) {
////        return bigquery.listDatasets(options);
////    }
////
////    public Page<Table> listTables(String datasetId, TableListOption options) {
////        return bigquery.listTables(datasetId, options);
////    }
////
////    public TableResult listTableData(String dataset, String table, long pageSize) {
////        TableId tableId = TableId.of(dataset, table);
////        return bigquery.listTableData(tableId, TableDataListOption.pageSize(pageSize));
////    }
//}