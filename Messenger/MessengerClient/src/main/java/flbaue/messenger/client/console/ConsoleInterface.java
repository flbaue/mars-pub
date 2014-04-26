/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.client.console;

import flbaue.messenger.client.core.Client;
import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessageListener;

import java.util.Scanner;

/**
 * Created by Florian Bauer on 26.04.14. flbaue@posteo.de
 */
public class ConsoleInterface implements MessageListener {

    private static final String PROMT = "> ";
    private Client client;

    public ConsoleInterface(String[] args) {
        int port = Integer.parseInt(args[1]);
        this.client = new Client(port);
        client.registerForIncomingMessages(this);
    }

    public void run() {

        welcome();
        //System.out.print(PROMT);
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String input;
        while (!(input = scanner.next()).equals("exit")) {
            if (input.matches("server:.+:\\d+:.*")) {
                connectToServer(input);
            } else {
                client.sendMessage(input);
            }

            //System.out.print(PROMT);
        }
        client.shutdown();
    }

    private void connectToServer(String input) {
        String[] parts = input.split(":");
        String host = parts[1];
        int port = Integer.parseInt(parts[2]);
        String secretToken = parts[3];
        client.connectToServer(host, port, secretToken);
    }

    private void welcome() {
        String out = "Messenger Client\n";
        out += "cmd interface (dev version)\n";
        out += "enter 'server:[host]:[port]:[password]' to connect to a server";
        System.out.println(out);
    }

    @Override
    public void update(Message message) {
        System.out.println(message.getCommand().toString() + " > " + message.getText());
    }
}
