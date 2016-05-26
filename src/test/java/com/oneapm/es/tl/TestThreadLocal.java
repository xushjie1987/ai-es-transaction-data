/**
 * Project Name:ai-es-transaction-data
 * File Name:TestThreadLocal.java
 * Package Name:com.oneapm.es.tl
 * Date:2016年5月25日下午7:47:21
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.tl;

import org.junit.Test;

/**
 * ClassName:TestThreadLocal <br/>
 * Function: <br/>
 * Date: 2016年5月25日 下午7:47:21 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class TestThreadLocal {
    
    private ThreadLocal<A> tl = new ThreadLocal<TestThreadLocal.A>();
    
    @Test
    public void testThreadLocal01() {
        tl.get()
          .print();
    }
    
    private class A {
        private String value = "hello";
        
        public A() {
            
        }
        
        public void print() {
            System.out.println(value);
        }
    }
    
}
