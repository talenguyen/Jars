package vn.tale.jars.di;

import android.app.Application;
import android.os.SystemClock;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import vn.tale.jars.GsonUtils;
import vn.tale.jars.model.User;
import vn.tale.jars.ui.list_user.UserListApi;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
@Module public class MockApiModule {
  private final Application application;

  public MockApiModule(Application application) {
    this.application = application;
  }

  private List<User> getMockUsers(Application application) throws IOException {
    SystemClock.sleep(1500);
    final InputStream inputStream = application.getAssets().open("users.json");
    final TypeToken<List<User>> typeToken = new TypeToken<List<User>>() {};
    return GsonUtils.readJsonStream(inputStream, typeToken.getType());
  }

  @Provides @Singleton public UserListApi provideUserListApi() {
    return () -> {
      final long delta = System.currentTimeMillis() % 3;
      System.out.println("delta: " + delta);
      if (delta == 0) {
        return Observable.error(new RuntimeException());
      } else if (delta == 1) {
        return Observable.empty();
      }
      return Observable.fromCallable(() -> getMockUsers(application));
    };
  }
}
