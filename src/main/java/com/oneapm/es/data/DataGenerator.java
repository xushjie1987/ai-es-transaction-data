/**
 * Project Name:ai-es-transaction-data
 * File Name:DataGenerator.java
 * Package Name:com.oneapm.es.data
 * Date:2016年4月29日下午4:00:37
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:DataGenerator <br/>
 * Function: <br/>
 * Date: 2016年4月29日 下午4:00:37 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class DataGenerator {
    
    private static final List<Boolean> b = new ArrayList<Boolean>();
    
    private static final List<Integer> i = new ArrayList<Integer>();
    
    static {
        b.add(Boolean.TRUE);
        b.add(Boolean.FALSE);
        i.add(0);
        i.add(1);
    }
    
    private Random                     random;
    
    public DataGenerator() {
        random = new Random(System.currentTimeMillis());
    }
    
    public String guid() {
        return UUID.randomUUID()
                   .toString()
                   .replaceAll("-",
                               "");
    }
    
    public Object range(List<?> range,
                        Class<?> clz) {
        try {
            if (null == range || range.size() == 0) {
                return clz.newInstance();
            }
            return clz.cast(range.get(random.nextInt(range.size())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Long timestamp() {
        return System.currentTimeMillis();
    }
    
    public Integer randomInt() {
        return randomInt(0,
                         65536);
    }
    
    public Integer randomInt(int max) {
        return randomInt(0,
                         max);
    }
    
    public Integer randomInt(int min,
                             int max) {
        if (min >= max) {
            return 0;
        }
        return random.nextInt(max - min) + min;
    }
    
    public String generateFromRegex(String regex) {
        StringBuilder b = new StringBuilder();
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("");
        
        int[] cypher = new int[95];
        boolean done = false;
        
        // start from an empty string and grow a solution
        while (!done) {
            // make a cypher to jumble the order letters are tried
            for (int i = 0; i < 95; i++) {
                cypher[i] = i;
            }
            
            for (int i = 0; i < 95; i++) {
                int n = random.nextInt(95 - i) + i;
                
                int t = cypher[n];
                cypher[n] = cypher[i];
                cypher[i] = t;
            }
            
            // try and grow partial solution using an extra letter on the end
            for (int i = 0; i < 95; i++) {
                int n = cypher[i] + 32;
                b.append((char) n);
                
                String result = b.toString();
                m.reset(result);
                if (m.matches()) { // complete solution found
                    // don't try to expand to a larger solution
                    if (!random.nextBoolean()) {
                        done = true;
                    }
                    
                    break;
                } else if (m.hitEnd()) { // prefix to a solution found, keep this letter
                    break;
                } else { // dead end found, try a new character at the end
                    b.deleteCharAt(b.length() - 1);
                    
                    // no more possible characters to try and expand with - stop
                    if (i == 94) {
                        done = true;
                    }
                }
            }
        }
        return b.toString();
    }
    
    public TransactionData getTransactionData() {
        TransactionData data = new TransactionData();
        String s0 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s1 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s2 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s3 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s4 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String s5 = generateFromRegex("^[a-zA-Z0-9]{5,10}$");
        String uri = s0 + "/" + s1 + "/" + s2 + "/" + s3 + "/" + s4 + "/" + s5;
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
        System.out.println(new DataGenerator().getTransactionData());
        System.out.println("###########");
        System.out.println(new DataGenerator().getTransactionData()
                                              .toJSON());
        System.out.println("###########");
        System.out.println(new DataGenerator().getTransactionData()
                                              .toCVS());
    }
}
