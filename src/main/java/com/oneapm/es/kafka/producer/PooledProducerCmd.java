/**
 * Project Name:ai-es-transaction-data
 * File Name:PooledProducerCmd.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月3日下午11:59:23
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import io.airlift.airline.Command;

/**
 * ClassName:PooledProducerCmd <br/>
 * Function: <br/>
 * Date: 2016年5月3日 下午11:59:23 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Command(name = "pool",
         description = "线程池Kafka producer客户端")
public class PooledProducerCmd extends ProducerCommand {
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        
    }
    
}
