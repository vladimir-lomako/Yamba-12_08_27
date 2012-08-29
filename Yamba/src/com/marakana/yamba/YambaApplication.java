package com.marakana.yamba;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * YambaApplication
 */
public class YambaApplication extends Application
    implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private static final String TAG = "YambaApplication";
    private static final String KEY_USERNAME = "PREFS_USER";
    private static final String KEY_PASSWORD = "PREFS_PWD";
    private static final String KEY_API_ROOT = "PREFS_URL";
    private static final String DEFAULT_API_ROOT = "http://yamba.marakana.com/api";

    private Twitter twitter;

    /**
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Application up!");

        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this);
    }


    /**
     * Don't use an anonymous class to handle this event!
     * http://stackoverflow.com/questions/3799038/onsharedpreferencechanged-not-fired-if-change-occurs-in-separate-activity
     *
     * @see android.content.SharedPreferences.OnSharedPreferenceChangeListener#onSharedPreferenceChanged(android.content.SharedPreferences, java.lang.String)
     */
    @Override
    public synchronized void onSharedPreferenceChanged(SharedPreferences key, String val) {
        twitter = null;
    }

    /**
     * @return a current, valid, twitter object
     */
    public synchronized Twitter getTwitter() {
        if (twitter == null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String user = prefs.getString(KEY_USERNAME, "");
            String pass = prefs.getString(KEY_PASSWORD, "");
            String api = prefs.getString(KEY_API_ROOT, DEFAULT_API_ROOT);

            Twitter t = new Twitter(user, pass);
            t.setAPIRootUrl(api);

            Log.d(TAG, "new twitter: " + user + ", " + pass + ", " + api);
            twitter = t;
        }

        return twitter;
    }
}
