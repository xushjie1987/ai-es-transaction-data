/**
 * Project Name:ai-es-transaction-data
 * File Name:ProducerEngine.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月4日下午11:10:46
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import lombok.Getter;
import lombok.Setter;

import com.oneapm.es.data.DataGenerator;
import com.oneapm.es.data.TransactionData;

/**
 * ClassName:ProducerEngine <br/>
 * Function: <br/>
 * Date: 2016年5月4日 下午11:10:46 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Setter
@Getter
public class ProducerEngine {
    
    private Producer<String, String> producer;
    
    private DataGenerator            source;
    
    /**
     * build: <br/>
     * 
     * @author xushjie
     * @param brokerList
     * @param keyType
     * @param valueType
     * @param partitionerType
     * @param isACK
     * @return
     * @since JDK 1.7
     */
    public static ProducerEngine build(String brokerList,
                                       String keyType,
                                       String valueType,
                                       String partitionerType,
                                       Boolean isACK) {
        //
        ProducerEngine engine = new ProducerEngine();
        //
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
        engine.producer = new Producer<String, String>(config);
        engine.source = new DataGenerator();
        //
        return engine;
    }
    
    /**
     * sendOne: <br/>
     * 
     * @author xushjie
     * @param topicId
     * @return
     * @since JDK 1.7
     */
    public int sendOne(String topicId) {
        try {
            TransactionData td = source.getTransactionData();
            KeyedMessage<String, String> data = new KeyedMessage<String, String>(topicId,
                                                                                 String.valueOf(td.getApplicationId()),
                                                                                 td.toJSON());
            producer.send(data);
            return 1;
        } catch (Exception e) {
            // skip
        }
        return 0;
    }
    
    /**
     * sendBulk: <br/>
     * 
     * @author xushjie
     * @param topicId
     * @param bulk
     * @return
     * @since JDK 1.7
     */
    public int sendBulk(String topicId,
                        Integer bulk) {
        int total = 0;
        for (int i = 0; i < bulk.intValue(); i++) {
            total += sendOne(topicId);
        }
        return total;
    }
    
    /**
     * shutdown: <br/>
     * 
     * @author xushjie
     * @since JDK 1.7
     */
    public void shutdown() {
        producer.close();
    }
    
}
