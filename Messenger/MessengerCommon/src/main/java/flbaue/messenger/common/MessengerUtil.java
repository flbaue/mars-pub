/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Florian Bauer on 24.04.14. flbaue@posteo.de
 */
public class MessengerUtil {

    public static void closeServerSocketSafely(ServerSocket serverSocket) {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeSocketSafely(Socket socket) {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
