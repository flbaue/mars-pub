/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.server;

import flbaue.messenger.common.Client;
import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessengerUtil;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Florian Bauer on 24.04.14. flbaue@posteo.de
 */
public class Broadcaster implements Runnable {

    private BlockingDeque<Message> messageQueue;
    private Set<Client> clients;

    public Broadcaster(BlockingDeque<Message> messageQueue, Set<Client> clients) {
        this.messageQueue = messageQueue;
        this.clients = clients;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Message message;
            try {
                message = messageQueue.takeFirst();
                if (message.getReceiver() != null) {
                    sendToReceiver(message);
                } else {
                    broadcast(message);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void sendToReceiver(Message message) {
        Client client = message.getReceiver();
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(client.getHost(), client.getPort());
            ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new BufferedOutputStream(clientSocket
                    .getOutputStream())));
            out.writeObject(message);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            MessengerUtil.closeSocketSafely(clientSocket);
        }
    }

    private void broadcast(Message message) {
        for (Client client : clients) {
            if (message.getSender().equals(client)) {
                continue;
            }
            message.setReceiver(client);
            sendToReceiver(message);
        }
    }
}
