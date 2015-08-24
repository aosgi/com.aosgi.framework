package com.aosgi.framework;

import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * A delegate of {@link UncaughtExceptionHandler}
 * 
 * @author johnson
 * 
 */
public class DelegatedUncaughtExceptionHandler implements UncaughtExceptionHandler {

	private final UncaughtExceptionHandler delegate;

	/**
	 * Create a delegate for default uncaught exception handler
	 */
	public DelegatedUncaughtExceptionHandler() {
		this(Thread.getDefaultUncaughtExceptionHandler());
	}

	/**
	 * Create a delegate for the specified {@code handler}
	 * 
	 * @param handler
	 *            An instance of {@link UncaughtExceptionHandler}
	 */
	public DelegatedUncaughtExceptionHandler(final UncaughtExceptionHandler handler) {
		if (null == handler) {
			this.delegate = null;
		} else {
			final ClassLoader classLoader = handler.getClass().getClassLoader();
			final Class<?>[] interfaces = new Class[] { UncaughtExceptionHandler.class };
			this.delegate = (UncaughtExceptionHandler) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					try {
						return method.invoke(handler, args);
					} catch (InvocationTargetException e) {
						return e.getTargetException();
					}
				}
			});
		}
	}

	@Override
	public void uncaughtException(final Thread thread, final Throwable t) {
		if (null == this.delegate)
			return;

		this.delegate.uncaughtException(thread, t);
	}

}
