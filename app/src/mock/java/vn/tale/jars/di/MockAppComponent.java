package vn.tale.jars.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Giang Nguyen at Tiki on 4/15/16.
 */

@Singleton
@Component(modules = { AppModule.class, AppApiModule.class })
public interface MockAppComponent extends AppComponent {
}
