package com.aosgi.framework;

import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Dynamic proxy of interface {@link UncaughtExceptionHandler}
 * 
 * @author johnson
 * 
 */
public class UncaughtExceptionHandlerProxy implements InvocationHandler {

	/**
	 * Create a proxy of interface {@link UncaughtExceptionHandler} for the
	 * default uncaught exception handler
	 * 
	 * @return A proxy of interface {@link UncaughtExceptionHandler}
	 */
	public static UncaughtExceptionHandler newInstance() {
		return newInstance(Thread.getDefaultUncaughtExceptionHandler());
	}

	/**
	 * Create a proxy of interface {@link UncaughtExceptionHandler} for
	 * {@code handler}
	 * 
	 * @param handler
	 *            An instance of {@link UncaughtExceptionHandler}
	 * @return A proxy of interface {@link UncaughtExceptionHandler}
	 */
	public static UncaughtExceptionHandler newInstance(final UncaughtExceptionHandler handler) {
		final ClassLoader classLoader = handler.getClass().getClassLoader();
		final Class<?>[] interfaces = new Class[] { UncaughtExceptionHandler.class };
		final UncaughtExceptionHandlerProxy proxy = new UncaughtExceptionHandlerProxy(handler);
		return (UncaughtExceptionHandler) Proxy.newProxyInstance(classLoader, interfaces, proxy);
	}

	private final UncaughtExceptionHandler handler;

	/**
	 * Create a proxy for the default uncaught exception handler
	 */
	private UncaughtExceptionHandlerProxy() {
		this(Thread.getDefaultUncaughtExceptionHandler());
	}

	/**
	 * Create a {@link UncaughtExceptionHandler} proxy
	 * 
	 * @param handler
	 *            An instance of {@link UncaughtExceptionHandler}
	 */
	private UncaughtExceptionHandlerProxy(final UncaughtExceptionHandler handler) {
		this.handler = handler;
	}

	@Override
	public Object invoke(final Object proxy, final Method method,final Object[] args) throws Throwable {
		try {
			return method.invoke(this.handler, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

}
