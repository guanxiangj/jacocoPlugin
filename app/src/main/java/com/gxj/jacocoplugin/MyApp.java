package com.gxj.jacocoplugin;

import android.app.Application;

import android.util.Log;
import org.jacoco.agent.rt.CodeCoverageManager;

public class MyApp extends Application {
    public static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        CodeCoverageManager.init(app, "http://10.10.17.105:8080");//内网 服务器地址);
        CodeCoverageManager.uploadData();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d("myapp","切后台................");
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            CodeCoverageManager.generateCoverageFile();
        }
    }
}
