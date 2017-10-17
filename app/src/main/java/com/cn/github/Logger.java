package com.cn.github;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ld on 2017/10/16.
 */

public class Logger {
    private void logger() {
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter());
        com.orhanobut.logger.Logger.d("123");
        com.orhanobut.logger.Logger.clearLogAdapters();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(0)
                .methodOffset(3)
                .tag("tag")
                .build();
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        com.orhanobut.logger.Logger.addLogAdapter(new DiskLogAdapter());
        com.orhanobut.logger.Logger.w("no thread info and only 1 method");

        com.orhanobut.logger.Logger.clearLogAdapters();
        formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .build();

        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        com.orhanobut.logger.Logger.i("no thread info and method info");

        com.orhanobut.logger.Logger.t("tag").e("Custom tag for only one use");

        com.orhanobut.logger.Logger.json("{ \"key\": 3, \"value\": something}");

        com.orhanobut.logger.Logger.d(Arrays.asList("foo", "bar"));

        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key1", "value2");

        com.orhanobut.logger.Logger.d(map);

        com.orhanobut.logger.Logger.clearLogAdapters();
        formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .tag("MyTag")
                .build();
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        com.orhanobut.logger.Logger.w("my log message with my tag");
    }
}
