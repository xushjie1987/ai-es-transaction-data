/**
 * Project Name:ai-es-transaction-data
 * File Name:ProducerCommand.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月2日下午7:54:53
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import io.airlift.airline.Option;

import com.oneapm.es.kafka.BaseCommand;

/**
 * ClassName:ProducerCommand <br/>
 * Function: <br/>
 * Date: 2016年5月2日 下午7:54:53 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public abstract class ProducerCommand extends BaseCommand {
    
    @Option(name = { "-k", "--key" },
            required = false,
            description = "消息的key的类型，可选项")
    public String  keyType         = "kafka.serializer.StringEncoder";
    
    @Option(name = { "-v", "--value" },
            required = false,
            description = "消息的value的类型，可选项")
    public String  valueType       = "kafka.serializer.StringEncoder";
    
    @Option(name = { "-p", "--partitioner" },
            required = false,
            description = "partitioner的类型，可选项")
    public String  partitionerType = "com.oneapm.es.kafka.producer.SimplePartitioner";
    
    @Option(name = { "-a", "--ack" },
            required = false,
            description = "是否需要ACK响应，默认不扫描")
    public Boolean isACK           = true;
    
    @Option(name = { "-c", "--count" },
            required = false,
            description = "Producer发送消息总数（非必须），缺省-1，表示无限制")
    public Integer count           = -1;
    
}
