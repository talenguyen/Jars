package vn.tale.jars.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.tale.jars.api.GithubApi;
import vn.tale.jars.model.GsonAdaptersUser;
import vn.tale.jars.ui.list.UserListApi;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
@Module
public class ApiModule {

  @Provides @Singleton public Gson provideGson() {
    final GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapterFactory(new GsonAdaptersUser());
    return builder.create();
  }

  @Provides @Singleton public GithubApi provideGithubApi(Gson gson) {
    final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();

    return retrofit.create(GithubApi.class);
  }

  @Provides @Singleton public UserListApi provideUserListApi() {
    return () -> provideGithubApi(provideGson()).getUsers();
  }

}
