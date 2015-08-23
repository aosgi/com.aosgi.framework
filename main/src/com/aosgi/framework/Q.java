package com.aosgi.framework;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Resource lookup utility
 * 
 * @author johnson
 *
 */
public final class Q {

	private static final Map<String, Integer> cache = new HashMap<String, Integer>();

	/**
	 * Lookup the specified resource from {@link R.anim}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int anim(final String name) {
		return q("anim", name);
	}

	/**
	 * Lookup the specified resource from {@link R.animator}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int animator(final String name) {
		return q("animator", name);
	}

	/**
	 * Lookup the specified resource from {@link R.array}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int array(final String name) {
		return q("array", name);
	}

	/**
	 * Lookup the specified resource from {@link R.attr}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int attr(final String name) {
		return q("attr", name);
	}

	/**
	 * Lookup the specified resource from {@link R.bool}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int bool(final String name) {
		return q("bool", name);
	}

	/**
	 * Lookup the specified resource from {@link R.color}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int color(final String name) {
		return q("color", name);
	}

	/**
	 * Lookup the specified resource from {@link R.dimen}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int dimen(final String name) {
		return q("dimen", name);
	}

	/**
	 * Lookup the specified resource from {@link R.drawable}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int drawable(final String name) {
		return q("drawable", name);
	}

	/**
	 * Lookup the specified resource from {@link R.fraction}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int fraction(final String name) {
		return q("fraction", name);
	}

	/**
	 * Lookup the specified resource from {@link R.id}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int id(final String name) {
		return q("id", name);
	}

	/**
	 * Lookup the specified resource from {@link R.integer}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int integer(final String name) {
		return q("integer", name);
	}

	/**
	 * Lookup the specified resource from {@link R.interpolator}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int interpolator(final String name) {
		return q("interpolator", name);
	}

	/**
	 * Lookup the specified resource from {@link R.layout}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int layout(final String name) {
		return q("layout", name);
	}

	/**
	 * Lookup the specified resource from {@link R.menu}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int menu(final String name) {
		return q("menu", name);
	}

	/**
	 * Lookup the specified resource from {@link R.mipmap}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int mipmap(final String name) {
		return q("mipmap", name);
	}

	/**
	 * Lookup the specified resource from {@link R.plurals}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int plurals(final String name) {
		return q("plurals", name);
	}

	/**
	 * Lookup the specified resource from {@link R.raw}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int raw(final String name) {
		return q("raw", name);
	}

	/**
	 * Lookup the specified resource from {@link R.string}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int string(final String name) {
		return q("string", name);
	}

	/**
	 * Lookup the specified resource from {@link R.style}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int style(final String name) {
		return q("style", name);
	}

	/**
	 * Lookup the specified resource from {@link R.xml}
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	public static int xml(final String name) {
		return q("xml", name);
	}

	/**
	 * Lookup the resource with the specified name
	 * 
	 * @param res
	 *            The resource category
	 * 
	 * @param name
	 *            The resource name
	 * @return The resource id
	 */
	private static int q(final String res, final String name) {
		final ApplicationContext app = ApplicationContext.getInstance();
		final String packageName = app.getPackageName();
		final String resType = packageName + ".R$" + res;
		final String resName = resType + "." + name;

		if (Q.cache.containsKey(resName)) {
			return Q.cache.get(resName);
		}

		try {
			final Class<?> clazz = Class.forName(resType);
			final Field field = clazz.getDeclaredField(name);
			final int resId = field.getInt(clazz);
			Q.cache.put(resName, Integer.valueOf(resId));
			return resId;
		} catch (Throwable t) {
			if (app.isDebuggable()) {
				new Handler(Looper.getMainLooper()).post(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(app, ":( " + resName + " not found", Toast.LENGTH_LONG).show();
					}
				});
			}
		}

		return 0;
	}

	private Q() {
	}

}
