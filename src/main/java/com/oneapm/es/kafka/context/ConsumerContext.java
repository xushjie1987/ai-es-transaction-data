/**
 * Project Name:ai-es-transaction-data
 * File Name:ConsumerContext.java
 * Package Name:com.oneapm.es.kafka.context
 * Date:2016年5月8日下午4:16:33
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.context;

import kafka.consumer.ConsumerConfig;
import lombok.Getter;
import lombok.Setter;

import com.oneapm.es.kafka.consumer.ConsumerCommand;

/**
 * ClassName:ConsumerContext <br/>
 * Function: <br/>
 * Date: 2016年5月8日 下午4:16:33 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Getter
@Setter
public class ConsumerContext {
    
    private ConsumerConfig config;
    
    /**
     * build: <br/>
     * 
     * @author xushjie
     * @param option
     * @return
     * @since JDK 1.7
     */
    public static ConsumerContext build(ConsumerCommand option) {
        ConsumerContext context = new ConsumerContext();
        // FIXME
        return context;
    }
    
}
