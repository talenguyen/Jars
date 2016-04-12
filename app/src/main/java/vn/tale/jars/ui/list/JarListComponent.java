package vn.tale.jars.ui.list;

import dagger.Subcomponent;

/**
 * Created by Giang Nguyen at Tiki on 4/11/16.
 */
@Subcomponent(modules = JarListModule.class)
public interface JarListComponent {

  void inject(JarListFragment fragment);
}
