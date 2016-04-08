package vn.tale.jars.ui.list;

import dagger.Module;
import dagger.Provides;
import vn.tale.lcebinding.LoadingContentError;
import vn.tale.jars.util.ComputationMainThreadScheduler;
import vn.tale.jars.util.ThreadScheduler;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
@Module public class UserListModule {

  @Provides public UserListAdapter provideUserListAdapter() {
    return new UserListAdapter();
  }

  @Provides public ThreadScheduler provideThreadScheduler() {
    return new ComputationMainThreadScheduler();
  }

  @Provides public UserListVM provideUserListVM(LoadingContentError loadingContentError,
      ThreadScheduler threadScheduler, UserListApi apiClient) {
    return new UserListVM(loadingContentError, apiClient, threadScheduler);
  }
}
