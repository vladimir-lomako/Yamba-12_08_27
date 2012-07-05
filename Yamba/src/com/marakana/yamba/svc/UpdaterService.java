package com.marakana.yamba.svc;

import java.util.Date;
import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.marakana.yamba.YambaApplication;


/**
 * UpdaterService
 */
public class UpdaterService extends IntentService {
    private static final String TAG = "UpdaterService";

    /** Ctor */
    public UpdaterService() { super(TAG); }

    /**
     * @see android.app.IntentService#onHandleIntent(android.content.Intent)
     */
    @Override
    protected void onHandleIntent(Intent arg0) {
        Log.v(TAG, "onHandleIntent() invoked");

        try {
            List<Twitter.Status> timeline
                = ((YambaApplication) getApplication()).getTwitter().getHomeTimeline();

            for (Twitter.Status status: timeline) {
                String name = status.user.name;
                String msg = status.text;
                long id = status.id;
                Date createdAt = status.createdAt;
                Log.v(TAG, name + "#" + id + " @" + createdAt + ": " + msg);
            }
        }
        catch (NullPointerException e) { /* handle twitter bug */ }
        catch (TwitterException e) { Log.w(TAG, "Failed to retrieve timeline data"); }
    }
}
