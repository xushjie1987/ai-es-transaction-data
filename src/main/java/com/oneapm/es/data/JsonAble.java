/**
 * Project Name:ai-es-transaction-data
 * File Name:JsonAble.java
 * Package Name:com.oneapm.es.data
 * Date:2016年4月29日下午6:33:23
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.data;

import com.google.gson.Gson;

/**
 * ClassName:JsonAble <br/>
 * Function: <br/>
 * Date: 2016年4月29日 下午6:33:23 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public interface JsonAble {
    
    static final Gson gson = new Gson();
    
    String toJSON();
    
}
