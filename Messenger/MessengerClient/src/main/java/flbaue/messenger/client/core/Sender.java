/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.client.core;

import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessageCommand;
import flbaue.messenger.common.MessengerUtil;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingDeque;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Florian Bauer on 26.04.14. flbaue@posteo.de
 */
public class Sender implements Runnable {

    private BlockingDeque<Message> sendQueue;
    private BlockingDeque<Message> receiveQueue;
    private Server server;

    public Sender(BlockingDeque<Message> sendQueue, BlockingDeque<Message> receiveQueue, Server server) {
        this.sendQueue = sendQueue;
        this.receiveQueue = receiveQueue;
        this.server = server;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            Socket socket = null;
            Message message = null;
            try {
                message = sendQueue.takeFirst();
                if (!server.validate()) {
                    couldNotSend(message);
                    continue;
                }
                socket = new Socket(server.getHost(), server.getPort());
                ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new BufferedOutputStream(socket
                        .getOutputStream())));
                out.writeObject(message);
                out.close();
            } catch (IOException e) {
                if (message != null) {
                    couldNotSend(message);
                }
                e.printStackTrace();
            } catch (InterruptedException e) {
                //e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                MessengerUtil.closeSocketSafely(socket);
            }
        }
        System.out.println("Sender exit");
    }

    private void couldNotSend(Message message) {
        message.setCommand(MessageCommand.COULD_NOT_SEND);
        receiveQueue.add(message);
    }
}

