package com.images.krith.mosaic;

import com.images.krith.mosaic.Modules.ApplicationModule;
import com.images.krith.mosaic.Modules.NetModule;
import com.images.krith.mosaic.View.Home;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by krith on 30/11/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {
    void inject(Home home);
}