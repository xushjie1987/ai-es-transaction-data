/**
 * Project Name:ai-es-transaction-data
 * File Name:ProducerContext.java
 * Package Name:com.oneapm.es.kafka.context
 * Date:2016年5月8日下午4:17:03
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.context;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.concurrent.atomic.LongAdder;

import kafka.producer.ProducerConfig;
import lombok.Getter;
import lombok.Setter;

import com.oneapm.es.data.DataGenerator;
import com.oneapm.es.data.MetricDataGenerator;
import com.oneapm.es.kafka.producer.ProducerCommand;
import com.oneapm.es.util.TimeUtil;

/**
 * ClassName:ProducerContext <br/>
 * Function: <br/>
 * Date: 2016年5月8日 下午4:17:03 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Getter
@Setter
public class ProducerContext {
    
    private ProducerConfig       config;
    
    private Class<DataGenerator> dgClazz;
    
    private LongAdder            sumCount = new LongAdder();
    
    /**
     * build: <br/>
     * 
     * @author xushjie
     * @param option
     * @return
     * @since JDK 1.7
     */
    @SuppressWarnings("unchecked")
    public static ProducerContext build(ProducerCommand option) {
        //
        Properties props = new Properties();
        props.put("metadata.broker.list",
                  option.getBrokerList());
        props.put("key.serializer.class",
                  option.getKeyType());
        props.put("serializer.class",
                  option.getValueType());
        props.put("partitioner.class",
                  option.getPartitionerType());
        props.put("request.required.acks",
                  option.getIsACK()
                        .booleanValue()
                                       ? "1"
                                       : "0");
        //
        ProducerContext context = new ProducerContext();
        context.config = new ProducerConfig(props);
        try {
            context.dgClazz = (Class<DataGenerator>) Class.forName(option.getGenerator(),
                                                                   true,
                                                                   Thread.currentThread()
                                                                         .getContextClassLoader());
            // 对于MetricDataGenerator类型，特殊处理duration字段
            if (MetricDataGenerator.class.isAssignableFrom(context.dgClazz)) {
                Field f = context.dgClazz.getDeclaredField("duration");
                f.setAccessible(true);
                f.set(null,
                      TimeUtil.calcDuration(option.getDuration()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载" +
                               option.getGenerator() +
                               "数据生成器类失败!");
            context.dgClazz = null;
        } catch (Exception e) {
            // ignore
        }
        //
        return context;
    }
    
    /**
     * plusPlus: <br/>
     * @author xushjie
     * @since JDK 1.7
     */
    public void plusPlus() {
        sumCount.increment();
    }
    
    /**
     * plusBulk: <br/>
     * @author xushjie
     * @param bulk
     * @since JDK 1.7
     */
    public void plusBulk(long bulk) {
        sumCount.add(bulk);
    }
    
    /**
     * sumAll: <br/>
     * @author xushjie
     * @return
     * @since JDK 1.7
     */
    public long sumAll() {
        return sumCount.sum();
    }
    
}
