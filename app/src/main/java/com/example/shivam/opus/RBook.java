package com.example.shivam.opus;

public class RBook {
    public RBook() {
    }
    String name;
    String inv;
    String var;
    public RBook(String name, String inv, String var) {
        this.name = name;
        this.inv = inv;
        this.var = var;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }
    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
