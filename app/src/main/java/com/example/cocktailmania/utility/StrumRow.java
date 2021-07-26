package com.example.cocktailmania.utility;

public class StrumRow {
    int id;
    String name;

    public StrumRow() {
    }

    public StrumRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StrumElem{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
