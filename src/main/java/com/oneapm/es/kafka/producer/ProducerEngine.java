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
import com.oneapm.es.data.RandomDataGenerator;
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
    
    private DataGenerator            source;
    
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
        try {
            engine.source = context.getDgClazz() == null
                                                        ? new RandomDataGenerator()
                                                        : context.getDgClazz()
                                                                 .newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("实例化" +
                               context.getDgClazz()
                                      .getName() +
                               "类型实例失败!");
            engine.source = new RandomDataGenerator();
        }
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
    public long sendBulk(String topicId,
                         Long bulk) {
        long total = 0;
        for (long i = 0; i < bulk.longValue(); i++) {
            total += sendOne(topicId);
        }
        return total;
    }
    
    /**
     * offset: <br/>
     * @author xushjie
     * @return
     * @since JDK 1.7
     */
    public static long offset() {
        return context == null
                              ? 0L
                              : context.sumAll();
    }
    
    /**
     * plusBulk: <br/>
     * @author xushjie
     * @param bulk
     * @since JDK 1.7
     */
    public void plusBulk(long bulk) {
        context.plusBulk(bulk);
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
