package edu.qc.seclass.glm.models;

import java.io.Serializable;

public class ItemTypes implements Serializable {
    private int id;
    private String typeName;


    public ItemTypes(String typeName) {
        this.typeName = typeName;
    }

    public ItemTypes(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() { return id; }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return getTypeName();
    }

}
