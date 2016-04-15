package vn.tale.jars.di;

import javax.inject.Singleton;

import dagger.Component;
import vn.tale.jars.database.DbModule;

/**
 * Created by Giang Nguyen at Tiki on 4/15/16.
 */

@Singleton
@Component(modules = {
    DbModule.class,
    AppModule.class,
    MockApiModule.class,
    LceBindingModule.class,
    MockRepositoryModule.class,
})
public interface MockAppComponent extends AppComponent {
}
