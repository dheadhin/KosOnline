package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DHEA on 20/11/2016.
 */

public class SessionManagement {
    public static final String KEY_USERNAME = "Email";
    public static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public SessionManagement(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences("KosOnline", PRIVATE_MODE);
        editor = pref.edit();
    }

    /* Get stored session data */
    public String getActiveInformation() {
        return pref.getString(KEY_USERNAME, "");
    }

    public void setActiveInformation(String username) {
        // Storing name in pref
        editor.putString(KEY_USERNAME, username);
        // commit changes
        editor.commit();
    }

    public boolean getKeyIsLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setKeyIsLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }
}

