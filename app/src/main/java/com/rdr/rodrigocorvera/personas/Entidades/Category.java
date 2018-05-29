package com.rdr.rodrigocorvera.personas.Entidades;

/**
 * Created by Rodrigo Corvera on 27/5/2018.
 */

public class Category {

    private String categoryName;
    private String categoryId;

    public Category (String categoryName, String categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
