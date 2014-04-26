/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.client;

import flbaue.messenger.client.console.ConsoleInterface;
import flbaue.messenger.client.gui.GraphicalInterface;

/**
 * Created by Florian Bauer on 26.04.14. flbaue@posteo.de
 */
public class Starter {

    public static void main(String[] args) {
        String ui = args[0];
        if (!ui.equals("gui")) {
            new ConsoleInterface(args).run();
        }
        if (!ui.equals("cmd")) {
            new GraphicalInterface(args).run();
        }
    }
}
