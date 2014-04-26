/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.client.core;

import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessageListener;

import java.util.Set;
import java.util.concurrent.BlockingDeque;

/**
 * Created by Florian Bauer on 26.04.14. flbaue@posteo.de
 */
public class MessageProcessor implements Runnable {

    private Set<MessageListener> messageObservers;
    private BlockingDeque<Message> messageQueue;

    public MessageProcessor(Set<MessageListener> messageObservers, BlockingDeque<Message> messageQueue) {
        this.messageObservers = messageObservers;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Message message = messageQueue.takeFirst();
                for (MessageListener listener : messageObservers) {
                    listener.update(message.clone());
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("MessageProcessor exit");
    }
}
