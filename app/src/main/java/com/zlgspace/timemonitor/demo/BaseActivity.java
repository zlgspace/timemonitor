package com.zlgspace.timemonitor.demo;

import android.app.Activity;
import android.os.Bundle;

import com.zlgspace.timemonitor.TimeMonitorMng;

public class BaseActivity extends Activity {
    String TAG = getClass().getSimpleName();
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            TimeMonitorMng.recoding(TimeMonitorConfig.MONITOR_MAIN_TO_NEWACTIVITY,TAG+":onCreate");
        }
    
        @Override
        protected void onStart() {
            super.onStart();
            TimeMonitorMng.recoding(TimeMonitorConfig.MONITOR_MAIN_TO_NEWACTIVITY,TAG+":onStart");
        }
    
        @Override
        protected void onRestart() {
            super.onRestart();
            TimeMonitorMng.recoding(TimeMonitorConfig.MONITOR_MAIN_TO_NEWACTIVITY,TAG+":onRestart");
        }
    
        @Override
        protected void onResume() {
            super.onResume();
            TimeMonitorMng.recoding(TimeMonitorConfig.MONITOR_MAIN_TO_NEWACTIVITY,TAG+":onResume");
        }
    
        @Override
        protected void onPause() {
            super.onPause();
            TimeMonitorMng.recoding(TimeMonitorConfig.MONITOR_MAIN_TO_NEWACTIVITY,TAG+":onPause");
        }
    
        @Override
        protected void onStop() {
            super.onStop();
            TimeMonitorMng.recoding(TimeMonitorConfig.MONITOR_MAIN_TO_NEWACTIVITY,TAG+":onStop");
        }
    
        @Override
        protected void onDestroy() {
            super.onDestroy();
            TimeMonitorMng.end(TimeMonitorConfig.MONITOR_MAIN_TO_NEWACTIVITY,TAG+":onDestroy");
        }
}
