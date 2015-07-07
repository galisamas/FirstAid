package com.itworks.firstaid;

import android.app.Application;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).threadPoolSize(5)
                .build();
        ImageLoader.getInstance().init(config);

//        ParseCrashReporting.enable(this);
//        Parse.initialize(this, "82oOrqqYacCPkNSBhBy4cwTJ5NNbMkpPghYNvltZ", "vpgRI8ZHKK3T2p1jecM3wWpF0TKdyzjMmwapWdMr");
//        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
