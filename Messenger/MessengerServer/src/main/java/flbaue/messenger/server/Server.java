/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.server;

import flbaue.messenger.common.Client;
import flbaue.messenger.common.Message;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Florian Bauer on 24.04.14. flbaue@posteo.de
 */
public class Server {

    public static int PORT = 33333;

    private BlockingDeque<Message> messageReceiveQueue;
    private BlockingDeque<Message> messageBroadcastQueue;
    private Set<Client> clients;

    public Server() {
        this.messageReceiveQueue = new LinkedBlockingDeque<>();
        this.messageBroadcastQueue = new LinkedBlockingDeque<>();
        this.clients = new HashSet<>();
    }

    public static void main(String[] args) {
        new Server().run(PORT);
    }

    private void run(int port) {
        Thread receiverThread = new Thread(new Receiver(messageReceiveQueue, port));
        receiverThread.setName("receiverThread");
        receiverThread.start();

        Thread commandProcessorThread = new Thread(new CommandProcessor(messageReceiveQueue, messageBroadcastQueue,
                clients));
        commandProcessorThread.setName("commandProcessorThread");
        commandProcessorThread.start();

        Thread broadcasterThread = new Thread(new Broadcaster(messageBroadcastQueue, clients));
        broadcasterThread.setName("broadcasterThread");
        broadcasterThread.start();

//        while (!Thread.currentThread().isInterrupted()) {
//            try {
//                Thread.currentThread().wait(5000);
//
//                if (!receiverThread.isAlive() || receiverThread.isInterrupted()) {
//                    System.out.println("-> receiverThread has stopped!");
//                }
//
//                if (!broadcasterThread.isAlive() || broadcasterThread.isInterrupted()) {
//                    System.out.println("-> broadcasterThread has stopped!");
//                }
//
//                if (!commandProcessorThread.isAlive() || commandProcessorThread.isInterrupted()) {
//                    System.out.println("-> commandProcessorThread has stopped!");
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
