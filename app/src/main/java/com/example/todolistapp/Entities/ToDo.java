package com.example.todolistapp.Entities;

import com.example.todolistapp.Activity.TagActivity;
import com.example.todolistapp.Core.PersistenceManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ToDos")
public class ToDo implements PersistenceManager.Modal {
    @DatabaseField(generatedId = true ,allowGeneratedIdInsert=true )
    private int Id;
    @DatabaseField
    private String TodoName;
    @DatabaseField
    private boolean IsDone;
    @DatabaseField
    private int TagId;


    public ToDo() {
    }

    public ToDo(int id, String todoName, boolean isDone, int tagId) {
        Id = id;
        TodoName = todoName;
        IsDone = isDone;
        TagId = tagId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTodoName() {
        return TodoName;
    }

    public void setTodoName(String todoName) {
        TodoName = todoName;
    }

    public boolean isDone() {
        return IsDone;
    }

    public void setDone(boolean done) {
        IsDone = done;
    }

    public int getTagId() {
        return TagId;
    }

    public void setTagId(int tagId) {
        TagId = tagId;
    }
}
