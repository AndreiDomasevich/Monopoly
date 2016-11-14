package com.example.gardi.monopoly;

/**
 * Created by gardi on 14.11.2016.
 */
public class Client extends User {

    private String serverMacAddress;

    Client(int uniqueId) {
        super();
        this.uniqueId = uniqueId;
    }
}
