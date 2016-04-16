package vn.tale.jars.ui.list;

import dagger.Module;
import dagger.Provides;
import vn.tale.jars.ui.list.adapter.JarListAdapter;
import vn.tale.jars.util.ComputationMainThreadScheduler;
import vn.tale.lcebinding.LoadingContentError;

/**
 * Created by Giang Nguyen at Tiki on 4/11/16.
 */
@Module
public class JarListModule {

  @Provides
  public JarListViewModel provideJarListViewModel(LoadingContentError lce,
                                                  JarRepository jarRepository) {
    return new JarListViewModel(new ComputationMainThreadScheduler(), jarRepository);
  }

  @Provides
  public JarListAdapter provideJarListAdapter() {
    return new JarListAdapter();
  }
}
