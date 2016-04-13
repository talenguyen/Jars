package vn.tale.jars.database.table;

import android.support.annotation.NonNull;

/**
 * Created by Giang Nguyen at Tiki on 4/13/16.
 */
public class JarTable {
  public static final String TABLE = "jar";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_FULL_NAME = "full_name";
  public static final String COLUMN_RATE = "rate";
  public static final String COLUMN_AMOUNT = "amount";

  // Better than static final field -> allows VM to unload useless String
  // Because you need this string only once per application life on the device
  @NonNull
  public static String getCreateTableQuery() {
    return "CREATE TABLE " + TABLE + "("
        + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
        + COLUMN_NAME + " TEXT NOT NULL UNIQUE, "
        + COLUMN_FULL_NAME + " TEXT, "
        + COLUMN_RATE + " REAL, "
        + COLUMN_AMOUNT + " INTEGER"
        + ");";
  }
}
