package com.taximicroservice.chatservice.model.utils;

public enum BookingStatusEnum {
    CREATED("CREATED"),
    ASSIGNED("ASSIGNED"),
    IN_PROGRESS("IN_PROGRESS"),
    CANCELED("CANCELED"),
    FINISHED("FINISHED"),
    ABORTED("ABORTED");

    private String name;

    BookingStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
