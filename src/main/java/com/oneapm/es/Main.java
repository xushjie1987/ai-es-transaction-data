/**
 * Project Name:ai-es-transaction-data
 * File Name:Main.java
 * Package Name:com.oneapm.es
 * Date:2016年5月2日下午7:41:38
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es;

import io.airlift.airline.Cli;
import io.airlift.airline.Cli.CliBuilder;
import io.airlift.airline.Help;

import com.oneapm.es.data.console.ConsoleDataCmd;
import com.oneapm.es.elastic.pool.PoolESCmd;
import com.oneapm.es.kafka.producer.ForkProducerCmd;
import com.oneapm.es.kafka.producer.MultiProducerCmd;
import com.oneapm.es.kafka.producer.PooledProducerCmd;
import com.oneapm.es.kafka.producer.SingleProducerCmd;

/**
 * ClassName:Main <br/>
 * Function: <br/>
 * Date: 2016年5月2日 下午7:41:38 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class Main {
    
    /**
     * producer -c 10 <br>
     * main: <br/>
     * 
     * @author xushjie
     * @param args
     * @since JDK 1.7
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //
        CliBuilder<Runnable> builder = Cli.<Runnable> builder("XCli")
                                          .withDescription("XCli客户端")
                                          .withDefaultCommand(Help.class)
                                          .withCommand(Help.class);
        // data generator
        builder.withGroup("data")
               .withDescription("Data Generator XCli客户端")
               .withDefaultCommand(ConsoleDataCmd.class)
               .withCommands(ConsoleDataCmd.class);
        // kafka-0.8.0
        builder.withGroup("kafka")
               .withDescription("Kafka XCli客户端")
               .withDefaultCommand(MultiProducerCmd.class)
               .withCommands(SingleProducerCmd.class,
                             MultiProducerCmd.class,
                             PooledProducerCmd.class,
                             ForkProducerCmd.class);
        // es-2.1.1
        builder.withGroup("elastic")
               .withDescription("ElasticSearch XCli客户端")
               .withDefaultCommand(PoolESCmd.class)
               .withCommands(PoolESCmd.class);
        //
        Cli<Runnable> gitParser = builder.build();
        //
        gitParser.parse(args)
                 .run();
    }
}
