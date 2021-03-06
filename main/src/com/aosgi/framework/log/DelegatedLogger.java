package com.aosgi.framework.log;

import org.slf4j.helpers.MessageFormatter;

class DelegatedLogger implements Logger {

	private final org.slf4j.Logger delegate;

	public DelegatedLogger(final Class<?> clazz) {
		this.delegate = org.slf4j.LoggerFactory.getLogger(clazz);
	}

	public DelegatedLogger(final String tag) {
		this.delegate = org.slf4j.LoggerFactory.getLogger(tag);
	}

	@Override
	public String getName() {
		return this.delegate.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		return this.delegate.isTraceEnabled();
	}

	@Override
	public boolean isDebugEnabled() {
		return this.delegate.isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return this.delegate.isInfoEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return this.delegate.isWarnEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return this.delegate.isErrorEnabled();
	}

	@Override
	public void trace(String msg, Throwable t) {
		this.delegate.trace(msg, t);
	}

	@Override
	public void trace(String msg, Object... args) {
		this.delegate.trace(MessageFormatter.format(msg, args).getMessage());
	}

	@Override
	public void debug(String msg, Throwable t) {
		this.delegate.debug(msg, t);
	}

	@Override
	public void debug(String msg, Object... args) {
		this.delegate.debug(MessageFormatter.format(msg, args).getMessage());
	}

	@Override
	public void info(String msg, Throwable t) {
		this.delegate.info(msg, t);
	}

	@Override
	public void info(String msg, Object... args) {
		this.delegate.info(MessageFormatter.format(msg, args).getMessage());
	}

	@Override
	public void warn(String msg, Throwable t) {
		this.delegate.warn(msg, t);
	}

	@Override
	public void warn(String msg, Object... args) {
		this.delegate.warn(MessageFormatter.format(msg, args).getMessage());
	}

	@Override
	public void error(String msg, Throwable t) {
		this.delegate.error(msg, t);
	}

	@Override
	public void error(String msg, Object... args) {
		this.delegate.error(MessageFormatter.format(msg, args).getMessage());
	}

}
