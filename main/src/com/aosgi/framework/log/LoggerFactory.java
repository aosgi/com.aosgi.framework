package com.aosgi.framework.log;

import java.util.HashMap;
import java.util.Map;

/**
 * The factory for logger initializing
 * 
 * @author johnson
 * 
 */
public final class LoggerFactory {

	private static final Map<String, Logger> CACHE = new HashMap<String, Logger>();

	/**
	 * Returns a logger instance for the specified class
	 * 
	 * @param clazz
	 *            The class which the logger logging for
	 * @return a logger instance
	 */
	public static final Logger getLogger(final Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	/**
	 * Returns a logger instance with the specified name
	 * 
	 * @param name
	 *            The name of logger
	 * @return a logger instance
	 */
	public static final Logger getLogger(final String name) {
		synchronized (LoggerFactory.CACHE) {
			if (LoggerFactory.CACHE.containsKey(name)) {
				return LoggerFactory.CACHE.get(name);
			}

			final Logger logger = new DelegatedLogger(name);
			CACHE.put(name, logger);
			return logger;
		}
	}

}
