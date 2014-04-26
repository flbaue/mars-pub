/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.server;

import flbaue.messenger.common.Client;
import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessageCommand;

import java.util.Set;
import java.util.concurrent.BlockingDeque;

/**
 * Created by Florian Bauer on 25.04.14. flbaue@posteo.de
 */
public class CommandProcessor implements Runnable {

    private BlockingDeque<Message> messageReceiveQueue;
    private BlockingDeque<Message> messageBroadcastQueue;
    private Set<Client> clients;
    private String secretToken;

    public CommandProcessor(BlockingDeque<Message> messageReceiveQueue, BlockingDeque<Message> messageBroadcastQueue, Set<Client> clients) {
        this.messageReceiveQueue = messageReceiveQueue;
        this.messageBroadcastQueue = messageBroadcastQueue;
        this.clients = clients;
        secretToken = "";
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Message message;
            try {
                message = messageReceiveQueue.takeFirst();
                MessageCommand command = message.getCommand();

                switch (command) {
                    case LOGIN:
                        loginClient(message);
                        break;
                    case LOGOUT:
                        logoutClient(message);
                        break;
                    case FILE:
                    case TEXT:
                        addToBroadcastQueue(message);
                        break;
                    default: /* nothing */
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void addToBroadcastQueue(Message message) {
        if (authenticated(message.getSender())) {
            messageBroadcastQueue.add(message);
        }
    }

    private boolean authenticated(Client sender) {
        return clients.contains(sender);
    }

    private void logoutClient(Message message) {
        clients.remove(message.getSender());
    }

    private void loginClient(Message message) {
        if (clients.isEmpty() && secretToken.isEmpty()) {
            secretToken = message.getText();
        }

        boolean login = false;
        if (secretToken.equals(message.getText())) {
            clients.add(message.getSender());
            login = true;
        }

        Message response = new Message();
        response.setCommand(MessageCommand.SERVER_RESPONSE);
        response.setReceiver(message.getSender());

        if (login) {
            response.setText("LOGIN OK");
        } else {
            response.setText("LOGIN FAILD");
        }
        messageBroadcastQueue.add(response);
    }
}
