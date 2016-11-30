package com.images.krith.mosaic;

import android.app.Application;

import com.images.krith.mosaic.Modules.ApplicationModule;
import com.images.krith.mosaic.Modules.NetModule;
import com.images.krith.mosaic.Utils.Constant;

/**
 * Created by krith on 30/11/16.
 */

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(Constant.BASE_URL))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
