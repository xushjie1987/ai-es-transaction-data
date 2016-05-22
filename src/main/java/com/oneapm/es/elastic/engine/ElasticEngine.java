/**
 * Project Name:ai-es-transaction-data
 * File Name:ElasticEngine.java
 * Package Name:com.oneapm.es.elastic.engine
 * Date:2016年5月21日下午5:33:45
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.elastic.engine;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.LongAdder;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.oneapm.es.data.DataGenerator;
import com.oneapm.es.data.MetricDataGenerator;
import com.oneapm.es.elastic.AbstractESCmd;
import com.oneapm.es.engine.Engine;

/**
 * ClassName:ElasticEngine <br/>
 * Function: <br/>
 * Date: 2016年5月21日 下午5:33:45 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public abstract class ElasticEngine<ONE, OTYPE, BULK, BTYPE> implements Engine<ONE, OTYPE, BULK, BTYPE> {
    
    protected Settings            settings = null;
    
    protected Client              client   = null;
    
    protected final DataGenerator source   = new MetricDataGenerator();
    
    protected final LongAdder     sumCount = new LongAdder();
    
    /**
     * custome: <br/>
     * 
     * @author xushjie
     * @param cmd
     * @since JDK 1.7
     */
    protected abstract void custome(AbstractESCmd cmd);
    
    /**
     * getData: <br/>
     * 
     * @author xushjie
     * @return
     * @since JDK 1.7
     */
    public abstract OTYPE getData();
    
    /**
     * @see com.oneapm.es.engine.Engine#sumAll()
     */
    @Override
    public Long sumAll() {
        return Long.valueOf(sumCount.sum());
    }
    
    /**
     * @see com.oneapm.es.engine.Engine#plusBulk(java.lang.Long)
     */
    @Override
    public void plusBulk(Long bulk) {
        sumCount.add(bulk.longValue());
    }
    
    /**
     * Creates a new instance of ElasticEngine.
     * 
     * @param cluster
     * @param ip
     * @param port
     * @param cmd
     */
    public ElasticEngine(String cluster,
                         String ip,
                         Integer port,
                         AbstractESCmd cmd) {
        //
        settings = Settings.settingsBuilder()
                           .put("cluster.name",
                                cluster)
                           .build();
        //
        try {
            client = TransportClient.builder()
                                    .settings(settings)
                                    .build()
                                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip),
                                                                                        port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //
        custome(cmd);
    }
    
}
