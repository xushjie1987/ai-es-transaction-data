/**
 * Project Name:ai-es-transaction-data
 * File Name:SingleProducerCmd.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月3日下午11:51:40
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import io.airlift.airline.Command;

import com.oneapm.es.kafka.context.ContextFactory;
import com.oneapm.es.util.TimeUtil;

/**
 * ClassName:SingleProducerCmd <br/>
 * Function: <br/>
 * Date: 2016年5月3日 下午11:51:40 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Command(name = "single",
         description = "Single单线程Kafka producer客户端")
public class SingleProducerCmd extends ProducerCmd {
    
    public ProducerEngine engine = ProducerEngine.build(ContextFactory.getProducerContext(this));
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        //
        int i = 0;
        int total = 0;
        long start = System.currentTimeMillis();
        while (count.intValue() < 0 ||
               i++ < Math.abs(count.intValue())) {
            //
            total += engine.sendOne(topicId);
        }
        //
        engine.shutdown();
        //
        long end = System.currentTimeMillis();
        System.out.println("预计发送[" +
                           count.intValue() +
                           "]条Message，实际发送[" +
                           total +
                           "]条Message，耗时：" +
                           TimeUtil.humanTime(end -
                                              start));
    }
    
}
