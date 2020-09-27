package com.taximicroservice.notificationservice.model.utils;

public enum NotificationStatusEnum {
    SENT("SENT"),
    READ("READ"),
    DELETED("DELETED");

    private String name;

    NotificationStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
