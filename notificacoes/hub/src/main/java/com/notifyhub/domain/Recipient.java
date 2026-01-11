package com.notifyhub.domain;

public class Recipient {

    private String address;
    private String name;

    public Recipient(String address) {
        this.address = address;
    }

    public Recipient(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public Recipient() {
    }

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return name;
    }
}
