package com.example.budalajedna.nfctryout.presentation.main;

public enum MediaType {

    facebook (2),
    instagram (3),
    phoneNumber (0),
    email(1),
    twitter(5),
    whatsapp(4);

    final int value;

    MediaType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
