package com.example.todolistapp.Entities;

import java.util.List;


public class Tag {

    private int Id;
    private String TagName;


    public Tag() {
    }

    public Tag(int id, String tagName) {
        Id = id;
        TagName = tagName;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }
}
