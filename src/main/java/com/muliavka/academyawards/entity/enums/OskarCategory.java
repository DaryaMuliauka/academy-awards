package com.muliavka.academyawards.entity.enums;

/**
 * enum with categories of oskar
 */
public enum OskarCategory {
    BEST_PICTURE("Best Picture");

    OskarCategory(String name) {
        this.name = name;
    }

    public final String name;
}
