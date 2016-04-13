package vn.tale.jars.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.tale.jars.database.table.JarTable;

/**
 * Created by Giang Nguyen at Tiki on 4/13/16.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

  public static final int VERSION = 1;

  public DbOpenHelper(Context context) {
    super(context, "Jars_db", null, VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    db.execSQL(JarTable.getCreateTableQuery());
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // No-Op
  }
}
