package com.zlgspace.timemonitor;

import java.util.HashMap;

public final class TimeMonitorMng {

    private static HashMap<String,TimeMonitor> mTimeMonitorCache = new HashMap<>();

    private static PrintRstformatAdapter mPrintRstformatAdapter = PrintRstformatAdapter.Factory.newDefaultLogAdapter();

    private static boolean isMonitor = true;//configuring whether to monitor

    private TimeMonitorMng(){}

    public static void init(boolean isMonitor,PrintRstformatAdapter adapter){
        TimeMonitorMng.isMonitor = isMonitor;
        if(adapter!=null)
            mPrintRstformatAdapter = adapter;
    }

    public static void start(String processName){
        start(processName,null);
    }

    public static void start(String processName,String actionName){
        if(!isMonitor)
            return;
        synchronized (mTimeMonitorCache){
            if(mTimeMonitorCache.containsKey(processName)){
                mTimeMonitorCache.get(processName).start(actionName);
                return;
            }
            mTimeMonitorCache.put(processName,new TimeMonitor(processName));
            mTimeMonitorCache.get(processName).start(actionName);
        }
    }

    public static void recoding(String processName,String actionName){
        if(!isMonitor)
            return;
        if(mTimeMonitorCache.containsKey(processName)){
            mTimeMonitorCache.get(processName).recoding(actionName);
            return;
        }
        mTimeMonitorCache.put(processName,new TimeMonitor(processName));
        mTimeMonitorCache.get(processName).recoding(actionName);
    }

    public static void end(String processName){
        end(processName,null);
    }

    public static void end(String processName,String actionName){
        if(!isMonitor)
            return;
        if(mTimeMonitorCache.containsKey(processName)){
            mTimeMonitorCache.get(processName).end(actionName,rstListener);
            return;
        }
    }

    private static OnMonitorRstListener rstListener;
    public static void setOnMonitorRstListener(OnMonitorRstListener listener){
        rstListener = listener;
    }

    static PrintRstformatAdapter getFormatAdapter(){
        return mPrintRstformatAdapter;
    }
}
