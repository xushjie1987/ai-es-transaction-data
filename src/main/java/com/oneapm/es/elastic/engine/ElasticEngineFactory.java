/**
 * Project Name:ai-es-transaction-data
 * File Name:ElasticEngineFactory.java
 * Package Name:com.oneapm.es.elastic.engine
 * Date:2016年5月21日下午5:35:23
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.elastic.engine;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.oneapm.es.data.MetricDataGenerator;
import com.oneapm.es.data.TransactionData;
import com.oneapm.es.elastic.AbstractESCmd;
import com.oneapm.es.elastic.pool.PoolESCmd;
import com.oneapm.es.util.TimeUtil;

/**
 * ClassName:ElasticEngineFactory <br/>
 * Function: <br/>
 * Date: 2016年5月21日 下午5:35:23 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class ElasticEngineFactory {
    
    /**
     * buildPooledEngine: <br/>
     * 
     * @author xushjie
     * @param cmd
     * @return
     * @since JDK 1.7
     */
    public static ElasticEngine<Long, TransactionData, Long, List<TransactionData>> buildPooledEngine(PoolESCmd cmd) {
        //
        return new ElasticEngine<Long, TransactionData, Long, List<TransactionData>>(cmd.getClusterName(),
                                                                                     cmd.getClusterIP(),
                                                                                     cmd.getClusterPort(),
                                                                                     cmd) {
            // 定制化配置
            private PoolESCmd specSettings;
            
            /**
             * @see com.oneapm.es.engine.Engine#sendOne(java.lang.Object)
             */
            @Override
            public Long sendOne(TransactionData data) {
                assert client != null;
                //
                DateTime date = new DateTime(data.getTimestamp(),
                                             DateTimeZone.UTC);
                //
                IndexResponse resp = client.prepareIndex(specSettings.getIndexPrefix() +
                                                                 "-" +
                                                                 date.year()
                                                                     .get() +
                                                                 "." +
                                                                 date.monthOfYear()
                                                                     .get() +
                                                                 "." +
                                                                 date.dayOfMonth()
                                                                     .get() +
                                                                 "-" +
                                                                 specSettings.getIndexSuffix(),
                                                         specSettings.getIndexType())
                                           .setRouting(specSettings.getIsRouting()
                                                                   .booleanValue()
                                                                                  ? date.getHourOfDay() +
                                                                                    "h"
                                                                                  : "")
                                           .setSource(getJsonBuilder(data))
                                           .get();
                //
                if (resp.isCreated()) {
                    return 1L;
                }
                return 0L;
            }
            
            /**
             * @see com.oneapm.es.engine.Engine#sendBulk(java.lang.Object)
             */
            @Override
            public Long sendBulk(List<TransactionData> datas) {
                assert client != null;
                //
                if (datas == null ||
                    0 == datas.size()) {
                    return 0L;
                }
                //
                BulkRequestBuilder bulkRequest = client.prepareBulk();
                //
                long mini = 0L;
                long count = 0L;
                for (TransactionData data : datas) {
                    DateTime date = new DateTime(data.getTimestamp(),
                                                 DateTimeZone.UTC);
                    //
                    bulkRequest.add(client.prepareIndex(specSettings.getIndexPrefix() +
                                                                "-" +
                                                                date.year()
                                                                    .get() +
                                                                "." +
                                                                date.monthOfYear()
                                                                    .get() +
                                                                "." +
                                                                date.dayOfMonth()
                                                                    .get() +
                                                                "-" +
                                                                specSettings.getIndexSuffix(),
                                                        specSettings.getIndexType())
                                          .setRouting(specSettings.getIsRouting()
                                                                  .booleanValue()
                                                                                 ? date.getHourOfDay() +
                                                                                   "h"
                                                                                 : "")
                                          .setSource(getJsonBuilder(data)));
                    // 分成若干个微小的bulk，做为底层bulk的API数据量
                    if (++mini >= (specSettings.getMiniBulk()
                                               .longValue() > specSettings.getBulk()
                                                                          .longValue()
                                                                                      ? specSettings.getBulk()
                                                                                                    .longValue()
                                                                                      : specSettings.getMiniBulk()
                                                                                                    .longValue())) {
                        // 置0，为了下一次循环
                        mini = 0L;
                        // 计数成功个数
                        BulkResponse resp = bulkRequest.get();
                        if (resp.hasFailures()) {
                            for (BulkItemResponse item : resp.getItems()) {
                                if (!item.isFailed()) {
                                    count++;
                                }
                            }
                        }
                    }
                }
                //
                BulkResponse resp = bulkRequest.get();
                if (resp.hasFailures()) {
                    for (BulkItemResponse item : resp.getItems()) {
                        if (!item.isFailed()) {
                            count++;
                        }
                    }
                }
                //
                return count > 0L
                                 ? count
                                 : Integer.valueOf(datas.size())
                                          .longValue();
            }
            
            /**
             * @see com.oneapm.es.elastic.engine.ElasticEngine#custome(com.oneapm.es.elastic.AbstractESCmd)
             */
            @Override
            protected void custome(AbstractESCmd cmd) {
                //
                specSettings = (PoolESCmd) cmd;
                //
                MetricDataGenerator.duration = TimeUtil.calcDuration(specSettings.getDuration());
            }
            
            /**
             * getJsonBuilder: <br/>
             * 
             * @author xushjie
             * @param data
             * @return
             * @since JDK 1.7
             */
            private XContentBuilder getJsonBuilder(TransactionData data) {
                //
                DateTimeFormatter formatter = ISODateTimeFormat.dateTime()
                                                               .withZoneUTC();
                //
                XContentBuilder builder = null;
                try {
                    builder = XContentFactory.jsonBuilder()
                                             .startObject()
                                             .field("uri",
                                                    data.getUri())
                                             .field("duration",
                                                    data.getDuration())
                                             .field("guid",
                                                    data.getGuid())
                                             .field("timestamp",
                                                    data.getTimestamp())
                                             .field("errors",
                                                    data.getErrors())
                                             .field("apdex",
                                                    data.getApdex())
                                             .field("applicationId",
                                                    data.getApplicationId())
                                             .field("applicationName",
                                                    data.getApplicationName())
                                             .field("tierId",
                                                    data.getTierId())
                                             .field("tierName",
                                                    data.getTierName())
                                             .field("nodeId",
                                                    data.getNodeId())
                                             .field("startTime",
                                                    data.getStartTime())
                                             .field("referrerGuid",
                                                    data.getReferrerGuid())
                                             .field("tripGuid",
                                                    data.getTripGuid())
                                             .field("callCount",
                                                    data.getCallCount())
                                             .field("hasTrace",
                                                    data.getHasTrace())
                                             .field("execTime",
                                                    data.getExecTime())
                                             .field("name",
                                                    data.getName())
                                             .field("scope",
                                                    data.getScope())
                                             .field("metricId",
                                                    data.getMetricId())
                                             .field("isHeader",
                                                    data.getIsHeader())
                                             .field("segment0",
                                                    data.getSegment0())
                                             .field("segment1",
                                                    data.getSegment1())
                                             .field("segment2",
                                                    data.getSegment2())
                                             .field("segment3",
                                                    data.getSegment3())
                                             .field("segment4",
                                                    data.getSegment4())
                                             .field("segment5",
                                                    data.getSegment5())
                                             .field("@version",
                                                    "1")
                                             .field("@timestamp",
                                                    formatter.print(data.getTimestamp()
                                                                        .longValue()))
                                             .field("@uuid",
                                                    UUID.randomUUID()
                                                        .toString())
                                             .endObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return builder;
            }
            
            /**
             * @see com.oneapm.es.elastic.engine.ElasticEngine#getData()
             */
            @Override
            public TransactionData getData() {
                return source.getTransactionData();
            }
            
        };
    }
}
