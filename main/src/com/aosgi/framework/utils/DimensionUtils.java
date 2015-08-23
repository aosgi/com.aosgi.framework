package com.aosgi.framework.utils;

import android.content.Context;

/**
 * Utility for dimension conversion
 * 
 * @author johnson
 * 
 */
public final class DimensionUtils {

    /**
     * Convert dp to pixel
     * 
     * @param context
     *            The android context
     * @param dp
     *            The value in dp
     * @return The dimension in pixel
     */
    public static int dp2px(final Context context, final float dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    /**
     * Convert sp to pixel
     * 
     * @param context
     *            The android context
     * @param sp
     *            The value in sp
     * @return The dimension in pixel
     */
    public static int sp2px(final Context context, final float sp) {
        return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * Convert pt to pixel
     * 
     * @param context
     *            The android context
     * @param pt
     *            The value in pt
     * @return The dimension in pixel
     */
    public static int pt2px(final Context context, final float pt) {
    	return Math.round(pt * context.getResources().getDisplayMetrics().xdpi * (1.0f / 72f));
    }

    /**
     * Convert inch to pixel
     * 
     * @param context
     *            The android context
     * @param in
     *            The value in inch
     * @return The dimension in pixel
     */
    public static int in2px(final Context context, final float in) {
    	return Math.round(in * context.getResources().getDisplayMetrics().xdpi);
    }

    /**
     * Convert millimeter to pixel
     * 
     * @param context
     *            The android context
     * @param millimeter
     *            The value in millimeter
     * @return The dimension in pixel
     */
    public static int mm2px(final Context context, final float mm) {
    	return Math.round(mm * context.getResources().getDisplayMetrics().xdpi * (1.0f / 25.4f));
    }

    /**
     * Convert pixel to dip
     * 
     * @param context
     *            The android context
     * @param px
     *            The value in pixel
     * @return The dimension in dp
     */
    public static int px2dp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().density);
    }

    /**
     * Convert pixel to dip
     * 
     * @param context
     *            The android context
     * @param px
     *            The value in pixel
     * @return The dimension in sp
     */
    public static int px2sp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * Convert pixel to pt
     * 
     * @param context
     *            The android context
     * @param px
     *            The value in pixel
     * @return The dimension in pt
     */
    public static int px2pt(final Context context, final float px) {
        return Math.round(px / (context.getResources().getDisplayMetrics().xdpi * (1.0f / 72f)));
    }

    /**
     * Convert pixel to inch
     * 
     * @param context
     *            The android context
     * @param px
     *            The value in pixel
     * @return The dimension in pixel
     */
    public static int px2in(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().xdpi);
    }

    /**
     * Convert pixel to millimeter
     * 
     * @param context
     *            The android context
     * @param px
     *            The value in pixel
     * @return The dimension in millimeter
     */
    public static int px2mm(final Context context, final float px) {
    	return Math.round(px / (context.getResources().getDisplayMetrics().xdpi * (1.0f / 25.4f)));
    }

    private DimensionUtils() {
    }

}
