/**
 * Project Name:ai-es-transaction-data
 * File Name:MultiProducerCmd.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月3日下午11:58:16
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import io.airlift.airline.Command;

/**
 * ClassName:MultiProducerCmd <br/>
 * Function: <br/>
 * Date: 2016年5月3日 下午11:58:16 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Command(name = "multi",
         description = "多线程Kafka producer客户端")
public class MultiProducerCmd extends ProducerCommand {
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        
    }
    
}
