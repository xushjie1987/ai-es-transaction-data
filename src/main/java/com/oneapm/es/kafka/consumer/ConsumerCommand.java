/**
 * Project Name:ai-es-transaction-data
 * File Name:ConsumerCommand.java
 * Package Name:com.oneapm.es.kafka.consumer
 * Date:2016年5月2日下午8:03:19
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.consumer;

import io.airlift.airline.Command;

/**
 * ClassName:ConsumerCommand <br/>
 * Function: <br/>
 * Date: 2016年5月2日 下午8:03:19 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Command(name = "consumer",
         description = "Kafka Consumer Client")
public class ConsumerCommand implements Runnable {
    
    @Override
    public void run() {
        System.out.println("功能未开发");
    }
    
}
