package com.agh.cinehub_backend.model;

public enum Statuses {
    PENDING("Pending"),
    PAYED("Payed"),
    CANCELLED("Cancelled");

    private final String name;

    Statuses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
