/**
 * Project Name:ai-es-transaction-data
 * File Name:Engine.java
 * Package Name:com.oneapm.es.engine
 * Date:2016年5月21日下午5:29:33
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.engine;

/**
 * ClassName:Engine <br/>
 * Function: <br/>
 * Date: 2016年5月21日 下午5:29:33 <br/>
 * 
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public interface Engine<ONE, OTYPE, BULK, BTYPE> {
    
    /**
     * sendOne: <br/>
     * 
     * @author xushjie
     * @param data
     * @return
     * @since JDK 1.7
     */
    public ONE sendOne(OTYPE data);
    
    /**
     * sendBulk: <br/>
     * 
     * @author xushjie
     * @param datas
     * @return
     * @since JDK 1.7
     */
    public BULK sendBulk(BTYPE datas);
    
    /**
     * sumAll: <br/>
     * 
     * @author xushjie
     * @return
     * @since JDK 1.7
     */
    public Long sumAll();
    
    /**
     * plusBulk: <br/>
     * 
     * @author xushjie
     * @param bulk
     * @since JDK 1.7
     */
    public void plusBulk(Long bulk);
    
}
