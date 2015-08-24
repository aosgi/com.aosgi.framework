package com.aosgi.framework.log;

/**
 * The logger is used for logging
 * 
 * @author johnson
 * 
 */
public interface Logger {

	/**
	 * Returns the name of this logger
	 * 
	 * @return the logger name
	 */
	public String getName();

	/**
	 * Test whether this logger is enabled for TRACE level
	 * 
	 * @return true only if the TRACE level is enabled
	 */
	public boolean isTraceEnabled();

	/**
	 * Test whether this logger is enabled for DEBUG level
	 * 
	 * @return true only if the DEBUG level is enabled
	 */
	public boolean isDebugEnabled();

	/**
	 * Test whether this logger is enabled for INFO level
	 * 
	 * @return true only if the INFO level is enabled
	 */
	public boolean isInfoEnabled();

	/**
	 * Test whether this logger is enabled for WARN level
	 * 
	 * @return true only if the WARN level is enabled
	 */
	public boolean isWarnEnabled();

	/**
	 * Test whether this logger is enabled for ERROR level
	 * 
	 * @return true only if the ERROR level is enabled
	 */
	public boolean isErrorEnabled();

	/**
	 * Log a message at TRACE level
	 * 
	 * @param msg
	 *            The message accompanying the exception
	 * @param t
	 *            The exception to log
	 */
	public void trace(final String msg, final Throwable t);

	/**
	 * Log a message at TRACE level
	 * 
	 * @param msg
	 *            The format string
	 * @param args
	 *            The arguments
	 */
	public void trace(final String msg, final Object... args);

	/**
	 * Log a message at DEBUG level
	 * 
	 * @param msg
	 *            The message accompanying the exception
	 * @param t
	 *            The exception to log
	 */
	public void debug(final String msg, final Throwable t);

	/**
	 * Log a message at DEBUG level
	 * 
	 * @param msg
	 *            The format string
	 * @param args
	 *            The arguments
	 */
	public void debug(final String msg, final Object... args);

	/**
	 * Log a message at INFO level
	 * 
	 * @param msg
	 *            The message accompanying the exception
	 * @param t
	 *            The exception to log
	 */
	public void info(final String msg, final Throwable t);

	/**
	 * Log a message at INFO level
	 * 
	 * @param msg
	 *            The format string
	 * @param args
	 *            The arguments
	 */
	public void info(final String msg, final Object... args);

	/**
	 * Log a message at WARN level
	 * 
	 * @param msg
	 *            The message accompanying the exception
	 * @param t
	 *            The exception to log
	 */
	public void warn(final String msg, final Throwable t);

	/**
	 * Log a message at WARN level
	 * 
	 * @param msg
	 *            The format string
	 * @param args
	 *            The arguments
	 */
	public void warn(final String msg, final Object... args);

	/**
	 * Log a message at ERROR level
	 * 
	 * @param msg
	 *            The message accompanying the exception
	 * @param t
	 *            The exception to log
	 */
	public void error(final String msg, final Throwable t);

	/**
	 * Log a message at ERROR level
	 * 
	 * @param msg
	 *            The format string
	 * @param args
	 *            The arguments
	 */
	public void error(final String msg, final Object... args);

}
