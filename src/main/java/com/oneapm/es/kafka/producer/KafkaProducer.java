/**
 * Project Name:ai-es-transaction-data
 * File Name:KafkaProducer.java
 * Package Name:com.oneapm.es.kafka
 * Date:2016年4月29日下午4:00:20
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.oneapm.es.data.DataGenerator;
import com.oneapm.es.data.RandomDataGenerator;
import com.oneapm.es.data.TransactionData;

/**
 * ClassName:KafkaProducer <br/>
 * Function: <br/>
 * Date: 2016年4月29日 下午4:00:20 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class KafkaProducer {
    
    /**
     * main: <br/>
     * 
     * @author xushjie
     * @param args
     * @since JDK 1.7
     */
    public static void main(String[] args) {
        long events = Long.parseLong(args[0]);
        
        Properties props = new Properties();
        props.put("metadata.broker.list",
                  "10.45.39.199:9092,10.46.177.114:9092,10.45.11.108:9092");
        props.put("serializer.class",
                  "kafka.serializer.StringEncoder");
        props.put("partitioner.class",
                  "com.oneapm.es.kafka.SimplePartitioner");
        props.put("request.required.acks",
                  "1");
        
        ProducerConfig config = new ProducerConfig(props);
        
        Producer<String, String> producer = new Producer<String, String>(config);
        
        DataGenerator dg = new RandomDataGenerator();
        
        for (long nEvents = 0; nEvents < events; nEvents++) {
            TransactionData td = dg.getTransactionData();
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("ai_es_transaction_data",
                                                                                 String.valueOf(td.getApplicationId()),
                                                                                 td.toJSON());
            producer.send(data);
        }
        producer.close();
    }
    
}
