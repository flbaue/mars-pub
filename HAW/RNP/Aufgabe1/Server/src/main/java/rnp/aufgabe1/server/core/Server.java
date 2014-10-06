/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package rnp.aufgabe1.server.core;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Florian Bauer on 06.10.14. flbaue@posteo.de
 */
public class Server {

    private final BlockingDeque<IncomingMessage> receiverQueue;
    private final BlockingDeque<Message> broadcasterQueue;
    private final Set<Client> clients;
    private Receiver receiver;
    private Broadcaster broadcaster;
    private CommandProcessor commandProcessor;

    public Server() {
        receiverQueue = new LinkedBlockingDeque();
        broadcasterQueue = new LinkedBlockingDeque();
        clients = new HashSet<>();
    }

    public void start(int port, String password) {
        receiver = new Receiver(receiverQueue, port, clients);
        Thread receiverThread = new Thread(receiver);
        receiverThread.setName("receiverThread");
        receiverThread.start();

        commandProcessor = new CommandProcessor(receiverQueue, broadcasterQueue, clients, password, this);
        Thread commandProcessorThread = new Thread(commandProcessor);
        commandProcessorThread.setName("commandProcessorThread");
        commandProcessorThread.start();

        broadcaster = new Broadcaster(broadcasterQueue, clients);
        Thread broadcasterThread = new Thread(broadcaster);
        broadcasterThread.setName("broadcasterThread");
        broadcasterThread.start();
    }

    public void shutdown() {
        receiver.shutdown();
        commandProcessor.shutdown();
        broadcaster.shutdown();
    }

}
