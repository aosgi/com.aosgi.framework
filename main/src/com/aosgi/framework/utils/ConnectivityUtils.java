package com.aosgi.framework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utility for connectivity management
 * 
 * @author johnson
 * 
 */
public final class ConnectivityUtils {

    /**
     * Returns the active network information
     * 
     * @param context
     *            The android context
     * @return The active network info or null if no network activated
     */
    public static NetworkInfo getActiveNetwork(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == cm)
            return null;

        return cm.getActiveNetworkInfo();
    }

    /**
     * Returns all network information
     * 
     * @param context
     *            The android context
     * @return All network info
     */
    public static NetworkInfo[] getAllNetworkInfo(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == cm)
            return new NetworkInfo[0];

        return cm.getAllNetworkInfo();
    }

    /**
     * Returns the active network type
     * 
     * @param context
     *            The android context
     * @return The active network type or -1 if no network activated
     */
    public static int getActiveNetworkType(final Context context) {
        final NetworkInfo ni = getActiveNetwork(context);
        if (null != ni) {
            return ni.getType();
        }

        return -1; // ConnectivityManager.TYPE_NONE;
    }

    private ConnectivityUtils() {
    }

}
