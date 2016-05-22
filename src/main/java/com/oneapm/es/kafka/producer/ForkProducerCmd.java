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
import io.airlift.airline.Option;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import lombok.Getter;
import lombok.Setter;

import com.oneapm.es.kafka.context.ContextFactory;
import com.oneapm.es.util.TimeUtil;

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
@Getter
@Setter
@Command(name = "fork",
         description = "ForkJoin模式Kafka producer客户端")
public class ForkProducerCmd extends ProducerCmd {
    
    @Option(name = { "-s", "--subs" },
            required = false,
            description = "ForkJoin模式fork子任务数，内部参数（非必须），缺省5，不要设置太大")
    public Integer       subs             = 5;
    
    @Option(name = { "-u", "--bulk" },
            required = false,
            description = "ForkJoin模式fork子任务数据批量（非必须），缺省2000条Message")
    public Integer       bulk             = 2000;
    
    @Option(name = { "-m", "--parallelism" },
            required = false,
            description = "Producer发送消息ForkJoin池线程容量（非必须），缺省200")
    public Integer       parallelism      = 200;
    
    private ForkJoinPool producerTaskPool = null;
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        //
        long start = System.currentTimeMillis();
        //
        producerTaskPool = new ForkJoinPool(parallelism);
        //
        ProducerTask task = ProducerTask.build(bulk,
                                               count,
                                               topicId,
                                               subs,
                                               ProducerEngine.build(ContextFactory.getProducerContext(this)));
        if (count < 0L) {
            // 如果是无限发送，那么强制subs子任务数为2，否则LIFO会导致线程数暴增，从而OOM异常
            subs = 2;
            // 无限循环标识
            task.setLoop(true);
        }
        producerTaskPool.execute(task);
        //
        while (!task.isDone()) {
            System.out.println("[" +
                               ProducerEngine.offset() +
                               "]-" +
                               producerTaskPool);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //
        long end = System.currentTimeMillis();
        try {
            System.out.println("预计发送[" +
                               count.longValue() +
                               "]条Message，实际发送[" +
                               task.get()
                                   .longValue() +
                               "]条Message，耗时：" +
                               TimeUtil.humanTime(end -
                                                  start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
}
