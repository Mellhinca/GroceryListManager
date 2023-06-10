package edu.qc.seclass.glm.models;

public class GroceryList {
    int groceryId;
    String name;

    public GroceryList(int groceryId, String name) {
        this.groceryId = groceryId;
        this.name = name;
    }

    public int getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(int groceryId) {
        this.groceryId = groceryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
