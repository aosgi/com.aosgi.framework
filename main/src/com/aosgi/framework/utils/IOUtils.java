package com.aosgi.framework.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import android.net.Uri;
import android.webkit.MimeTypeMap;

public final class IOUtils {

    private static final int BUFSIZ = 4096;

    /**
     * Join all arguments together and normalize the resulting path.
     * 
     * @param segments
     *            Path segments
     * @return The file of joined path
     */
    public static File join(final String... segments) {
        if (null == segments || segments.length <= 0)
            throw new IllegalArgumentException("Path segments required");

        File file = new File(segments[0]);

        for (int i = 1; i < segments.length; i++) {
            file = new File(file, segments[i]);
        }

        return file;
    }

    /**
     * Returns the MIME type of the specified file
     * 
     * @param file
     *            A file
     * @return The MIME type string
     */
    public static String getMimeType(final File file) {
        final Uri uri = Uri.fromFile(file);
        final MimeTypeMap mtm = MimeTypeMap.getSingleton();
        final String ext = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
        return mtm.getMimeTypeFromExtension(ext);
    }

    /**
     * Implements the same behavior as the {@code touch} utility on Unix.
     * 
     * @param file
     *            The file to touch
     * @throws IOException
     */
    public static void touch(final File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }

        file.setLastModified(System.currentTimeMillis());
    }

    /**
     * Delete the specified file
     * 
     * @param file
     *            The file to be deleted
     * @param recursive
     *            The value that indicated whether delete the specified file
     *            recursively or not if the specified file is a directory
     */
    public static void delete(final File file, final boolean recursive) {
        if (file.isFile() || !recursive) {
            file.delete();
            return;
        }

        final Stack<File> stack = new Stack<File>();
        stack.push(file);

        do {
            final File[] files = stack.peek().listFiles();
            if (null != files && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    final File f = files[i];

                    if (f.isDirectory()) {
                        stack.push(f);
                    } else if (f.isFile()) {
                        f.delete();
                    }
                }
            } else {
                stack.pop().delete();
            }
        } while (!stack.isEmpty());
    }

    /**
     * Write an integer value into the specified file.
     * 
     * @param file
     *            The target file
     * @param data
     *            The integer value
     * @throws IOException
     */
    public static void writeInt(final File file, final int data) throws IOException {
        writeInt(file.getAbsolutePath(), data);
    }

    /**
     * Write an integer value into the specified path.
     * 
     * @param path
     *            The target file path
     * @param data
     *            The integer value
     * @throws IOException
     */
    public static void writeInt(final String path, final int data) throws IOException {
        DataOutputStream dos = null;

        try {
            dos = new DataOutputStream(new FileOutputStream(path));
            dos.writeInt(data);
            dos.flush();
        } finally {
            closeQuietly(dos);
        }
    }

    /**
     * Write a long value into the specified file
     * 
     * @param file
     *            The target file
     * @param data
     *            The long value
     * @throws IOException
     */
    public static void writeLong(final File file, final long data) throws IOException {
        writeLong(file.getAbsolutePath(), data);
    }

    /**
     * Write a long value into the specified path.
     * 
     * @param path
     *            The target file path
     * @param data
     *            The long value
     * @throws IOException
     */
    public static void writeLong(final String path, final long data) throws IOException {
        DataOutputStream dos = null;

        try {
            dos = new DataOutputStream(new FileOutputStream(path));
            dos.writeLong(data);
            dos.flush();
        } finally {
            closeQuietly(dos);
        }
    }

    /**
     * Write a string value into the specified file.
     * 
     * @param path
     *            The target file
     * @param data
     *            The string value
     * @throws IOException
     */
    public static void writeString(final File file, final String data) throws IOException {
        writeString(file.getAbsolutePath(), data);
    }

    /**
     * Write a string value into the specified path.
     * 
     * @param path
     *            The target file path
     * @param data
     *            The string value
     * @throws IOException
     */
    public static void writeString(final String path, final String data) throws IOException {
        DataOutputStream dos = null;

        try {
            dos = new DataOutputStream(new FileOutputStream(path));
            dos.writeBytes(data);
            dos.flush();
        } finally {
            closeQuietly(dos);
        }
    }

    /**
     * Read an integer value from the specified file
     * 
     * @param file
     *            The target file
     * @return An integer value
     * @throws IOException
     */
    public static int readAsInt(final File file) throws IOException {
        return readAsInt(file.getAbsolutePath());
    }

    /**
     * Read an integer value from the specified path
     * 
     * @param path
     *            The target file path
     * @return An integer value
     * @throws IOException
     */
    public static int readAsInt(final String path) throws IOException {
        FileInputStream fis = null;

        try {
            return readAsInt(fis = new FileInputStream(path));
        } finally {
            closeQuietly(fis);
        }
    }

    /**
     * Read an integer value from the specified path
     * 
     * @param path
     *            The target file path
     * @return An integer value
     * @throws IOException
     */
    public static int readAsInt(final InputStream in) throws IOException {
        return new DataInputStream(in).readInt();
    }

    /**
     * Read a long value from the specified file
     * 
     * @param file
     *            The target file
     * @return A long value
     * @throws IOException
     */
    public static long readAsLong(final File file) throws IOException {
        return readAsLong(file.getAbsolutePath());
    }

    /**
     * Read a long value from the specified path
     * 
     * @param path
     *            The target file path
     * @return A long value
     * @throws IOException
     */
    public static long readAsLong(final String path) throws IOException {
        FileInputStream fis = null;

        try {
            return readAsLong(fis = new FileInputStream(path));
        } finally {
            closeQuietly(fis);
        }
    }

    /**
     * Read a long value from the specified input stream
     * 
     * @param is
     *            The target input stream
     * @return A long value
     * @throws IOException
     */
    public static long readAsLong(final InputStream is) throws IOException {
        return new DataInputStream(is).readLong();
    }

    /**
     * Read a string value from the specified file
     * 
     * @param file
     *            The target file
     * @return A string value
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String readAsString(final File file) throws FileNotFoundException, IOException {
        return readAsString(file.getAbsolutePath());
    }

    /**
     * Read a string value from the specified path
     * 
     * @param path
     *            The target file path
     * @return A string value
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String readAsString(final String path) throws FileNotFoundException, IOException {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(path);
            return readAsString(fis);
        } finally {
            closeQuietly(fis);
        }
    }

    /**
     * Read a string value from the specified input stream
     * 
     * @param is
     *            The target input stream
     * @return A string value
     * @throws IOException
     */
    public static String readAsString(final InputStream is) throws IOException {
        final byte[] buf = new byte[BUFSIZ];
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (int n = 0; (n = is.read(buf)) > 0;) {
            baos.write(buf, 0, n);
        }

        return baos.toString();
    }

    /**
     * Read a byte array value from the specified input stream
     * 
     * @param path
     *            The target file path
     * @return An array value
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] readAsBytes(final String path) throws FileNotFoundException, IOException {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(path);
            return readAsBytes(fis);
        } finally {
            closeQuietly(fis);
        }
    }

    /**
     * Read a byte array value from the specified input stream
     * 
     * @param is
     *            The target input stream
     * @return An array value
     * @throws IOException
     */
    public static byte[] readAsBytes(final InputStream is) throws IOException {
        final byte[] buf = new byte[BUFSIZ];
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (int n = 0; 0 != (n = is.read(buf));) {
            baos.write(buf, 0, n);
        }

        return baos.toByteArray();
    }

    /**
     * Close the closeables quietly
     * 
     * @param closeables
     *            Instances of {@link Closeable}
     */
    public static void closeQuietly(final Closeable... closeables) {
        if (null == closeables || closeables.length <= 0)
            return;

        for (int i = 0; i < closeables.length; i++) {
            final Closeable closeable = closeables[i];
            if (null == closeable)
                continue;

            try {
                closeables[i].close();
            } catch (IOException e) {
            }
        }
    }

    private IOUtils() {
    }
}
