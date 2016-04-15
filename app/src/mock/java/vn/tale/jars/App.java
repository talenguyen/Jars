package vn.tale.jars;

import android.support.annotation.NonNull;

import vn.tale.jars.di.AppComponent;
import vn.tale.jars.di.AppModule;
import vn.tale.jars.di.DaggerMockAppComponent;
import vn.tale.jars.di.LceBindingModule;
import vn.tale.jars.di.MockApiModule;
import vn.tale.jars.di.MockRepositoryModule;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
public class App extends BaseApp {

  @NonNull @Override protected AppComponent prepareAppComponent() {
    return DaggerMockAppComponent.builder()
        .mockRepositoryModule(new MockRepositoryModule(this))
        .lceBindingModule(new LceBindingModule())
        .mockApiModule(new MockApiModule(this))
        .appModule(new AppModule(this))
        .build();
  }
}
