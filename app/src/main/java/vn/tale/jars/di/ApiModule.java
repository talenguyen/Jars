package vn.tale.jars.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

  @Provides @Singleton public GithubApi provideGithubApi(Gson gson, OkHttpClient client) {
    final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build();

    return retrofit.create(GithubApi.class);
  }

  @Provides @Singleton  public OkHttpClient provideOkHttpClient() {
    final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build();
  }

  @Provides @Singleton public UserListApi provideUserListApi(GithubApi githubApi) {
    return githubApi::getUsers;
  }

}
