/**
 * Project Name:ai-es-transaction-data
 * File Name:MetricDataGenerator.java
 * Package Name:com.oneapm.es.data
 * Date:2016年5月16日下午6:35:13
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

/**
 * ClassName:MetricDataGenerator <br/>
 * Function: <br/>
 * Date: 2016年5月16日 下午6:35:13 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class MetricDataGenerator extends BaseDataGenerator {
    
    public static Long                 duration        = 0L;
    
    private static final List<Boolean> b               = new ArrayList<Boolean>();
    
    private static final List<Integer> i               = new ArrayList<Integer>();
    
    private static final String[]      applicationName = new String[] { "appName_a", "appName_b", "appName_c", "appName_d",
                                                       //
                                                       "appName_e", "appName_f", "appName_g", "appName_h", "appName_i",
                                                       //
                                                       "appName_j", "appName_k", "appName_l", "appName_m", "appName_n",
                                                       //
                                                       "appName_o", "appName_p", "appName_q", "appName_r", "appName_s",
                                                       //
                                                       "appName_t", "appName_u", "appName_v", "appName_w", "appName_x",
                                                       //
                                                       "appName_y", "appName_z", "appName_1", "appName_2", "appName_3",
                                                       //
                                                       "appName_4", "appName_5", "appName_6", "appName_7", "appName_8",
                                                       //
                                                       "appName_9", "appName_10", "appName_11", "appName_12", "appName_13",
                                                       //
                                                       "appName_14", "appName_15", "appName_16", "appName_17", "appName_18",
                                                       //
                                                       "appName_19", "appName_20", "appName_21", "appName_22", "appName_23",
                                                       //
                                                       "appName_24", "appName_25", "appName_26", "appName_27", "appName_28",
                                                       //
                                                       "appName_29", "appName_30", "appName_31", "appName_32", "appName_33",
                                                       //
                                                       "appName_34", "appName_35", "appName_36", "appName_37", "appName_38",
                                                       //
                                                       "appName_39", "appName_40", "appName_41", "appName_42", "appName_43",
                                                       //
                                                       "appName_44", "appName_45", "appName_46", "appName_47", "appName_48",
                                                       //
                                                       "appName_49", "appName_50", "appName_51", "appName_52" };
    
    private static final String[]      tierName        = new String[] { "tierName_a", "tierName_b", "tierName_c", "tierName_d",
                                                       //
                                                       "tierName_e", "tierName_f", "tierName_g", "tierName_h", "tierName_i",
                                                       //
                                                       "tierName_j", "tierName_k", "tierName_l", "tierName_m", "tierName_n",
                                                       //
                                                       "tierName_o", "tierName_p", "tierName_q", "tierName_r", "tierName_s",
                                                       //
                                                       "tierName_t", "tierName_u", "tierName_v", "tierName_w", "tierName_x",
                                                       //
                                                       "tierName_y", "tierName_z" };
    
    private static final String[]      scope           = new String[] { "scope_1", "scope_2", "scope_3", "scope_4",
                                                       //
                                                       "scope_5", "scope_6", "scope_7", "scope_8", "scope_9",
                                                       //
                                                       "scope_10", "scope_11", "scope_12", "scope_13", "scope_14",
                                                       //
                                                       "scope_15", "scope_16", "scope_17", "scope_18", "scope_19",
                                                       //
                                                       "scope_20", "scope_21", "scope_22", "scope_23", "scope_24",
                                                       //
                                                       "scope_25", "scope_26", "scope_27", "scope_28", "scope_29",
                                                       //
                                                       "scope_30", "scope_31", "scope_32", "scope_33", "scope_34",
                                                       //
                                                       "scope_35", "scope_36", "scope_37", "scope_38", "scope_39",
                                                       //
                                                       "scope_40", "scope_41", "scope_42", "scope_43", "scope_44",
                                                       //
                                                       "scope_45", "scope_46", "scope_47", "scope_48", "scope_49",
                                                       //
                                                       "scope_50", "scope_51", "scope_52" };
    
    static {
        b.add(Boolean.TRUE);
        b.add(Boolean.FALSE);
        i.add(0);
        i.add(1);
    }
    
    /**
     * @see com.oneapm.es.data.DataGenerator#getTransactionData()
     */
    @Override
    public TransactionData getTransactionData() {
        TransactionData data = new TransactionData();
        String s0 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s1 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s2 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s3 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s4 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s5 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String uri = s0 +
                     "/" +
                     s1 +
                     "/" +
                     s2 +
                     "/" +
                     s3 +
                     "/" +
                     s4 +
                     "/" +
                     s5;
        data.setUri(uri);
        data.setDuration(randomInt());
        String guid = guid();
        data.setGuid(guid);
        Long timestamp = timestamp();
        data.setTimestamp(duration.longValue() == 0L
                                                    ? timestamp
                                                    : RandomUtils.nextLong(timestamp,
                                                                           timestamp +
                                                                                   duration));
        data.setErrors(randomInt());
        data.setApdex(generateFromRegex("^[A-Z]$"));
        // 0-1024
        data.setApplicationId(randomInt(1024));
        // 26
        data.setApplicationName((String) range(Arrays.asList(applicationName),
                                               String.class));
        // 0-1024
        data.setTierId(randomInt(1024));
        // 26
        data.setTierName((String) range(Arrays.asList(tierName),
                                        String.class));
        // 256
        data.setNodeId(randomInt(256));
        data.setStartTime(timestamp);
        data.setReferrerGuid(guid());
        data.setTripGuid(guid);
        // 0-32768
        data.setCallCount(randomInt(32768));
        data.setHasTrace((Boolean) range(b,
                                         Boolean.class));
        // 0-32768
        data.setExecTime(randomInt(32768));
        data.setName(uri);
        // 52
        data.setScope((String) range(Arrays.asList(scope),
                                     String.class));
        // 0-128
        data.setMetricId(randomInt(128));
        data.setIsHeader((Integer) range(i,
                                         Integer.class));
        data.setSegment0(s0);
        data.setSegment1(s1);
        data.setSegment2(s2);
        data.setSegment3(s3);
        data.setSegment4(s4);
        data.setSegment5(s5);
        return data;
    }
    
    public static void main(String[] args) {
        System.out.println(Object.class.isAssignableFrom(Integer.class));
        System.out.println(new MetricDataGenerator().getTransactionData());
        System.out.println("###########");
        System.out.println(new MetricDataGenerator().getTransactionData()
                                                    .toJSON());
        System.out.println("###########");
        System.out.println(new MetricDataGenerator().getTransactionData()
                                                    .toCVS());
        System.out.println("######带有时区的毫秒UNIX时间戳即系统本地时间戳#####");
        System.out.println(System.currentTimeMillis());
    }
    
}
