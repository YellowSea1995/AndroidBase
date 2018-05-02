package com.example.huanghai91632.androidbase.model;

/**
 * ListView填充数据实体
 */

public class Item {

    public int itemImageResId;
    public String itemTitle;
    public String itemContent;
    public Boolean itemChecked;

    public Item(int itemImageResId, String itemTitle, String itemContent, boolean itemChecked) {
        this.itemImageResId = itemImageResId;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
        this.itemChecked = itemChecked;
    }

    public Boolean getItemChecked() {
        return itemChecked;
    }

    public void setItemChecked(Boolean itemChecked) {
        this.itemChecked = itemChecked;
    }
}
