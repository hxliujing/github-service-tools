package com.javens.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by liujing on 2017/7/1.
 */
public class PIDUtils {
    public static int getPid(){
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        int index = name.indexOf("@");
        if (index != -1) {
            int pid = Integer.parseInt(name.substring(0, index));
            return pid;
        }
        return 0;
    }
}
