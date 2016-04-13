package vn.tale.jars.pref;

import android.content.SharedPreferences;

/**
 * Created by Giang Nguyen at Tiki on 4/13/16.
 */
public class Settings {

  public static final String KEY_IS_FIRST_RUN = "isFirstRun";
  private SharedPreferences sharedPreferences;

  public Settings(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  public boolean isFirstRun() {
    return sharedPreferences.getBoolean(KEY_IS_FIRST_RUN, true);
  }

  public void setFirstRun(boolean firstRun) {
    sharedPreferences.edit()
        .putBoolean(KEY_IS_FIRST_RUN, firstRun)
        .apply();
  }
}
