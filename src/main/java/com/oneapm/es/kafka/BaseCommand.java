/**
 * Project Name:ai-es-transaction-data
 * File Name:BaseCommand.java
 * Package Name:com.oneapm.es.kafka
 * Date:2016年5月3日下午11:46:22
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.kafka;

import io.airlift.airline.Option;
import io.airlift.airline.OptionType;

/**
 * ClassName:BaseCommand <br/>
 * Function: <br/>
 * Date: 2016年5月3日 下午11:46:22 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public abstract class BaseCommand implements Runnable {
    
    @Option(type = OptionType.GLOBAL,
            name = { "-b", "--broker" },
            required = false,
            description = "Broker地址列表，格式：IP:PORT,IP:PORT,IP:PORT，可选项")
    public String brokerList = "10.45.39.199:9092,10.46.177.114:9092,10.45.11.108:9092";
    
    @Option(type = OptionType.GLOBAL,
            name = { "-t", "--topic" },
            required = false,
            description = "kafka的tipic的id，可选项")
    public String topicId    = "ai_es_transaction_data";
    
}
