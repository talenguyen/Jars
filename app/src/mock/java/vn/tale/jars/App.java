package vn.tale.jars;

import android.support.annotation.NonNull;

import vn.tale.jars.di.AppApiModule;
import vn.tale.jars.di.AppComponent;
import vn.tale.jars.di.AppModule;
import vn.tale.jars.di.DaggerMockAppComponent;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
public class App extends BaseApp {

  @NonNull @Override protected AppComponent prepareAppComponent() {
    return DaggerMockAppComponent.builder()
        .appModule(new AppModule(this))
        .appApiModule(new AppApiModule(this))
        .build();
  }
}
