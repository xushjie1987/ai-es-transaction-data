/**
 * Project Name:ai-es-transaction-data
 * File Name:AbstractDataCmd.java
 * Package Name:com.oneapm.es.data
 * Date:2016年5月23日下午5:13:51
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.data;

import io.airlift.airline.Option;
import io.airlift.airline.OptionType;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName:AbstractDataCmd <br/>
 * Function: <br/>
 * Date: 2016年5月23日 下午5:13:51 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
@Getter
@Setter
public abstract class AbstractDataCmd implements Runnable {
    
    @Option(type = OptionType.GROUP,
            name = { "-c", "--count" },
            required = false,
            description = "Data消息数，默认10")
    public Long count = 10L;
    
}
