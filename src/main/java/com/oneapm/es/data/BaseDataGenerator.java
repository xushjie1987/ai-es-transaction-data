/**
 * Project Name:ai-es-transaction-data
 * File Name:BaseDataGenerator.java
 * Package Name:com.oneapm.es.data
 * Date:2016年4月29日下午4:00:37
 * Copyright (c) 2016, All Rights Reserved.
 *
 */
package com.oneapm.es.data;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:BaseDataGenerator <br/>
 * Function: <br/>
 * Date: 2016年4月29日 下午4:00:37 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public abstract class BaseDataGenerator implements DataGenerator {
    
    private Random random;
    
    /**
     * Creates a new instance of BaseDataGenerator.
     */
    public BaseDataGenerator() {
        random = new Random(System.currentTimeMillis());
    }
    
    /**
     * guid: <br/>
     * @author xushjie
     * @return
     * @since JDK 1.7
     */
    public String guid() {
        return UUID.randomUUID()
                   .toString()
                   .replaceAll("-",
                               "");
    }
    
    /**
     * range: <br/>
     * @author xushjie
     * @param range
     * @param clz
     * @return
     * @since JDK 1.7
     */
    public Object range(List<?> range,
                        Class<?> clz) {
        try {
            if (null == range ||
                range.size() == 0) {
                return clz.newInstance();
            }
            return clz.cast(range.get(random.nextInt(range.size())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * timestamp: <br/>
     * @author xushjie
     * @return
     * @since JDK 1.7
     */
    public Long timestamp() {
        return System.currentTimeMillis();
    }
    
    /**
     * randomInt: <br/>
     * @author xushjie
     * @return
     * @since JDK 1.7
     */
    public Integer randomInt() {
        return randomInt(0,
                         65536);
    }
    
    /**
     * randomInt: <br/>
     * @author xushjie
     * @param max
     * @return
     * @since JDK 1.7
     */
    public Integer randomInt(int max) {
        return randomInt(0,
                         max);
    }
    
    /**
     * randomInt: <br/>
     * @author xushjie
     * @param min
     * @param max
     * @return
     * @since JDK 1.7
     */
    public Integer randomInt(int min,
                             int max) {
        if (min >= max) {
            return 0;
        }
        return random.nextInt(max -
                              min) +
               min;
    }
    
    /**
     * generateFromRegex: <br/>
     * @author xushjie
     * @param regex
     * @return
     * @since JDK 1.7
     */
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
                int n = random.nextInt(95 - i) +
                        i;
                
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
    
}
