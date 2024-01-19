package com.visibleone.ecommerce.model;

public class ItemSizeModel {
    private boolean status;
    private String itemSize;

    public ItemSizeModel(boolean status, String itemSize) {
        this.status = status;
        this.itemSize = itemSize;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }
}
