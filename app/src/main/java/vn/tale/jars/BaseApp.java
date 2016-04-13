package vn.tale.jars;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vn.tale.jars.di.AppComponent;
import vn.tale.jars.di.DaggerAppComponent;
import vn.tale.jars.model.ImmutableJar;
import vn.tale.jars.model.Jar;
import vn.tale.jars.pref.Settings;
import vn.tale.jars.ui.list.JarRepository;

/**
 * Author giangnguyen. Created on 4/1/16.
 */
public abstract class BaseApp extends Application {

  private AppComponent component;

  @Inject Settings settings;
  @Inject JarRepository jarRepository;

  public static BaseApp get(Context context) {
    return (BaseApp) context.getApplicationContext();
  }

  @Override public void onCreate() {
    super.onCreate();

    DaggerAppComponent.Builder builder = prepareAppComponentBuilder();
    component = builder.build();
    component.inject(this);

    initialize();
  }

  private void initialize() {
    if (settings.isFirstRun()) {
      jarRepository.add(getDefaultJars());
      settings.setFirstRun(true);
    }
  }

  private List<Jar> getDefaultJars() {
    final List<Jar> defaultJars = new ArrayList<>(6);
    for (int i = 0; i < 6; i++) {
      final ImmutableJar jar = ImmutableJar.builder()
          .name("NEC")
          .fullName("Necessities")
          .rate(.55f)
          .amount(550)
          .build();
      defaultJars.add(jar);
    }
    return defaultJars;
  }

  public AppComponent getComponent() {
    return component;
  }


  protected abstract DaggerAppComponent.Builder prepareAppComponentBuilder();
}
