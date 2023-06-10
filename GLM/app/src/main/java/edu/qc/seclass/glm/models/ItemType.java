package edu.qc.seclass.glm.models;

import java.io.Serializable;

public class ItemType implements Serializable {
    int typeID;
    String name;
    String description;

    public ItemType(int typeID, String name, String description) {
        this.typeID = typeID;
        this.name = name;
        this.description = description;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
