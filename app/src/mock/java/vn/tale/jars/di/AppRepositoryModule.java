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
import vn.tale.jars.model.Jar;
import vn.tale.jars.repository.Specification;
import vn.tale.jars.ui.list.JarRepository;

/**
 * Created by Giang Nguyen at Tiki on 4/11/16.
 */
@Module
public class AppRepositoryModule implements RepositoryModule {

  private Application application;

  public AppRepositoryModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  @Override
  public JarRepository provideJarRepository() {
    return new JarRepository() {
      @Override
      public void add(Jar item) {

      }

      @Override
      public void add(Iterable<Jar> items) {

      }

      @Override
      public Observable<Boolean> update(Jar item) {
        return null;
      }

      @Override
      public Observable<Boolean> remove(Jar item) {
        return null;
      }

      @Override
      public Observable<Integer> remove(Specification specification) {
        return null;
      }

      @Override
      public Observable<List<Jar>> query(Specification specification) {
        final long delta = System.currentTimeMillis() % 3;
        System.out.println("delta: " + delta);
        if (delta == 0) {
          return Observable.error(new RuntimeException());
        } else if (delta == 1) {
          return Observable.empty();
        }
        return Observable.fromCallable(() -> getMockJars(application));
      }
    };
  }

  private List<Jar> getMockJars(Application application) throws IOException {
    SystemClock.sleep(1500);
    final InputStream inputStream = application.getAssets().open("jars.json");
    final TypeToken<List<Jar>> typeToken = new TypeToken<List<Jar>>() {
    };
    return GsonUtils.readJsonStream(inputStream, typeToken.getType());
  }
}
