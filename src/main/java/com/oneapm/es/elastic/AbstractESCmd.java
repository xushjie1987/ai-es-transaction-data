/**
 * Project Name:ai-es-transaction-data
 * File Name:AbstractESCmd.java
 * Package Name:com.oneapm.es.elastic
 * Date:2016年5月21日下午4:49:56
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.elastic;

import io.airlift.airline.Option;
import io.airlift.airline.OptionType;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName:AbstractESCmd <br/>
 * Function: <br/>
 * Date: 2016年5月21日 下午4:49:56 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Getter
@Setter
public abstract class AbstractESCmd implements Runnable {
    
    @Option(type = OptionType.GROUP,
            name = { "-i", "--ip" },
            required = false,
            description = "ES集群地址，默认10.45.39.191")
    public String  clusterIP   = "10.45.39.191";
    
    @Option(type = OptionType.GROUP,
            name = { "-p", "--port" },
            required = false,
            description = "ES集群端口，默认9300")
    public Integer clusterPort = 9300;
    
    @Option(type = OptionType.GROUP,
            name = { "-n", "--name" },
            required = false,
            description = "ES集群名称，默认xintest")
    public String  clusterName = "xintest";
    
    @Option(type = OptionType.GROUP,
            name = { "-x", "--index" },
            required = false,
            description = "ES索引名称，默认metric_x")
    public String  indexName   = "metric_x";
    
    @Option(type = OptionType.GROUP,
            name = { "-t", "--type" },
            required = false,
            description = "ES索引类型名称，默认logs")
    public String  indexType   = "logs";
    
    @Option(type = OptionType.GROUP,
            name = { "-r", "--routing" },
            required = false,
            description = "ES索引文档过程是否需要使用路由，默认FALSE")
    public Boolean isRouting   = Boolean.FALSE;
    
}
