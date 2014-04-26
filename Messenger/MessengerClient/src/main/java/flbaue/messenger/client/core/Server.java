/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.messenger.client.core;

/**
 * Created by Florian Bauer on 26.04.14. flbaue@posteo.de
 */
public class Server {

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean validate() {
        return host != null && !host.isEmpty() && port > 0;
    }
}
