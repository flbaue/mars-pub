/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package rnp.aufgabe1.server.ui;

import rnp.aufgabe1.server.core.Server;

import java.util.Scanner;

/**
 * Created by Florian Bauer on 06.10.14. flbaue@posteo.de
 */
public class consoleUI {

    private int port;
    private String secretToken;

    public static void main(String[] args) {
        new consoleUI().run(args);
    }

    private void run(String[] args) {


        System.out.println("Server Startup");
        System.out.println("Port: " + port);
        System.out.println("Remote Shutdown Password:" + secretToken);
        System.out.println("press 'q' to shutdown locally");

        Server server = new Server();
        server.start(port,secretToken);

        System.out.println("Server is running");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String input;
        while (!(scanner.next()).equals("q")) {
            //nothing
        }
        System.out.println("Server is shutting down");
        server.shutdown();
        System.out.println("Goodbye");

    }
}
