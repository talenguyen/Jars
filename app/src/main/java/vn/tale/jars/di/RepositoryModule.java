package vn.tale.jars.di;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.tale.jars.repository.SqlJarRepository;
import vn.tale.jars.ui.list.JarRepository;

/**
 * Created by Giang Nguyen at Tiki on 4/11/16.
 */
@Module
public class RepositoryModule {

  @Provides @Singleton public JarRepository provideJarRepository(StorIOSQLite storIOSQLite) {
    return new SqlJarRepository(storIOSQLite);
  }
}

