package com.aosgi.framework;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * A delegate of {@link UncaughtExceptionHandler}
 * 
 * @author johnson
 * 
 */
public class UncaughtExceptionHandlerDelegate implements UncaughtExceptionHandler {

	private final UncaughtExceptionHandler delegate;

	/**
	 * Create a delegate for default uncaught exception handler
	 */
	public UncaughtExceptionHandlerDelegate() {
		this(Thread.getDefaultUncaughtExceptionHandler());
	}

	/**
	 * Create a delegate for the specified {@code handler}
	 * 
	 * @param handler
	 *            An instance of {@link UncaughtExceptionHandler}
	 */
	public UncaughtExceptionHandlerDelegate(final UncaughtExceptionHandler handler) {
		if (null == handler) {
			this.delegate = null;
		} else {
			this.delegate = UncaughtExceptionHandlerProxy.newInstance(handler);
		}
	}

	@Override
	public void uncaughtException(final Thread thread, final Throwable t) {
		if (null == this.delegate)
			return;

		this.delegate.uncaughtException(thread, t);
	}

}
