package com.example.todolistapp.Entities;

import com.example.todolistapp.Core.PersistenceManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "Tags")
public class Tag implements PersistenceManager.Modal {
    @DatabaseField(generatedId = true ,allowGeneratedIdInsert=true )
    private int Id;
    @DatabaseField
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
