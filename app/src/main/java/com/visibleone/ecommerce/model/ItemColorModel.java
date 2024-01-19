package com.visibleone.ecommerce.model;

public class ItemColorModel {
    private boolean status;
    private String itemColor;

    public ItemColorModel(boolean status, String itemColor) {
        this.status = status;
        this.itemColor = itemColor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }
}
