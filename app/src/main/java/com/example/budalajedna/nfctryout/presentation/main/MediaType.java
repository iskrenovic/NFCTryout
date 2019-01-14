package com.example.budalajedna.nfctryout.presentation.main;

public enum MediaType {

    facebook (5),
    skype(2),
    instagram (6),
    phoneNumber (0),
    email(1),
    twitter(4),
    whatsapp(3);

    final int value;

    MediaType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
