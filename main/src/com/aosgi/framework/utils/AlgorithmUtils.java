package com.aosgi.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility for algorithm
 * 
 * @author johnson
 * 
 */
public final class AlgorithmUtils {

    /**
     * Returns the MD5 value of the specified text
     * 
     * @param text
     *            The text
     * @return The MD5 value of the text
     */
    public static String md5(final String text) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            return toHexString(md.digest(text.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /**
     * Returns the MD5 value of the specified file
     * 
     * @param file
     *            The file
     * @return The MD5 value of file content
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String md5(final File file) throws FileNotFoundException, IOException {
        InputStream input = null;

        try {
            return md5(input = new FileInputStream(file));
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * Returns the MD5 value of the input stream
     * 
     * @param is
     *            The input stream
     * @return The MD5 value of the input stream
     * @throws IOException
     */
    public static String md5(final InputStream is) throws IOException {
        final byte[] buf = new byte[4096];

        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");

            for (int n = 0; (n = is.read(buf)) != -1;) {
                md.update(buf, 0, n);
            }

            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /**
     * Returns the specified data as hex sequence
     * 
     * @param data
     *            The data
     * @return a hex string
     */
    public static String toHexString(final byte[] data) {
        final int n = data.length;
        final StringBuilder hex = new StringBuilder();

        for (int i = 0; i < n; i++) {
            final byte b = data[i];
            hex.append(String.format("%02x", b & 0xff));
        }

        return hex.toString();
    }

    private AlgorithmUtils() {
    }

}
