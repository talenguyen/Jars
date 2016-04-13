package vn.tale.jars.di;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.tale.jars.repository.SqlJarRepository;
import vn.tale.jars.ui.list.JarRepository;

/**
 * Created by Giang Nguyen at Tiki on 4/13/16.
 */
@Module
public class AppRepositoryModule implements RepositoryModule {

  @Provides @Singleton @Override public JarRepository provideJarRepository(StorIOSQLite storIOSQLite) {
    return new SqlJarRepository(storIOSQLite);
  }
}
