/**
 * Project Name:ai-es-transaction-data
 * File Name:ProducerContext.java
 * Package Name:com.oneapm.es.kafka.context
 * Date:2016年5月8日下午4:17:03
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.context;

import java.util.Properties;
import java.util.concurrent.atomic.LongAdder;

import kafka.producer.ProducerConfig;
import lombok.Getter;
import lombok.Setter;

import com.oneapm.es.kafka.producer.ProducerCommand;

/**
 * ClassName:ProducerContext <br/>
 * Function: <br/>
 * Date: 2016年5月8日 下午4:17:03 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Getter
@Setter
public class ProducerContext {
    
    private ProducerConfig config;
    
    private LongAdder      sumCount = new LongAdder();
    
    /**
     * build: <br/>
     * 
     * @author xushjie
     * @param optioin
     * @return
     * @since JDK 1.7
     */
    public static ProducerContext build(ProducerCommand optioin) {
        //
        Properties props = new Properties();
        props.put("metadata.broker.list",
                  optioin.getBrokerList());
        props.put("key.serializer.class",
                  optioin.getKeyType());
        props.put("serializer.class",
                  optioin.getValueType());
        props.put("partitioner.class",
                  optioin.getPartitionerType());
        props.put("request.required.acks",
                  optioin.getIsACK()
                         .booleanValue()
                                        ? "1"
                                        : "0");
        //
        ProducerContext context = new ProducerContext();
        context.config = new ProducerConfig(props);
        //
        return context;
    }
    
    /**
     * plusPlus: <br/>
     * @author xushjie
     * @since JDK 1.7
     */
    public void plusPlus() {
        sumCount.increment();
    }
    
    /**
     * plusBulk: <br/>
     * @author xushjie
     * @param bulk
     * @since JDK 1.7
     */
    public void plusBulk(long bulk) {
        sumCount.add(bulk);
    }
    
    /**
     * sumAll: <br/>
     * @author xushjie
     * @return
     * @since JDK 1.7
     */
    public long sumAll() {
        return sumCount.sum();
    }
    
}
