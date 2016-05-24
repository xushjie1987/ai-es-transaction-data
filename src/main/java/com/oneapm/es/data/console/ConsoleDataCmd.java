/**
 * Project Name:ai-es-transaction-data
 * File Name:ConsoleDataCmd.java
 * Package Name:com.oneapm.es.data.console
 * Date:2016年5月23日下午5:15:44
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.data.console;

import io.airlift.airline.Command;
import io.airlift.airline.Option;
import lombok.Getter;
import lombok.Setter;

import com.oneapm.es.data.AbstractDataCmd;
import com.oneapm.es.data.DataGenerator;
import com.oneapm.es.data.MetricDataGenerator;
import com.oneapm.es.util.TimeUtil;

/**
 * ClassName:ConsoleDataCmd <br/>
 * Function: <br/>
 * Date: 2016年5月23日 下午5:15:44 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Getter
@Setter
@Command(name = "console",
         description = "Console模式Data消息源")
public class ConsoleDataCmd extends AbstractDataCmd {
    
    @Option(name = { "-d", "--duration" },
            required = false,
            description = "Console模式Data消息时间戳范围，默认3d")
    public String         duration = "3d";
    
    private DataGenerator source   = new MetricDataGenerator();
    
    /**
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings("static-access")
    @Override
    public void run() {
        // 设置时间范围
        ((MetricDataGenerator) source).duration = TimeUtil.calcDuration(duration);
        // 循环打印console
        for (long i = 0; i < count; i++) {
            System.out.println(source.getTransactionData()
                                     .toJSON());
        }
    }
    
}
