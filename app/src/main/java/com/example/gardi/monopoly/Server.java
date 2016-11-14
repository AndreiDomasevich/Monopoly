package com.example.gardi.monopoly;

/**
 * Created by gardi on 14.11.2016.
 */
public class Server extends User {

    private String[] clientMacAddress;
    public final static int MAX_CLIENTS = 3;

    Server() {
        super();
        uniqueId = 0;
        clientMacAddress = new String[MAX_CLIENTS];
    }

    public void setClientMacAddress(int uniqId, String macAddress){
        clientMacAddress[uniqId-1] = macAddress;                    //[uniqId - 1]: client1 id = 1, client2 id = 2
    }
}
