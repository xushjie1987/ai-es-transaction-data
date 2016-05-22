/**
 * Project Name:ai-es-transaction-data
 * File Name:TransactionData.java
 * Package Name:com.oneapm.es.data
 * Date:2016年4月29日下午6:02:58
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName:TransactionData <br/>
 * Function: <br/>
 * Date: 2016年4月29日 下午6:02:58 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Getter
@Setter
@ToString
public class TransactionData implements JsonAble, CvsAble {
    
    private String  uri;
    
    private Integer duration;
    
    private String  guid;
    
    private Long    timestamp;
    
    private Integer errors;
    
    private String  apdex;
    
    private Integer applicationId;
    
    private String  applicationName;
    
    private Integer tierId;
    
    private String  tierName;
    
    private Integer nodeId;
    
    private Long    startTime;
    
    private String  referrerGuid;
    
    private String  tripGuid;
    
    private Integer callCount;
    
    private Boolean hasTrace;
    
    private Integer execTime;
    
    private String  name;
    
    private String  scope;
    
    private Integer metricId;
    
    private Integer isHeader;
    
    private String  segment0;
    
    private String  segment1;
    
    private String  segment2;
    
    private String  segment3;
    
    private String  segment4;
    
    private String  segment5;
    
    @Override
    public String toJSON() {
        return gson.toJson(this,
                           TransactionData.class);
    }
    
    @Override
    public String toCVS() {
        return new StringBuffer().append(uri)
                                 .append(",")
                                 .append(duration)
                                 .append(",")
                                 .append(guid)
                                 .append(",")
                                 .append(timestamp)
                                 .append(",")
                                 .append(errors)
                                 .append(",")
                                 .append(apdex)
                                 .append(",")
                                 .append(applicationId)
                                 .append(",")
                                 .append(applicationName)
                                 .append(",")
                                 .append(tierId)
                                 .append(",")
                                 .append(tierName)
                                 .append(",")
                                 .append(nodeId)
                                 .append(",")
                                 .append(startTime)
                                 .append(",")
                                 .append(referrerGuid)
                                 .append(",")
                                 .append(tripGuid)
                                 .append(",")
                                 .append(callCount)
                                 .append(",")
                                 .append(hasTrace)
                                 .append(",")
                                 .append(execTime)
                                 .append(",")
                                 .append(name)
                                 .append(",")
                                 .append(scope)
                                 .append(",")
                                 .append(metricId)
                                 .append(",")
                                 .append(isHeader)
                                 .append(",")
                                 .append(segment0)
                                 .append(",")
                                 .append(segment1)
                                 .append(",")
                                 .append(segment2)
                                 .append(",")
                                 .append(segment3)
                                 .append(",")
                                 .append(segment4)
                                 .append(",")
                                 .append(segment5)
                                 .toString();
    }
    
}
