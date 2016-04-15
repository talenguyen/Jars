package vn.tale.jars;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.schedulers.Schedulers;
import timber.log.Timber;
import vn.tale.jars.database.DbModule;
import vn.tale.jars.di.ApiModule;
import vn.tale.jars.di.AppComponent;
import vn.tale.jars.di.AppModule;
import vn.tale.jars.di.DaggerAppComponent;
import vn.tale.jars.di.LceBindingModule;
import vn.tale.jars.di.RepositoryModule;
import vn.tale.jars.model.ImmutableJar;
import vn.tale.jars.model.Jar;
import vn.tale.jars.pref.Settings;
import vn.tale.jars.ui.list.JarRepository;

/**
 * Author giangnguyen. Created on 4/1/16.
 */
public class BaseApp extends Application {

  private AppComponent component;

  @Inject Settings settings;
  @Inject JarRepository jarRepository;

  public static BaseApp get(Context context) {
    return (BaseApp) context.getApplicationContext();
  }

  @Override public void onCreate() {
    super.onCreate();

    component = prepareAppComponent();

    component.inject(this);

    setupLogging();

    initialize();
  }

  private void setupLogging() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      Timber.plant(new CrashReportingTree());
    }
  }

  private void initialize() {
    if (settings.isFirstRun()) {
      jarRepository.add(getDefaultJars())
          .subscribeOn(Schedulers.io())
          .subscribe(integer -> {
            Timber.d("number of item inserted is %d", integer);
            settings.setFirstRun(true);
          });
    }
  }

  /** A tree which logs important information for crash reporting. */
  private static class CrashReportingTree extends Timber.Tree {
    @Override protected void log(int priority, String tag, String message, Throwable t) {
      if (priority == Log.VERBOSE || priority == Log.DEBUG) {
        return;
      }

      FakeCrashLibrary.log(priority, tag, message);

      if (t != null) {
        if (priority == Log.ERROR) {
          FakeCrashLibrary.logError(t);
        } else if (priority == Log.WARN) {
          FakeCrashLibrary.logWarning(t);
        }
      }
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

  @NonNull protected AppComponent prepareAppComponent() {
    return DaggerAppComponent.builder()
        .lceBindingModule(new LceBindingModule())
        .repositoryModule(new RepositoryModule())
        .appModule(new AppModule(this))
        .apiModule(new ApiModule())
        .dbModule(new DbModule())
        .build();
  }
}
