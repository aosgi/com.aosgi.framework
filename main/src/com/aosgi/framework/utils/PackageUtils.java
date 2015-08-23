package com.aosgi.framework.utils;

import java.io.File;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

/**
 * Utility for package accessing
 * 
 * @author johnson
 * 
 */
public final class PackageUtils {

    /**
     * Test whether the specified package already install or not
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @return true if only the specified package already installed.
     */
    public static boolean isInstalled(final Context context, final String packageName) {
        return null != getPackageInfo(context, packageName);
    }

    /**
     * Install the specified package
     * 
     * @param context
     *            The android context
     * @param file
     *            The package file to be installed
     * @throws ActivityNotFoundException
     */
    public static final void installPackage(final Context context, final File file) throws ActivityNotFoundException {
        final Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * Returns the version code
     * 
     * @param context
     *            The android context
     * @return The package version code or -1 if package not found
     */
    public static final int getVersionCode(final Context context) {
        return getVersionCode(context, context.getPackageName());
    }

    /**
     * Returns the version code of the specified package
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @return The package version code or -1 if package not found
     */
    public static int getVersionCode(final Context context, final String packageName) {
        final PackageInfo pi = getPackageInfo(context, packageName);
        if (null != pi) {
            return pi.versionCode;
        }

        return -1;
    }

    /**
     * Returns the version name
     * 
     * @param context
     *            The android context
     * @return The package version name
     */
    public static final String getVersionName(final Context context) {
        return getVersionName(context, context.getPackageName());
    }

    /**
     * Return the version name of the specified package
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @return The package version name
     */
    public static String getVersionName(final Context context, final String packageName) {
        final PackageInfo pi = getPackageInfo(context, packageName);
        if (null != pi) {
            return pi.versionName;
        }

        return null;
    }

    /**
     * Returns all installed application informations in the device
     * 
     * @param context
     *            The android context
     * @param flags
     *            The flags
     * @return all install applications
     */
    public static List<ApplicationInfo> getInstalledApplications(final Context context, final int flags) {
        return context.getPackageManager().getInstalledApplications(flags);
    }

    /**
     * Returns the icon of the specified application
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @return the icon of the application
     */
    public static Drawable getApplicationIcon(final Context context, final String packageName) {
        final PackageManager pm = context.getPackageManager();
        final PackageInfo pi = getPackageInfo(context, packageName);
        if (null != pi) {
            return pm.getApplicationIcon(pi.applicationInfo);
        }

        return null;
    }

    /**
     * Returns the label of the specified application
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @return the label of the application
     */
    public static String getApplicationLabel(final Context context, final String packageName) {
        final PackageManager pm = context.getPackageManager();
        final PackageInfo pi = getPackageInfo(context, packageName);
        if (pi != null) {
            return String.valueOf(pm.getApplicationLabel(pi.applicationInfo));
        }

        return null;
    }

    /**
     * Returns the label of the specified activity
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @param className
     *            The activity class name
     * @return The label of the activity
     */
    public static String getActivityLabel(final Context context, final String packageName, final String className) {
        final PackageManager pm = context.getPackageManager();
        final Intent intent = new Intent().setClassName(packageName, className);
        final List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
        if (info != null && !info.isEmpty()) {
            return String.valueOf(info.get(0).activityInfo.loadLabel(pm));
        } else {
            return getApplicationLabel(context, packageName);
        }
    }

    /**
     * Returns the icon of the specified activity
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @param className
     *            The activity class name
     * @return The icon of the activity
     */
    public static Drawable getActivityIcon(final Context context, final String packageName, final String className) {
        final PackageManager pm = context.getPackageManager();
        final Intent intent = new Intent().setClassName(packageName, className);
        final List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
        if (info != null && !info.isEmpty()) {
            return info.get(0).activityInfo.loadIcon(pm);
        } else {
            return getApplicationIcon(context, packageName);
        }
    }

    /**
     * Returns the information of the specified package
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @return The package information
     */
    public static PackageInfo getPackageInfo(final Context context, final String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /**
     * Test whether the specified package has any launcher
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @return true if only the specified application has launcher activity
     */
    public static boolean hasLauncher(final Context context, final String packageName) {
        final List<ResolveInfo> launchers = getLaunchers(context, packageName);
        return launchers != null && !launchers.isEmpty();
    }

    /**
     * Returns all launchers in this device
     * 
     * @param context
     *            The android context
     * @return The resolve information of all launchers
     */
    public static List<ResolveInfo> getLaunchers(final Context context) {
        final PackageManager pm = context.getPackageManager();
        final Intent query = new Intent(Intent.ACTION_MAIN);
        query.addCategory(Intent.CATEGORY_LAUNCHER);
        return pm.queryIntentActivities(query, 0);
    }

    /**
     * Returns all launchers of the specified package
     * 
     * @param context
     *            The android context
     * @param packageName
     *            The package name
     * @return The resolve information of the specified package
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
    public static List<ResolveInfo> getLaunchers(final Context context, final String packageName) {
        final PackageManager pm = context.getPackageManager();
        final Intent query = new Intent(Intent.ACTION_MAIN);
        query.addCategory(Intent.CATEGORY_LAUNCHER);
        query.setPackage(packageName);
        return pm.queryIntentActivities(query, 0);
    }

	/**
	 * Returns the meta data of the specified application
	 * 
	 * @param context
	 *            The android context
	 * @return The meta data bundle
	 */
    public static Bundle getMetaData(final Context context) {
    	final PackageManager pm = context.getPackageManager();
    	final String packageName = context.getPackageName();

    	try {
			return pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA).metaData;
		} catch (NameNotFoundException e) {
			return null;
		}
    }

    /**
	 * Returns the meta data of the specified activity
	 * 
	 * @param context
	 *            The activity context
	 * @return The meta data bundle
	 */
    public static Bundle getMetaData(final Activity context) {
    	final PackageManager pm = context.getPackageManager();

    	try {
			final ComponentName componentName = ((Activity) context).getComponentName();
			return pm.getActivityInfo(componentName, PackageManager.GET_META_DATA).metaData;
		} catch (NameNotFoundException e) {
			return null;
		}
    }

    private PackageUtils() {
    }

}
