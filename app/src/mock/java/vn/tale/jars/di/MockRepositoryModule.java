package vn.tale.jars.di;

import android.app.Application;

import com.google.gson.reflect.TypeToken;

import java.util.Collection;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import vn.tale.jars.model.Jar;
import vn.tale.jars.repository.Specification;
import vn.tale.jars.ui.list.JarRepository;
import vn.tale.jars.util.AssertsDataSource;

/**
 * Created by Giang Nguyen at Tiki on 4/11/16.
 */
@Module
public class MockRepositoryModule {

  private Application application;

  public MockRepositoryModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  public JarRepository provideJarRepository(AssertsDataSource assertsDataSource) {
    return new JarRepository() {

      @Override public Observable<Long> add(Jar item) {
        return Observable.empty();
      }

      @Override public Observable<Integer> add(Collection<Jar> items) {
        return Observable.empty();
      }

      @Override
      public Observable<Boolean> update(Jar item) {
        return Observable.empty();
      }

      @Override
      public Observable<Boolean> remove(Jar item) {
        return Observable.empty();
      }

      @Override
      public Observable<Integer> remove(Specification specification) {
        return Observable.empty();
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
        final TypeToken<List<Jar>> typeToken = new TypeToken<List<Jar>>() {
        };
        return assertsDataSource.read("jars.json", typeToken.getType());
      }
    };
  }
}
