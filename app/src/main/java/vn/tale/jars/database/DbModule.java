package vn.tale.jars.database;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.tale.jars.database.entity.JarEntity;
import vn.tale.jars.database.entity.JarEntitySQLiteTypeMapping;

@Module
public class DbModule {

  // We suggest to keep one instance of StorIO (SQLite or ContentResolver)
  // It's thread safe and so on, so just share it.
  // But if you need you can have multiple instances of StorIO
  // (SQLite or ContentResolver) with different settings such as type mapping, logging and so on.
  // But keep in mind that different instances of StorIOSQLite won't share notifications!
  @Provides
  @NonNull
  @Singleton
  public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
    return DefaultStorIOSQLite.builder()
        .sqliteOpenHelper(sqLiteOpenHelper)
        .addTypeMapping(JarEntity.class, new JarEntitySQLiteTypeMapping())
        .build();
  }

  @Provides
  @NonNull
  @Singleton
  public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Application application) {
    return new DbOpenHelper(application);
  }
}