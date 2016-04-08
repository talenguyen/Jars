package vn.tale.jars.di;

import javax.inject.Singleton;

import dagger.Component;
import vn.tale.jars.ui.list_user.UserListComponent;
import vn.tale.jars.ui.list_user.UserListModule;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
@Singleton
@Component(modules = { AppModule.class, AppApiModule.class })
public interface AppComponent {
  UserListComponent plus(UserListModule module);
}
