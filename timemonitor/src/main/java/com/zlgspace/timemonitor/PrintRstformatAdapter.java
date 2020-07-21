package com.zlgspace.timemonitor;

import java.util.LinkedHashMap;
import java.util.Map;

public interface PrintRstformatAdapter {
    String onFormat(String processName, long startTime,LinkedHashMap<String, Long> timeTicks);

    final class Factory {
        private Factory() {}
        public static PrintRstformatAdapter newDefaultLogAdapter() {
            return new PrintRstformatAdapter(){

                @Override
                public String onFormat(String processName, long startTime, LinkedHashMap<String, Long> timeTicks) {
                    String rst = processName+": [ \n";

                   for(Map.Entry<String, Long> entry :timeTicks.entrySet()){
                       String actionName = entry.getKey();
                       long time = entry.getValue();
                       String tick = "{ "+actionName+"："+time+"ms,distance start time:"+(time-startTime)+"ms }\n";
                       rst+=tick;
                   }
                    rst = processName+": ] "+processName;
                    return rst;
                }
            };
        }
    }
}
