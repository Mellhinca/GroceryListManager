package edu.qc.seclass.glm.models;

public class ItemList {

    int itemId;
    String name;
    float price;
    int groceryID;
    Boolean checked;
    int itemtpyeID;
    int quantity;

    public int getItemtpyeID() {
        return itemtpyeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemList(int itemId, String name, float price, int groceryID, Boolean checked, int itemtpyeID) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.groceryID = groceryID;
        this.checked = checked;
        this.itemtpyeID = itemtpyeID;
    }

    public ItemList(int itemId, String name, float price, int groceryID, int itemtpyeID, int quantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.groceryID = groceryID;
        this.itemtpyeID = itemtpyeID;
        this.quantity = quantity;
        this.checked = false;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGroceryID() {
        return groceryID;
    }

    public void setGroceryID(int groceryID) {
        this.groceryID = groceryID;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
