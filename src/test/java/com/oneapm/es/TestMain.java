/**
 * Project Name:ai-es-transaction-data
 * File Name:TestMain.java
 * Package Name:com.oneapm.es
 * Date:2016年5月3日下午10:54:26
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es;

import io.airlift.airline.Cli;
import io.airlift.airline.Cli.CliBuilder;
import io.airlift.airline.Command;
import io.airlift.airline.Help;

/**
 * ClassName:TestMain <br/>
 * Function: <br/>
 * Date: 2016年5月3日 下午10:54:26 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class TestMain {
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //
        CliBuilder<Runnable> builder = Cli.<Runnable> builder("a")
                                          .withDescription("*****")
                                          .withDefaultCommand(Help.class)
                                          .withCommands(Help.class,
                                                        B.class);
        builder.withGroup("c")
               .withDescription("*****")
               .withDefaultCommand(D.class)
               .withCommands(D.class,
                             E.class);
        builder.withGroup("f")
               .withDescription("*****")
               .withDefaultCommand(G.class)
               .withCommands(G.class,
                             H.class);
        //
        Cli<Runnable> gitParser = builder.build();
        //
        gitParser.parse(args)
                 .run();
    }
    
    @Command(name = "b",
             description = "*****")
    public static class B implements Runnable {
        
        @Override
        public void run() {
        }
        
    }
    
    @Command(name = "d",
             description = "*****")
    public static class D implements Runnable {
        
        @Override
        public void run() {
        }
        
    }
    
    @Command(name = "e",
             description = "*****")
    public static class E implements Runnable {
        
        @Override
        public void run() {
        }
        
    }
    
    @Command(name = "g",
             description = "*****")
    public static class G implements Runnable {
        
        @Override
        public void run() {
        }
        
    }
    
    @Command(name = "h",
             description = "*****")
    public static class H implements Runnable {
        
        @Override
        public void run() {
        }
        
    }
    
}
