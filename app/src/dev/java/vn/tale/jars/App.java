package vn.tale.jars;

import android.support.annotation.NonNull;

import vn.tale.jars.database.DbModule;
import vn.tale.jars.di.AppApiModule;
import vn.tale.jars.di.AppModule;
import vn.tale.jars.di.AppRepositoryModule;
import vn.tale.jars.di.DaggerAppComponent;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
public class App extends BaseApp {

  @NonNull protected DaggerAppComponent.Builder prepareAppComponentBuilder() {
    return DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .appApiModule(new AppApiModule())
        .dbModule(new DbModule())
        .appRepositoryModule(new AppRepositoryModule());
  }
}
