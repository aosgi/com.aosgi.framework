package com.aosgi.framework;

import android.app.Application;
import android.os.Bundle;

import com.aosgi.framework.utils.PackageUtils;

/**
 * The base application context
 * 
 * @author johnson
 * 
 */
public abstract class ApplicationContext extends Application {

	private static ApplicationContext instance;

	/**
	 * Returns the instance of {@link ApplicationContext}
	 * 
	 * @return The instance of {@link ApplicationContext}
	 */
	public static ApplicationContext getInstance() {
		return ApplicationContext.instance;
	}

	/**
	 * Default constructor
	 */
	public ApplicationContext() {
		ApplicationContext.instance = this;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// set default uncaught exception handler
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerDelegate());
	}

	/**
	 * Returns the application version code
	 * 
	 * @return The application version code
	 */
	public int getVersionCode() {
		return PackageUtils.getVersionCode(this);
	}

	/**
	 * Returns the application version name
	 * 
	 * @return The application version name
	 */
	public String getVersionName() {
		return PackageUtils.getVersionName(this);
	}

	/**
	 * Returns the application meta data
	 * 
	 * @return The application meta data
	 */
	public Bundle getMetaData() {
		return PackageUtils.getMetaData(this);
	}

}
