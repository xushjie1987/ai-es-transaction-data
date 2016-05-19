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
import io.airlift.airline.Option;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
         description = "Pooled线程池Kafka producer客户端")
public class PooledProducerCmd extends ProducerCommand {
    
    @Option(name = { "-f", "--fixed" },
            required = false,
            description = "fixed模式的线程池，默认关闭")
    public Boolean          fixed      = false;
    
    @Option(name = { "-h", "--cached" },
            required = false,
            description = "cached模式的线程池，默认关闭")
    public Boolean          cached     = false;
    
    @Option(name = { "-g", "--single" },
            required = false,
            description = "single模式的线程池，默认关闭")
    public Boolean          single     = false;
    
    @Option(name = { "-s", "--scaled" },
            required = false,
            description = "scaled模式的线程池，默认关闭")
    public Boolean          scaled     = false;
    
    private ExecutorService threadPool = null;
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        init();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
            }
        });
        System.out.println("暂未开发");
    }
    
    /**
     * init: <br/>
     * 
     * @author xushjie
     * @since JDK 1.7
     */
    private void init() {
        if (fixed) {
            threadPool = Executors.newFixedThreadPool(1);
        }
    }
    
}
