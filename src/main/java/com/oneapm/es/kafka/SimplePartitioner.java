/**
 * Project Name:ai-es-transaction-data
 * File Name:SimplePartitioner.java
 * Package Name:com.oneapm.es.kafka
 * Date:2016年4月29日下午6:50:11
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * ClassName:SimplePartitioner <br/>
 * Function: <br/>
 * Date: 2016年4月29日 下午6:50:11 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class SimplePartitioner implements Partitioner {
    public SimplePartitioner(VerifiableProperties props) {
        
    }
    
    @Override
    public int partition(Object key,
                         int numPartitions) {
        return Integer.valueOf((String) key)
                      .intValue() % numPartitions;
    }
    
}
