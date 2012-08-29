package com.marakana.yamba.data;

import android.provider.BaseColumns;


/**
 *
 * @version $Revision: $
 * @author <a href="mailto:blake.meike@gmail.com">G. Blake Meike</a>
 */
public final class TimelineContract {

    // Prevent instantiation
    private TimelineContract() {}

    /** Our table */
    public static final String TABLE = "timeline";

    /**
     * Column definitions for status information.
     */
    public final static class Columns implements BaseColumns {

        // Prevent instantiation
        private Columns() {}

        /** */
        public static final String CREATED_AT = "created_at";
        /** */
        public static final String TEXT = "txt";
        /** */
        public static final String USER = "user";

        /** The default sort order for this table, reverse chronological order */
        public static final String DEFAULT_SORT_ORDER = CREATED_AT + " COLLATE LOCALIZED DESC";
    }
}
