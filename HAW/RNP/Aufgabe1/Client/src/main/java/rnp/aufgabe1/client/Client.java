/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package rnp.aufgabe1.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Florian Bauer on 07.10.14. flbaue@posteo.de
 */
public class Client {

    private final String serverAddress;
    private final int serverPort;

    public Client(final String serverAddress, final int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void sendMessage(String message) {
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            writer.write(message);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            //TODO Log
            e.printStackTrace();
        }
        waitForResponse();
    }

    private void waitForResponse() {
        
    }

}
