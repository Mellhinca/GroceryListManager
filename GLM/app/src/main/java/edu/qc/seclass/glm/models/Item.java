package edu.qc.seclass.glm.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Item implements Serializable{
    private int id = 0;
    private String itemName;
    private ItemTypes itemType;

    public Item(String itemName, ItemTypes itemType) {
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public Item(int id, String itemName, ItemTypes itemType) {
        this.id = id;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {

        this.itemName = itemName;
    }
    public ItemTypes getItemType() {

        return itemType;
    }
    public void setItemType(ItemTypes itemType)
    {
        this.itemType = itemType;
    }

}
