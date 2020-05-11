package com.example.todolistapp.Entities;

import com.example.todolistapp.Activity.TagActivity;

public class ToDo {

    private int Id;
    private String TodoName;
    private boolean IsDone;
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
