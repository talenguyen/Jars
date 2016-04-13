package vn.tale.jars.repository;

import java.util.Collection;
import java.util.List;

import rx.Observable;

public interface Repository<T> {

  Observable<Long> add(T item);

  Observable<Integer> add(Collection<T> items);

  Observable<Boolean> update(T item);

  Observable<Boolean> remove(T item);

  Observable<Integer> remove(Specification specification);

  Observable<List<T>> query(Specification specification);
}