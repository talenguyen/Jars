package vn.tale.jars.di;

import android.app.Application;
import android.content.Context;

import java.util.NoSuchElementException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.tale.jars.R;
import vn.tale.jars.pref.Settings;
import vn.tale.lcebinding.ErrorMessageProvider;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
@Module public class AppModule {
  private final Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton public Application provideApplication() {
    return application;
  }

  @Provides @Singleton public Settings provideSettings() {
    return new Settings(application.getSharedPreferences("settings", Context.MODE_PRIVATE));
  }

  @Provides @Singleton public ErrorMessageProvider provideErrorMessageProvider() {
    return throwable -> {
      if (throwable instanceof NoSuchElementException) {
        return application.getString(R.string.error_empty);
      }
      return application.getString(R.string.error_try_again);
    };
  }

}
