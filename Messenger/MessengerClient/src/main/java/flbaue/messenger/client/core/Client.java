/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.client.core;

import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessageCommand;
import flbaue.messenger.common.MessageListener;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Receiver receiver;
    private MessageProcessor messageProcessor;
    private Sender sender;

    public Client(int port_incoming) {
        this.port_incoming = port_incoming;
        server = new Server();
        sendQueue = new LinkedBlockingDeque<>();
        receiveQueue = new LinkedBlockingDeque<>();
        messageListeners = new HashSet<>();
        initializeThreads();
    }

    private void initializeThreads() {
        messageProcessor = new MessageProcessor(messageListeners, receiveQueue);
        Thread messageProcessorThread = new Thread(messageProcessor);
        messageProcessorThread.setName("messageProcessorThread");
        messageProcessorThread.start();

        receiver = new Receiver(receiveQueue, port_incoming);
        Thread receiverThread = new Thread(receiver);
        receiverThread.setName("receiverThread");
        receiverThread.start();

        sender = new Sender(sendQueue, receiveQueue, server);
        Thread senderThread = new Thread(sender);
        senderThread.setName("senderThread");
        senderThread.start();
    }

    public void sendMessage(String text) {
        Message message = new Message(MessageCommand.TEXT, text, new flbaue.messenger.common.Client(null, port_incoming));
        sendQueue.add(message);
    }

    public void sendFile(File file) throws FileNotFoundException {

        if (!file.isFile()) {
            throw new FileNotFoundException(file + " does not exist or is not a file");
        }

        List<Byte> bytes = new ArrayList<>();

        try (InputStream in = Files.newInputStream(file.toPath())) {
            BufferedInputStream bin = new BufferedInputStream(in);
            for (int c; (c = in.read()) != -1; ) {
                bytes.add((byte) c);
            }
        } catch (IOException e) {
            //TODO
        }

        byte[] b = new byte[bytes.size()];

        for (int i = 0; i < bytes.size(); i++) {
            b[i] = bytes.get(i);
        }

        Message message = new Message(MessageCommand.FILE, file.getName(), b,
                new flbaue.messenger.common.Client(null, port_incoming));
        sendQueue.add(message);

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
        messageProcessor.shutdown();
        receiver.shutdown();
        sender.shutdown();
        System.out.println("Client exit");
    }
}
