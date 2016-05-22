/**
 * Project Name:ai-es-transaction-data
 * File Name:ContextFactory.java
 * Package Name:com.oneapm.es.kafka.context
 * Date:2016年5月8日下午4:13:51
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.context;

import com.oneapm.es.kafka.consumer.ConsumerCommand;
import com.oneapm.es.kafka.producer.ProducerCmd;

/**
 * ClassName:ContextFactory <br/>
 * Function: <br/>
 * Date: 2016年5月8日 下午4:13:51 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public abstract class ContextFactory {
    
    private static ProducerContext producerContext = null;
    
    private static ConsumerContext consumerContext = null;
    
    /**
     * getProducerContext: <br/>
     * 
     * @author xushjie
     * @param option
     * @return
     * @since JDK 1.7
     */
    public static synchronized ProducerContext getProducerContext(ProducerCmd option) {
        if (producerContext == null) {
            producerContext = ProducerContext.build(option);
        }
        return producerContext;
    }
    
    /**
     * getConsumerContext: <br/>
     * 
     * @author xushjie
     * @param option
     * @return
     * @since JDK 1.7
     */
    public static synchronized ConsumerContext getConsumerContext(ConsumerCommand option) {
        if (consumerContext == null) {
            consumerContext = ConsumerContext.build(option);
        }
        return consumerContext;
    }
    
}
