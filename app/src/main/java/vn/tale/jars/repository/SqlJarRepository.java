package vn.tale.jars.repository;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.pushtorefresh.storio.sqlite.queries.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.Observable;
import vn.tale.jars.database.entity.JarEntity;
import vn.tale.jars.database.table.JarTable;
import vn.tale.jars.model.ImmutableJar;
import vn.tale.jars.model.Jar;
import vn.tale.jars.ui.list.JarRepository;
import vn.tale.jars.util.Transformers;

/**
 * Created by Giang Nguyen at Tiki on 4/13/16.
 */
public class SqlJarRepository implements JarRepository {

  private StorIOSQLite storIOSQLite;

  public SqlJarRepository(StorIOSQLite storIOSQLite) {
    this.storIOSQLite = storIOSQLite;
  }

  @Override public Observable<Long> add(Jar item) {
    final JarEntity jarEntity = copyOf(item);
    return storIOSQLite.put()
        .object(jarEntity)
        .prepare()
        .asRxObservable()
        .map(PutResult::insertedId);
  }

  @NonNull private JarEntity copyOf(Jar item) {
    return new JarEntity(item.name(), item.fullName(), item.rate(), item.amount());
  }

  @Override public Observable<Integer> add(Collection<Jar> items) {
    final List<Jar> jars;
    if (items instanceof List) {
      jars = (List<Jar>) items;
    } else {
      jars = new ArrayList<>(items);
    }
    final List<JarEntity> entities = Transformers.transform(jars, this::copyOf);
    return storIOSQLite.put()
        .objects(entities)
        .prepare()
        .asRxObservable()
        .map(PutResults::numberOfInserts);
  }

  @Override public Observable<Boolean> update(Jar item) {
    return null;
  }

  @Override public Observable<Boolean> remove(Jar item) {
    return null;
  }

  @Override public Observable<Integer> remove(Specification specification) {
    return null;
  }

  @Override public Observable<List<Jar>> query(Specification specification) {
    return storIOSQLite.get()
        .listOfObjects(JarEntity.class)
        .withQuery(Query.builder()
            .table(JarTable.TABLE)
            .build())
        .prepare()
        .asRxObservable()
        .map(jarEntities -> Transformers.transform(jarEntities, (Transformers.TransformFunc<JarEntity, Jar>) ImmutableJar::copyOf));
  }
}
