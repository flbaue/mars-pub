/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package marstools.io;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Florian Bauer on 23.05.14. flbaue@posteo.de
 */
public class Streams {

    public static void close(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }

    public static byte[] toByteArray(final InputStream is) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[0];
        try {
            int reads = is.read();
            while (reads != -1) {
                baos.write(reads);
                reads = is.read();
            }
            bytes = baos.toByteArray();
        } catch (IOException e) {

        } finally {
            close(baos);
        }

        return bytes;
    }

    public static byte[] toByteArrayAndClose(final InputStream is) {
        final byte[] bytes = toByteArray(is);
        close(is);
        return bytes;
    }
}
