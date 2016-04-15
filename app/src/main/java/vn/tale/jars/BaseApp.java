package vn.tale.jars;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import vn.tale.jars.di.ApiModule;
import vn.tale.jars.di.AppComponent;
import vn.tale.jars.di.AppModule;
import vn.tale.jars.di.DaggerAppComponent;

/**
 * Author giangnguyen. Created on 4/1/16.
 */
public class BaseApp extends Application {

  private AppComponent component;

  public static BaseApp get(Context context) {
    return (BaseApp) context.getApplicationContext();
  }

  @Override public void onCreate() {
    super.onCreate();

    DaggerAppComponent.Builder builder = prepareAppComponentBuilder();
    component = builder.build();
  }

  public AppComponent getComponent() {
    return component;
  }

  @NonNull protected DaggerAppComponent.Builder prepareAppComponentBuilder() {
    return DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .apiModule(new ApiModule());
  }
}
