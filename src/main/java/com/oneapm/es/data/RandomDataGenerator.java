/**
 * Project Name:ai-es-transaction-data
 * File Name:RandomDataGenerator.java
 * Package Name:com.oneapm.es.data
 * Date:2016年5月16日下午6:14:47
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:RandomDataGenerator <br/>
 * Function: <br/>
 * Date: 2016年5月16日 下午6:14:47 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class RandomDataGenerator extends BaseDataGenerator {
    
    private static final List<Boolean> b = new ArrayList<Boolean>();
    
    private static final List<Integer> i = new ArrayList<Integer>();
    
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
        data.setTimestamp(timestamp);
        data.setErrors(randomInt());
        data.setApdex(generateFromRegex("^[A-Z]$"));
        data.setApplicationId(randomInt());
        data.setApplicationName(generateFromRegex("^[a-zA-Z0-9]{5,15}$"));
        data.setTierId(randomInt());
        data.setTierName(generateFromRegex("^[a-zA-Z0-9]{5,10}$"));
        data.setNodeId(randomInt(100));
        data.setStartTime(timestamp);
        data.setReferrerGuid(guid());
        data.setTripGuid(guid);
        data.setCallCount(randomInt());
        data.setHasTrace((Boolean) range(b,
                                         Boolean.class));
        data.setExecTime(randomInt());
        data.setName(uri);
        data.setScope(generateFromRegex("^[a-zA-Z0-9]{5,15}$"));
        data.setMetricId(randomInt(-1,
                                   65536));
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
        System.out.println(new RandomDataGenerator().getTransactionData());
        System.out.println("###########");
        System.out.println(new RandomDataGenerator().getTransactionData()
                                                    .toJSON());
        System.out.println("###########");
        System.out.println(new RandomDataGenerator().getTransactionData()
                                                    .toCVS());
    }
    
}
