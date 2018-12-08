package com.example.budalajedna.nfctryout.presentation;

public enum MediaType {

    facebook (0),
    instagram (1),
    phoneNumber (2),
    email(3);

    final int value;

    MediaType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
