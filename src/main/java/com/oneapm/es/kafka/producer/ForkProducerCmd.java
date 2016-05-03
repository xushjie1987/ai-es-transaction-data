/**
 * Project Name:ai-es-transaction-data
 * File Name:ForkProducerCmd.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月4日上午12:02:44
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import io.airlift.airline.Command;

/**
 * ClassName:ForkProducerCmd <br/>
 * Function: <br/>
 * Date: 2016年5月4日 上午12:02:44 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Command(name = "fork",
         description = "ForkJoin模式Kafka producer客户端")
public class ForkProducerCmd extends ProducerCommand {
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        
    }
    
}
