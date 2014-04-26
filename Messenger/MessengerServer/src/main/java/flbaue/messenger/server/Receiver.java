/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.server;

import flbaue.messenger.common.Client;
import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessengerUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingDeque;
import java.util.zip.GZIPInputStream;

/**
 * Created by Florian Bauer on 24.04.14. flbaue@posteo.de
 */
public class Receiver implements Runnable {

    private int port;
    private BlockingDeque<Message> messageQueue;

    public Receiver(BlockingDeque<Message> messageQueue, int port) {
        this.messageQueue = messageQueue;
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (!Thread.currentThread().isInterrupted()) {
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(new BufferedInputStream(clientSocket.getInputStream
                        ())));
                Message message = (Message) in.readObject();
                Client sender = message.getSender();
                sender.setHost(clientSocket.getInetAddress().getHostAddress());
                messageQueue.addLast(message);
                in.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MessengerUtil.closeServerSocketSafely(serverSocket);
        }
    }
}
