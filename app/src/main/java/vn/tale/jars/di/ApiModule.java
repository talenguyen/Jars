package vn.tale.jars.di;

import vn.tale.jars.ui.list.UserListApi;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
public interface ApiModule {

  UserListApi provideUserListApi();

}
