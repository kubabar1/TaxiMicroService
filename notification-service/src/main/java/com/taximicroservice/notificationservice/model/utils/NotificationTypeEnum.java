package com.taximicroservice.notificationservice.model.utils;

public enum NotificationTypeEnum {
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR");

    private String name;

    NotificationTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
