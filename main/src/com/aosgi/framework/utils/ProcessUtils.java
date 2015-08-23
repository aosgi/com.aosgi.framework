package com.aosgi.framework.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;

/**
 * Utility for process accessing
 * 
 * @author johnson
 * 
 */
public final class ProcessUtils {

    /**
     * Returns the name of this process
     * 
     * @param ctx
     *            The context
     * @return the this process name
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getMyProcessName(final Context ctx) {
        final ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        if (null == am)
            return null;

        final int pid = android.os.Process.myPid();
        for (final RunningAppProcessInfo rapi : am.getRunningAppProcesses()) {
            if (rapi.pid == pid) {
                return rapi.processName;
            }
        }

        return null;
    }

    private ProcessUtils() {
    }

}
