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
        CliBuilder<Runnable> builder = Cli.<Runnable> builder("kfk_cli")
                                          .withDescription("Kafka producer客户端")
                                          .withDefaultCommand(Help.class)
                                          .withCommands(Help.class,
                                                        SingleProducerCmd.class);
        //
        builder.withGroup("mode")
               .withDescription("Kafka producer多线程模式")
               .withDefaultCommand(MultiProducerCmd.class)
               .withCommands(MultiProducerCmd.class,
                             PooledProducerCmd.class,
                             ForkProducerCmd.class);
        //
        Cli<Runnable> gitParser = builder.build();
        //
        gitParser.parse(args)
                 .run();
    }
    
}
