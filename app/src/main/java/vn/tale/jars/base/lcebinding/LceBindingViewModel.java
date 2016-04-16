package vn.tale.jars.base.lcebinding;

import com.jakewharton.rxrelay.BehaviorRelay;
import com.jakewharton.rxrelay.SerializedRelay;

import rx.Observable;

/**
 * Created by Giang Nguyen at Tiki on 4/9/16.
 */
public class LceBindingViewModel<T> {

  private final SerializedRelay<T, T> dataSubject = BehaviorRelay.<T>create().toSerialized();

  public Observable<T> dataStream() {
    return dataSubject.asObservable();
  }

  protected void setData(T data) {
    dataSubject.call(data);
  }
}
