package com.agh.cinehub_backend.model;

public enum Discounts {
    STANDARD("Standard"),
    STUDENT("Student"),
    CHILD("Child"),
    SENIOR("Senior");

    private final String name;

    Discounts(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
