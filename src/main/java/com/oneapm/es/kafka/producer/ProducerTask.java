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
    
    private Integer           count;
    
    private String            topicId;
    
    private ProducerEngine    engine;
    
    /**
     * build: <br/>
     * 
     * @author xushjie
     * @param engine
     * @param bulk
     * @param count
     * @param topicId
     * @return
     * @since JDK 1.7
     */
    public static ProducerTask build(ProducerEngine engine,
                                     Integer bulk,
                                     Integer count,
                                     String topicId) {
        return new ProducerTask(Math.abs(bulk),
                                count,
                                topicId,
                                engine);
    }
    
    public static List<ProducerTask> build(ProducerTask parent, int subs) {
        List<ProducerTask> tasks = new ArrayList<ProducerTask>();
        if (parent.getCount().intValue() < 0) {
            tasks.add(build(parent.engine, parent.bulk, parent.count.intValue() < 0 ? parent.count : parent., parent.topicId));
            tasks.add(build(parent.engine, parent.bulk, parent.count, parent.topicId));
        } else {
            int total = parent.getCount().intValue();
            while (total > 0) {
                subs.add
            }
        }
        return subs;
    }
    
    /**
     * @see java.util.concurrent.RecursiveTask#compute()
     */
    @Override
    protected Long compute() {
        //
        if (count.intValue() < 0) {
            
        }
        // own job
        int total = engine.sendBulk(topicId,
                                    bulk > count
                                                ? count
                                                : bulk);
        // sub jobs
        
        //
        return null;
    }
    
    /**
     * Creates a new instance of ProducerTask.
     * 
     * @param bulk
     * @param count
     * @param topicId
     * @param engine
     */
    
    public ProducerTask(Integer bulk,
                        Integer count,
                        String topicId,
                        ProducerEngine engine) {
        this.bulk = bulk;
        this.count = count;
        this.topicId = topicId;
        this.engine = engine;
    }
    
}
