package com.ncspt.modular.main.model;

/**
 * ListView填充数据实体
 */

public class Item {

    public int itemImageResId;
    public String itemTitle;

    public Item(int itemImageResId, String itemTitle) {
        this.itemImageResId = itemImageResId;
        this.itemTitle = itemTitle;
    }
}
