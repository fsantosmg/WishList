package net.felipesantos.wishlist.common.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    SYSTEM_ERROR("/system-error", "System error"),
    INVALID_MESSAGE("/invalid-message", "Invalid message"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    ENTITY_ALREADY_REGISTERED("/entity-already-registered", "Entity already registered"),
    WISHLIST_REACHED_LIMIT("/wis-list-reached-limit", "The customer's wish list has reached its limit");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://whislist.internal" + path;
        this.title = title;
    }
}

