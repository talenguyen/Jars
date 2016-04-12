package vn.tale.jars.di;

import javax.inject.Singleton;

import dagger.Component;
import vn.tale.jars.ui.list.JarListComponent;
import vn.tale.jars.ui.list.JarListModule;
import vn.tale.jars.ui.list_user.UserListComponent;
import vn.tale.jars.ui.list_user.UserListModule;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
@Singleton
@Component(modules = { AppModule.class, LceBindingModule.class, AppApiModule.class, AppRepositoryModule.class })
public interface AppComponent {
  UserListComponent plus(UserListModule module);
  JarListComponent plus(JarListModule module);
}
