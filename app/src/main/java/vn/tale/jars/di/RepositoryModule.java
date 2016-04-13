package vn.tale.jars.di;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import vn.tale.jars.ui.list.JarRepository;

/**
 * Created by Giang Nguyen at Tiki on 4/11/16.
 */
public interface RepositoryModule {

  JarRepository provideJarRepository(StorIOSQLite storIOSQLite);
}
