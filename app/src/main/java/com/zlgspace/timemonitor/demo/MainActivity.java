package com.zlgspace.timemonitor.demo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zlgspace.timemonitor.TimeMonitorMng;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.toNewActivityBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeMonitorMng.start(TimeMonitorConfig.MONITOR_MAIN_TO_NEWACTIVITY);
                Intent intent = new Intent(MainActivity.this,NewActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
