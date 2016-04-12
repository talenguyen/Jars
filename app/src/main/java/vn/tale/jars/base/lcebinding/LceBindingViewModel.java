package vn.tale.jars.base.lcebinding;

import com.jakewharton.rxrelay.BehaviorRelay;
import com.jakewharton.rxrelay.SerializedRelay;

import rx.Observable;
import vn.tale.lcebinding.LoadingContentError;

/**
 * Created by Giang Nguyen at Tiki on 4/9/16.
 */
public class LceBindingViewModel<T> {

  private final LoadingContentError lce;
  private final SerializedRelay<T, T> dataSubject = BehaviorRelay.<T>create().toSerialized();

  public LceBindingViewModel(LoadingContentError lce) {
    this.lce = lce;
  }

  public Observable<T> dataStream() {
    return dataSubject.asObservable();
  }

  public LoadingContentError getLce() {
    return lce;
  }

  protected void setData(T data) {
    dataSubject.call(data);
  }
}
