package com.example.shivam.opus;

public class Book {
    public Book() {
    }
    String name;
    String cid;
    public Book(String name, String cid) {
        this.name = name;
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

}
