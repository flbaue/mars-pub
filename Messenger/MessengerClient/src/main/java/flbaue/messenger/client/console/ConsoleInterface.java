/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.client.console;

import flbaue.messenger.client.core.Client;
import flbaue.messenger.common.Message;
import flbaue.messenger.common.MessageListener;

import java.io.*;
import java.nio.file.Files;
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
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String input;
        while (!(input = scanner.next()).equals("exit")) {
            if (input.matches("server:.+:\\d+:.*")) {
                // server:localhost:[port]:[password]
                connectToServer(input);
            } else if (input.matches("file:.*")) {
                String path = input.replace("file:", "");
                try {
                    client.sendFile(new File(path));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                client.sendMessage(input);
            }
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
        switch (message.getCommand()) {
            case FILE:
                incomingFile(message);
                break;
            default:
                incomingText(message);
                break;
        }

    }

    private void incomingText(Message message) {
        System.out.println(message.getCommand().toString() + " > " + message.getText());
    }

    private void incomingFile(Message message) {
        System.out.println("> Incoming file \"" + message.getText() + "\"");
        System.out.print("> Path to save or empty: ");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String input = "/Users/florian/" + message.getText();//scanner.next();
        if(input.isEmpty()) {
            System.out.println("> File not saved");
            return;
        }
        File file = new File(input);
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(file.toPath()))) {
            byte[] bytes = message.getBinary();
            out.write(bytes);
            out.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
