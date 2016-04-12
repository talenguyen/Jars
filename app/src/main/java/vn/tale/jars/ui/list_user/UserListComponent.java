package vn.tale.jars.ui.list_user;

import dagger.Subcomponent;
import vn.tale.jars.di.ActivityScope;
import vn.tale.jars.di.LceBindingModule;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
@ActivityScope
@Subcomponent(modules = {UserListModule.class, LceBindingModule.class })
public interface UserListComponent {
  void inject(ListUserActivity activity);
}
