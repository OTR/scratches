package com.github.otr.domain.entity;

/*

 */
public enum FeedCategory {
    ECONOMY("Экономика");

    private final String categoryName;

    FeedCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
