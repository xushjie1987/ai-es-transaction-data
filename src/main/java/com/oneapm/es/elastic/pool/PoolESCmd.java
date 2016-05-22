/**
 * Project Name:ai-es-transaction-data
 * File Name:PoolESCmd.java
 * Package Name:com.oneapm.es.elastic.pool
 * Date:2016年5月21日下午5:01:31
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.elastic.pool;

import io.airlift.airline.Command;
import io.airlift.airline.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;

import com.oneapm.es.data.TransactionData;
import com.oneapm.es.elastic.AbstractESCmd;
import com.oneapm.es.elastic.engine.ElasticEngine;
import com.oneapm.es.elastic.engine.ElasticEngineFactory;
import com.oneapm.es.util.TimeUtil;

/**
 * ClassName:PoolESCmd <br/>
 * Function: <br/>
 * Date: 2016年5月21日 下午5:01:31 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Getter
@Setter
@Command(name = "pool",
         description = "Pooled线程池ES客户端")
public class PoolESCmd extends AbstractESCmd {
    
    @Option(name = { "-f", "--prefix" },
            required = false,
            description = "ES索引名称前缀，默认metric")
    public String           indexPrefix = "metric";
    
    @Option(name = { "-s", "--suffix" },
            required = false,
            description = "ES索引名称后缀，默认x")
    public String           indexSuffix = "x";
    
    @Option(name = { "-h", "--threads" },
            required = false,
            description = "ES客户端线程数，默认100")
    public Integer          nThreads    = 100;
    
    @Option(name = { "-b", "--bulk" },
            required = false,
            description = "ES索引文档bulk数据量，不要设置太高，否则fail数量太多，默认200")
    public Long             bulk        = 200L;
    
    @Option(name = { "-m", "--mini" },
            required = false,
            description = "ES索引文档bulk接口API数据量，不要设置太高，否则fail数量太多，务必小于bulk，默认50")
    public Long             miniBulk    = 50L;
    
    @Option(name = { "-c", "--count" },
            required = false,
            description = "ES索引文档总量，默认100")
    public Long             count       = 10000L;
    
    @Option(name = { "-d", "--duration" },
            required = false,
            description = "ES索引文档时间戳时间范围，默认3d")
    public String           duration    = "3d";
    
    @SuppressWarnings("rawtypes")
    private ElasticEngine   engine;
    
    private ExecutorService pool;
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        //
        long start = System.currentTimeMillis();
        //
        engine = ElasticEngineFactory.buildPooledEngine(this);
        //
        pool = Executors.newFixedThreadPool(nThreads);
        //
        long total = count.longValue();
        while (total > 0L) {
            pool.execute(buildTask(total > bulk.longValue()
                                                           ? bulk.longValue()
                                                           : total));
            total -= bulk.longValue();
        }
        // show process
        Thread monitor = new Thread(new Runnable() {
            @Override
            public void run() {
                //
                while (!pool.isTerminated()) {
                    long current = engine.sumAll()
                                         .longValue();
                    System.out.println("[" +
                                       current +
                                       "]条文档已经被索引，当前已经耗时[" +
                                       TimeUtil.humanTime(System.currentTimeMillis() -
                                                          start) +
                                       "]，平均速度[" +
                                       ((double) current / (double) (System.currentTimeMillis() - start)) *
                                       1000L +
                                       "条/秒].");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        monitor.start();
        //
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE,
                                  TimeUnit.DAYS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        //
        long end = System.currentTimeMillis();
        //
        try {
            monitor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //
        System.out.println("预计索引[" +
                           count +
                           "]条文档，实际索引[" +
                           engine.sumAll() +
                           "]条文档，耗时[" +
                           TimeUtil.humanTime(end -
                                              start) +
                           "]，平均速度[" +
                           (double) (engine.sumAll().longValue()) /
                           (double) (end - start) *
                           1000L +
                           "条/秒].");
    }
    
    /**
     * buildTask: <br/>
     * 
     * @author xushjie
     * @param bulk
     * @return
     * @since JDK 1.7
     */
    private Runnable buildTask(long bulk) {
        //
        return new Runnable() {
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                //
                if (bulk <= 0) {
                    return;
                }
                //
                List<TransactionData> batch = new ArrayList<TransactionData>();
                for (long i = 0; i < bulk; i++) {
                    batch.add((TransactionData) engine.getData());
                }
                //
                Long count = (Long) engine.sendBulk(batch);
                //
                engine.plusBulk(count);
            }
        };
    }
    
}
