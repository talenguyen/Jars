package vn.tale.jars.repository;

import java.util.List;

import rx.Observable;

public interface Repository<T> {

  void add(T item);

  void add(Iterable<T> items);

  Observable<Boolean> update(T item);

  Observable<Boolean> remove(T item);

  Observable<Integer> remove(Specification specification);

  Observable<List<T>> query(Specification specification);
}