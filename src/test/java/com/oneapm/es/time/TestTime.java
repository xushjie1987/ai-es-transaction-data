/**
 * Project Name:ai-es-transaction-data
 * File Name:TestTime.java
 * Package Name:com.oneapm.es.time
 * Date:2016年5月20日下午3:58:27
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

/**
 * ClassName:TestTime <br/>
 * Function: <br/>
 * Date: 2016年5月20日 下午3:58:27 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class TestTime {
    
    /**
     * testTime01: <br/>
     * Wed May 18 00:00:00 CST 2016
     * 
     * @author xushjie
     * @since JDK 1.7
     */
    @Test
    public void testTime01() {
        Date d = new Date(1463500800000L);
        System.out.println(d);
    }
    
    /**
     * testTime02: <br/>
     * GMT vs. CST
     * @author xushjie
     * @throws ParseException
     * @since JDK 1.7
     */
    @Test
    public void testTime02() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date d = sdf.parse("2016-05-17 00:00:00");
        System.out.println("格林威治时间：" +
                           sdf.format(d));
        System.out.println("UNIX时间戳：" +
                           d.getTime());
        System.out.println("北京地区时间：" +
                           d);
    }
    
    /**
     * testTime03: <br/>
     * 
     * @author xushjie
     * @throws ParseException
     * @since JDK 1.7
     */
    @Test
    public void testTime03() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        //
        Date d = sdf.parse("2016-05-17 00:00:00");
        System.out.println("2016-05-17 00:00:00：" +
                           d.getTime());
        //
        d = sdf.parse("2016-05-18 00:00:00");
        System.out.println("2016-05-18 00:00:00：" +
                           d.getTime());
        //
        d = sdf.parse("2016-05-19 00:00:00");
        System.out.println("2016-05-19 00:00:00：" +
                           d.getTime());
        //
        d = sdf.parse("2016-05-20 00:00:00");
        System.out.println("2016-05-20 00:00:00：" +
                           d.getTime());
        //
        d = sdf.parse("2016-05-21 00:00:00");
        System.out.println("2016-05-21 00:00:00：" +
                           d.getTime());
        //
        d = sdf.parse("2016-05-22 00:00:00");
        System.out.println("2016-05-18 00:00:00：" +
                           d.getTime());
        //
        d = sdf.parse("2016-05-23 00:00:00");
        System.out.println("2016-05-23 00:00:00：" +
                           d.getTime());
        //
        d = sdf.parse("2016-05-24 00:00:00");
        System.out.println("2016-05-24 00:00:00：" +
                           d.getTime());
    }
    
}
