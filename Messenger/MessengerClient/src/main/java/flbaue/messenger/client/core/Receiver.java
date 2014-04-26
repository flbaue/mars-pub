/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.client.core;

import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessengerUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingDeque;
import java.util.zip.GZIPInputStream;

/**
 * Created by Florian Bauer on 26.04.14. flbaue@posteo.de
 */
public class Receiver implements Runnable {

    ServerSocket serverSocket;
    private BlockingDeque<Message> receiveQueue;
    private int port;

    public Receiver(BlockingDeque<Message> receiveQueue, int port) {
        this.receiveQueue = receiveQueue;
        this.port = port;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
                Socket connection = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(new BufferedInputStream(connection
                        .getInputStream())));
                Message message = (Message) in.readObject();
                receiveQueue.add(message);
                in.close();
            } catch (SocketException e) {
                //e.printStackTrace();
                Thread.currentThread().interrupt();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                MessengerUtil.closeServerSocketSafely(serverSocket);
            }
        }
        System.out.println("Receiver exit");
    }

    public void shutdown() {
        MessengerUtil.closeServerSocketSafely(serverSocket);
    }
}
