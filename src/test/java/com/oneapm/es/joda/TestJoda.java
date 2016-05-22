/**
 * Project Name:ai-es-transaction-data
 * File Name:TestJoda.java
 * Package Name:com.oneapm.es.joda
 * Date:2016年5月21日下午7:15:35
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.joda;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

/**
 * ClassName:TestJoda <br/>
 * Function: <br/>
 * Date: 2016年5月21日 下午7:15:35 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class TestJoda {
    
    /**
     * testJoda: <br/>
     * 2016-05-21T11:19:28.171Z <br>
     * http://joda-time.sourceforge.net/userguide.html <br>
     * 
     * @author xushjie
     * @since JDK 1.7
     */
    @Test
    public void testJoda01() {
        DateTimeFormatter dtf = ISODateTimeFormat.dateTime()
                                                 .withZoneUTC();
        System.out.println(dtf.print(new Date().getTime()));
    }
    
    /**
     * testJoda02: <br/>
     * 
     * @author xushjie
     * @since JDK 1.7
     */
    @Test
    public void testJoda02() {
        DateTime date = new DateTime();
        System.out.println(date.year()
                               .get());
        System.out.println(date.yearOfCentury()
                               .get());
        System.out.println(date.yearOfEra()
                               .get());
        System.out.println(date.monthOfYear()
                               .get());
        System.out.println(date.dayOfMonth()
                               .get());
        System.out.println(date.hourOfDay()
                               .get());
        System.out.println(date.minuteOfHour()
                               .get());
        System.out.println(date.secondOfMinute()
                               .get());
        System.out.println(date.millisOfSecond()
                               .get());
        System.out.println();
    }
    
    /**
     * testJoda03: <br/>
     * 
     * @author xushjie
     * @since JDK 1.7
     */
    @Test
    public void testJoda03() {
        DateTimeFormatter dtf = ISODateTimeFormat.dateTime()
                                                 .withZoneUTC();
        System.out.println(dtf.print(1464106151883L));
    }
    
    /**
     * testJoda04: <br/>
     * 
     * @author xushjie
     * @since JDK 1.7
     */
    @Test
    public void testJoda04() {
        DateTimeFormatter dtf = ISODateTimeFormat.dateTime()
                                                 .withZoneUTC();
        System.out.println(dtf.getPrinter()
                              .toString());
    }
    
}
