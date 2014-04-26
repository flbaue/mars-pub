/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.client.core;

import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessageCommand;
import flbaue.messenger.common.MessageListener;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Florian Bauer on 26.04.14. flbaue@posteo.de
 */
public class Client {

    private int port_incoming;
    private Server server;
    private BlockingDeque<Message> sendQueue;
    private BlockingDeque<Message> receiveQueue;
    private Set<MessageListener> messageListeners;
    private Thread messageProcessorThread;
    private Thread receiverThread;
    private Thread senderThread;
    private Receiver receiver;

    public Client(int port_incoming) {
        this.port_incoming = port_incoming;
        server = new Server();
        sendQueue = new LinkedBlockingDeque<>();
        receiveQueue = new LinkedBlockingDeque<>();
        messageListeners = new HashSet<>();
        initializeThreads();
    }

    private void initializeThreads() {
        messageProcessorThread = new Thread(new MessageProcessor(messageListeners, receiveQueue));
        messageProcessorThread.setName("messageProcessorThread");
        messageProcessorThread.start();

        receiver = new Receiver(receiveQueue, port_incoming);
        receiverThread = new Thread(receiver);
        receiverThread.setName("receiverThread");
        receiverThread.start();

        senderThread = new Thread(new Sender(sendQueue, receiveQueue, server));
        senderThread.setName("senderThread");
        senderThread.start();
    }

    public void sendMessage(String text) {
        Message message = new Message(MessageCommand.TEXT, text, new flbaue.messenger.common.Client(null, port_incoming));
        sendQueue.add(message);
    }

    public void sendFile(File file) {
        throw new UnsupportedOperationException();
    }

    public void connectToServer(String host, int port, String secretToken) {
        server.setHost(host);
        server.setPort(port);
        Message message = new Message(MessageCommand.LOGIN, secretToken, new flbaue.messenger.common.Client(null,
                port_incoming));
        sendQueue.add(message);
    }

    public void registerForIncomingMessages(MessageListener listener) {
        messageListeners.add(listener);
    }

    public void shutdown() {
        messageProcessorThread.interrupt();
        receiver.shutdown();
        senderThread.interrupt();
        System.out.println("Client exit");
    }
}
