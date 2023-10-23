package com.backend.bigquery.autoconfig;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;

@Configuration
@EnableAutoConfiguration
public class BigQueryConfiguration {

    @Bean
    public DirectChannel bigQueryInsertDataChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel bigQueryReplyChannel() {
        return new DirectChannel();
    }

    @Bean
    public BigQuery bigQuery() {
        return BigQueryOptions.getDefaultInstance().getService();
    }

//    @Bean
//    @ServiceActivator(inputChannel = "bigQueryInsertDataChannel")
//    public MessageHandler messageSender(BigQueryTemplate bigQueryTemplate) {
//        BigQueryFileMessageHandler messageHandler = new BigQueryFileMessageHandler(bigQueryTemplate);
//        messageHandler.setFormatOptions(FormatOptions.csv());
//        messageHandler.setOutputChannel(bigQueryReplyChannel());
//        return messageHandler;
//    }
//
//    @Primary
//    @Bean
//    public GatewayProxyFactoryBean gatewayProxyFactoryBean() {
//        GatewayProxyFactoryBean factoryBean = new GatewayProxyFactoryBean(BigQueryFileGateway.class);
//        factoryBean.setDefaultRequestChannel(bigQueryInsertDataChannel());
//        factoryBean.setDefaultReplyChannel(bigQueryReplyChannel());
//        factoryBean.setAsyncExecutor(null);
//        return factoryBean;
//    }
//
//    @MessagingGateway
//    public interface BigQueryFileGateway {
//        ListenableFuture<Job> insertBigQueryTable(byte[] csvData, @Header(BigQuerySpringMessageHeaders.TABLE_NAME) String tableName);
//    }

}
