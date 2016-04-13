package vn.tale.jars.util;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tale on 2/16/16.
 */
public class Transformers {

    public interface TransformFunc<S, D> {
        D transform(S source);
    }

    public interface Predicate<T> {
        boolean apply(T t);
    }


    public static <S, D> List<D> transform(List<S> source,
                                           @NonNull TransformFunc<S, D> transformFunc) {
        return transform(source, transformFunc, null);
    }

    public static <S, D> List<D> transform(List<S> source,
                                           @NonNull TransformFunc<S, D> transformFunc,
                                           Predicate<S> predicate) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        final List<D> result = new ArrayList<>();
        for (S s : source) {
            if (predicate != null && !predicate.apply(s)) {
                continue;
            }
            D d = transformFunc.transform(s);
            result.add(d);
        }
        return Collections.unmodifiableList(result);
    }

}
