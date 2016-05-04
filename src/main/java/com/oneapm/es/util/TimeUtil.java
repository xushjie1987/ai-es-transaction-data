/**
 * Project Name:suzume-search
 * File Name:TimeUtil.java
 * Package Name:com.suzume.search.util
 * Date:2016年4月4日上午9:58:05
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.util;


/**
 * ClassName:TimeUtil <br/>
 * Function: <br/>
 * Date: 2016年4月4日 上午9:58:05 <br/>
 * 
 * @author shengjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class TimeUtil {
    
    /**
     * humanTime: <br/>
     * 
     * @author shengjie
     * @param date
     * @return
     * @since JDK 1.7
     */
    public static String humanTime(long date) {
        if (date /
            1000 /
            60 /
            60 /
            24 > 0) {
            return String.valueOf(date /
                                  1000 /
                                  60 /
                                  60 /
                                  24) +
                   "天";
        }
        if (date / 1000 / 60 / 60 > 0) {
            return String.valueOf(date / 1000 / 60 / 60) +
                   "小时";
        }
        if (date / 1000 / 60 > 0) {
            return String.valueOf(date / 1000 / 60) +
                   "分钟";
        }
        if (date / 1000 > 0) {
            return String.valueOf(date / 1000) +
                   "秒";
        }
        return String.valueOf(date) +
               "毫秒";
    }
    
}
