/**
 * Project Name:ai-es-transaction-data
 * File Name:SingleProducerCmd.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月3日下午11:51:40
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import io.airlift.airline.Command;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.oneapm.es.data.DataGenerator;
import com.oneapm.es.data.TransactionData;

/**
 * ClassName:SingleProducerCmd <br/>
 * Function: <br/>
 * Date: 2016年5月3日 下午11:51:40 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Command(name = "single",
         description = "单线程Kafka producer客户端")
public class SingleProducerCmd extends ProducerCommand {
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        Properties props = new Properties();
        props.put("metadata.broker.list",
                  brokerList);
        props.put("key.serializer.class",
                  keyType);
        props.put("serializer.class",
                  valueType);
        props.put("partitioner.class",
                  partitionerType);
        props.put("request.required.acks",
                  isACK.booleanValue()
                                      ? "1"
                                      : "0");
        //
        ProducerConfig config = new ProducerConfig(props);
        //
        Producer<String, String> producer = new Producer<String, String>(config);
        //
        DataGenerator dg = new DataGenerator();
        //
        int i = 0;
        while (count.intValue() < 0 ||
               i++ < Math.abs(count.intValue())) {
            TransactionData td = dg.getTransactionData();
            KeyedMessage<String, String> data = new KeyedMessage<String, String>(topicId,
                                                                                 String.valueOf(td.getApplicationId()),
                                                                                 td.toJSON());
            producer.send(data);
        }
        //
        producer.close();
    }
    
}
