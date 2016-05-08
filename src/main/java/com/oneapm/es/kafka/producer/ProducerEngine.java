/**
 * Project Name:ai-es-transaction-data
 * File Name:ProducerEngine.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月4日下午11:10:46
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import lombok.Getter;
import lombok.Setter;

import com.oneapm.es.data.DataGenerator;
import com.oneapm.es.data.TransactionData;
import com.oneapm.es.kafka.context.ProducerContext;

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
    
    public static ProducerContext    context = null;
    
    private Producer<String, String> producer;
    
    private DataGenerator            source  = new DataGenerator();
    
    /**
     * build: <br/>
     * 
     * @author xushjie
     * @return
     * @since JDK 1.7
     */
    public static synchronized ProducerEngine build() {
        //
        if (context == null) {
            return null;
        }
        //
        ProducerEngine engine = new ProducerEngine();
        //
        engine.producer = new Producer<String, String>(context.getConfig());
        //
        return engine;
    }
    
    /**
     * build: <br/>
     * 
     * @author xushjie
     * @param context
     * @return
     * @since JDK 1.7
     */
    public static synchronized ProducerEngine build(ProducerContext context) {
        //
        ProducerEngine.context = context;
        //
        return build();
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