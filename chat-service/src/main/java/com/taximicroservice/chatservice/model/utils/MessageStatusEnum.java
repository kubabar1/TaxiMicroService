package com.taximicroservice.chatservice.model.utils;

public enum MessageStatusEnum {
    SENT("SENT"),
    READ("READ");

    private String name;

    MessageStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
