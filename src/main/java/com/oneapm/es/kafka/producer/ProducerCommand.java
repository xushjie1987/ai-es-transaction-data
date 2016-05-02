/**
 * Project Name:ai-es-transaction-data
 * File Name:ProducerCommand.java
 * Package Name:com.oneapm.es.kafka.producer
 * Date:2016年5月2日下午7:54:53
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka.producer;

import io.airlift.airline.Command;
import io.airlift.airline.Option;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.oneapm.es.data.DataGenerator;
import com.oneapm.es.data.TransactionData;

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
@Command(name = "producer",
         description = "Kafka Producer Client")
public class ProducerCommand implements Runnable {
    
    @Option(name = { "-b", "--broker" },
            required = false,
            description = "Broker地址列表，格式：IP:PORT,IP:PORT,IP:PORT，可选项")
    public String  brokerList      = "10.45.39.199:9092,10.46.177.114:9092,10.45.11.108:9092";
    
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
    
    @Option(name = { "-t", "--topic" },
            required = false,
            description = "kafka的tipic的id，可选项")
    public String  topicId         = "ai_es_transaction_data";
    
    @Option(name = { "-c", "--count" },
            required = false,
            description = "Producer发送消息总数（非必须），缺省-1，表示无限制")
    public Integer count           = -1;
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        Properties props = new Properties();
        props.put("metadata.broker.list",
                  brokerList);
        props.put("key.serializer.class",
                  keyType);
        props.put("serializer.class",
                  valueType);
        props.put("partitioner.class",
                  partitionerType);
        props.put("request.required.acks",
                  isACK.booleanValue()
                                      ? "1"
                                      : "0");
        //
        ProducerConfig config = new ProducerConfig(props);
        //
        Producer<String, String> producer = new Producer<String, String>(config);
        //
        DataGenerator dg = new DataGenerator();
        //
        int i = 0;
        while (count.intValue() < 0 ||
               i++ < Math.abs(count.intValue())) {
            TransactionData td = dg.getTransactionData();
            KeyedMessage<String, String> data = new KeyedMessage<String, String>(topicId,
                                                                                 String.valueOf(td.getApplicationId()),
                                                                                 td.toJSON());
            producer.send(data);
        }
        //
        producer.close();
    }
    
}
