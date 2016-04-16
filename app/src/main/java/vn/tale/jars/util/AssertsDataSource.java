package vn.tale.jars.util;

import android.app.Application;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

import rx.Observable;

/**
 * Created by Giang Nguyen at Tiki on 4/15/16.
 */
public class AssertsDataSource {

  private final Application application;
  private Gson gson;

  public AssertsDataSource(Application application, Gson gson) {
    this.application = application;
    this.gson = gson;
  }

  public <T> Observable<T> read(String path, Class<T> clazz) {
   return Observable.defer(() -> {
     try {
       final InputStream in = application.getAssets().open(path);
       final Reader reader = new InputStreamReader(in);
       final T fromJson = gson.fromJson(reader, clazz);
       in.close();
       return Observable.just(fromJson);
     } catch (IOException e) {
       return Observable.error(e);
     }
   });
  }

  public <T> Observable<T> read(String path, Type typeOfT) {
   return Observable.defer(() -> {
     try {
       final InputStream in = application.getAssets().open(path);
       final Reader reader = new InputStreamReader(in);
       final T fromJson = gson.fromJson(reader, typeOfT);
       in.close();
       return Observable.just(fromJson);
     } catch (IOException e) {
       return Observable.error(e);
     }
   });
  }

}
