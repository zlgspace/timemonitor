package com.zlgspace.timemonitor;

import com.zlgspace.logadapter.LogPrint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

class TimeMonitor {
    private final String TAG = "TimeMonitor";

    private ArrayList<TimeTick> mTimeTicks = new ArrayList<>();

    private String mProcessName;

    private long mStartTime;

    private boolean isEnd = true;

    public TimeMonitor(String processName){
        mProcessName = processName;
    }

    public void start(){
        isEnd = false;
        synchronized (mTimeTicks){
            mTimeTicks.clear();
        }
        mStartTime = System.currentTimeMillis();
    }

    public void start(String actionName){
        start();
        recoding(actionName);
    }

    public void  recoding(String actionName){
        if(actionName == null||actionName.isEmpty())
            return;
        synchronized (mTimeTicks) {
            long time = System.currentTimeMillis() - mStartTime;
            if (isEnd) {
                isEnd = false;
                mStartTime = time;
            }
            mTimeTicks.add(new TimeTick(actionName, time));
        }
    }


    public void end(String actionName,OnMonitorRstListener listener){
        recoding(actionName);
        end(null);
    }

    public void end(OnMonitorRstListener listener){
        isEnd = true;
        debug(listener);
    }

    public void debug(OnMonitorRstListener listener){
        if (mTimeTicks.isEmpty()) {
            LogPrint.w(TAG,"mTimeTag is empty!");
            return;
        }

        if(listener!=null){
            listener.onMonitorRst(mProcessName,TimeMonitorMng.getFormatAdapter().onFormat(mProcessName,mStartTime,timeTicks2LinkedHashMap()));
            return;
        }

        new Thread(){
            @Override
            public void run() {
                synchronized (mTimeTicks) {
                    Iterator iterator = mTimeTicks.iterator();

                    LogPrint.d(TAG,mProcessName+": [ ");

                    while (iterator != null && iterator.hasNext()){
                        TimeTick tag = (TimeTick)iterator.next();
                        LogPrint.d(TAG,mProcessName+" "+tag.toString());
                    }
                    LogPrint.d(TAG," ] "+mProcessName);
                }
            }
        }.start();
    }

    LinkedHashMap<String ,Long> timeTicks2LinkedHashMap(){
        int count = mTimeTicks.size();
        LinkedHashMap<String ,Long> map = new LinkedHashMap<>();
        for(int i=0;i<count;i++){
            map.put(mTimeTicks.get(i).getActionName(),mTimeTicks.get(i).getActionTime());
        }
        return map;
    }

}
