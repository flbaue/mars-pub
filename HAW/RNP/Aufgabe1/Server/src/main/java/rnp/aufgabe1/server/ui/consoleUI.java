/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package rnp.aufgabe1.server.ui;

import rnp.aufgabe1.server.core.Server;

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
        System.out.println("Remote Shutdown Password:");

        //TODO UI

//        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
//        String input;
//        while (!(input = scanner.next()).equals("exit")) {
//            switch (input) {
//                case "start":
//                    server.startServices();
//                    break;
//                case "stop":
//                    server.shutdownServices();
//                    break;
//                case "restart":
//                    server.restart();
//                    break;
//                default:
//                    System.out.println("> Unknown command");
//                    break;
//            }
//        }


        Server server = new Server();
        server.start(port,secretToken);
    }
}
