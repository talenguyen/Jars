package vn.tale.jars.database.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import vn.tale.jars.database.table.JarTable;
import vn.tale.jars.model.Jar;

/**
 * Created by Giang Nguyen at Tiki on 4/13/16.
 */
@StorIOSQLiteType(table = JarTable.TABLE)
public class JarEntity implements Jar {

  @Nullable @StorIOSQLiteColumn(name = JarTable.COLUMN_ID, key = true)
  long id;

  @NonNull @StorIOSQLiteColumn(name = JarTable.COLUMN_NAME, key = true)
  String name;

  @NonNull @StorIOSQLiteColumn(name = JarTable.COLUMN_FULL_NAME)
  String fullName;

  @NonNull @StorIOSQLiteColumn(name = JarTable.COLUMN_RATE)
  float rate;

  @NonNull @StorIOSQLiteColumn(name = JarTable.COLUMN_AMOUNT)
  long amount;

  public JarEntity() {
  }

  public JarEntity(@NonNull String name, @NonNull String fullName, @NonNull float rate, @NonNull long amount) {
    this.name = name;
    this.fullName = fullName;
    this.rate = rate;
    this.amount = amount;
  }

  @Override public String name() {
    return name;
  }

  @Override public String fullName() {
    return fullName;
  }

  @Override public float rate() {
    return rate;
  }

  @Override public long amount() {
    return amount;
  }
}
