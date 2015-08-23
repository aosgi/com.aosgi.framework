package com.aosgi.framework.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Utility for telephony accessing
 * 
 * @author johnson
 * 
 */
public final class TelephonyUtils {

    /**
     * Returns the device id
     * 
     * @param context
     * @return The device id
     */
    public static String getDeviceId(final Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null == tm)
            return null;

        return tm.getDeviceId();
    }

    /**
     * Returns the SIM serial number
     * 
     * @param context
     * @return The SIM serial number
     */
    public static String getSimSerialNumber(final Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null == tm)
            return null;

        return tm.getSimSerialNumber();
    }

    /**
     * Returns a constant indicating the state of the device SIM card.
     * 
     * @param context
     * @return The state of the device SIM card.
     */
    public static int getSimState(final Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null == tm)
            return TelephonyManager.SIM_STATE_UNKNOWN;

        return tm.getSimState();
    }

    /**
     * Returns a constant indicating the device phone type. This indicates the
     * type of radio used to transmit voice calls.
     * 
     * @param context
     * @return The phone type.
     */
    public static int getPhoneType(final Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null == tm)
            return TelephonyManager.PHONE_TYPE_NONE;

        return tm.getPhoneType();
    }

    /**
     * Returns the network type for current data connection
     * 
     * @param context
     * @return The network type
     */
    public static int getNetworkType(final Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null == tm)
            return TelephonyManager.NETWORK_TYPE_UNKNOWN;

        return tm.getNetworkType();
    }

    private TelephonyUtils() {
    }

}
