/**
 * Project Name:ai-es-transaction-data
 * File Name:ProducerTask.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月4日下午10:05:19
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName:ProducerTask <br/>
 * Function: <br/>
 * Date: 2016年5月4日 下午10:05:19 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Setter
@Getter
public class ProducerTask extends RecursiveTask<Long> {
    
    /**
     * serialVersionUID:
     * 
     * @since JDK 1.7
     */
    private static final long serialVersionUID = -1493143104960663567L;
    
    private Integer           bulk;
    
    private Long              count;
    
    private String            topicId;
    
    private Integer           subs;
    
    private Boolean           loop             = false;
    
    private ProducerEngine    engine;
    
    /**
     * build: <br/>
     * 
     * @author xushjie
     * @param bulk
     * @param count
     * @param topicId
     * @param subs
     * @param engine
     * @return
     * @since JDK 1.7
     */
    public static ProducerTask build(Integer bulk,
                                     Long count,
                                     String topicId,
                                     Integer subs,
                                     ProducerEngine engine) {
        return new ProducerTask(Math.abs(bulk),
                                count,
                                topicId,
                                Math.abs(subs),
                                engine);
    }
    
    /**
     * build: <br/>
     * 
     * @author xushjie
     * @param parent
     * @return
     * @since JDK 1.7
     */
    public static List<ProducerTask> build(ProducerTask parent) {
        //
        List<ProducerTask> tasks = new ArrayList<ProducerTask>();
        //
        long total = parent.getCount() -
                     parent.getBulk() > 0L
                                          ? parent.getCount() -
                                            parent.getBulk()
                                          : 0L;
        if (total == 0) {
            return tasks;
        }
        //
        long subCount = total /
                        parent.getSubs() == 0L
                                              ? 1L
                                              : total /
                                                parent.getSubs();
        //
        for (int i = 0; i < parent.getSubs(); i++) {
            ProducerTask subTask = parent.getLoop()
                                         .booleanValue()
                                                        ? ProducerTask.build(parent.getBulk(),
                                                                             parent.getCount(),
                                                                             parent.getTopicId(),
                                                                             parent.getSubs(),
                                                                             ProducerEngine.build())
                                                        : ProducerTask.build(parent.getBulk(),
                                                                             (total -= subCount) >= subCount
                                                                                                            ? subCount
                                                                                                            : total >= 0
                                                                                                                        ? subCount +
                                                                                                                          total
                                                                                                                        : 0,
                                                                             parent.getTopicId(),
                                                                             parent.getSubs(),
                                                                             ProducerEngine.build());
            tasks.add(subTask);
        }
        return tasks;
    }
    
    /**
     * @see java.util.concurrent.RecursiveTask#compute()
     */
    @Override
    protected Long compute() {
        // sub jobs
        List<ProducerTask> subs = build(this);
        // fork sub jobs
        for (ProducerTask s : subs) {
            if (s.getCount() > 0L) {
                s.fork();
            }
        }
        // own job
        long total = engine.sendBulk(topicId,
                                     bulk > count
                                                 ? count
                                                 : bulk);
        engine.plusBulk(total);
        // join sub jobs
        Long count = total;
        for (ProducerTask s : subs) {
            if (s.getCount() > 0L) {
                count += s.join();
            }
        }
        return count;
    }
    
    /**
     * Creates a new instance of ProducerTask.
     * 
     * @param bulk
     * @param count
     * @param topicId
     * @param subs
     * @param engine
     */
    public ProducerTask(Integer bulk,
                        Long count,
                        String topicId,
                        Integer subs,
                        ProducerEngine engine) {
        this.bulk = bulk;
        this.count = count;
        this.topicId = topicId;
        this.subs = subs;
        this.engine = engine;
    }
    
    public static void main(String[] args) {
        //
        {
            int total = 10;
            int subs = 3;
            int subCount = total /
                           subs == 0
                                    ? 1
                                    : total /
                                      subs;
            List<Integer> vec = new ArrayList<Integer>();
            for (int i = 0; i < subs; i++) {
                vec.add((total -= subCount) >= subCount
                                                       ? subCount
                                                       : total >= 0
                                                                   ? subCount +
                                                                     total
                                                                   : 0);
            }
            System.out.println("no.1");
        }
        //
        {
            int total = 3;
            int subs = 5;
            int subCount = total /
                           subs == 0
                                    ? 1
                                    : total /
                                      subs;
            List<Integer> vec = new ArrayList<Integer>();
            for (int i = 0; i < subs; i++) {
                vec.add((total -= subCount) >= subCount
                                                       ? subCount
                                                       : total >= 0
                                                                   ? subCount +
                                                                     total
                                                                   : 0);
            }
            System.out.println("no.2");
        }
        //
        {
            int total = 10000000;
            int subs = 3;
            int subCount = total /
                           subs == 0
                                    ? 1
                                    : total /
                                      subs;
            List<Integer> vec = new ArrayList<Integer>();
            for (int i = 0; i < subs; i++) {
                vec.add((total -= subCount) >= subCount
                                                       ? subCount
                                                       : total >= 0
                                                                   ? subCount +
                                                                     total
                                                                   : 0);
            }
            System.out.println("no.3");
        }
        //
        {
            int total = 1000;
            int subs = 200;
            int subCount = total /
                           subs == 0
                                    ? 1
                                    : total /
                                      subs;
            List<Integer> vec = new ArrayList<Integer>();
            for (int i = 0; i < subs; i++) {
                vec.add((total -= subCount) >= subCount
                                                       ? subCount
                                                       : total >= 0
                                                                   ? subCount +
                                                                     total
                                                                   : 0);
            }
            System.out.println("no.4");
        }
    }
    
}
