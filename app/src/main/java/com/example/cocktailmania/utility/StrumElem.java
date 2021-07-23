package com.example.cocktailmania.utility;

public class StrumElem {
    String name;

    public StrumElem() {

    }

    public StrumElem(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                '}';
    }
}
