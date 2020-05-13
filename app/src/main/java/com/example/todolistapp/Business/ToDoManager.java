package com.example.todolistapp.Business;

import android.content.Context;

import com.example.todolistapp.DataAccess.ToDoDal;
import com.example.todolistapp.Entities.ToDo;

import java.util.List;

public class ToDoManager {

    ToDoDal toDoDal;

    public ToDoManager(Context context) {
        toDoDal = new ToDoDal(context);
    }

    public void Add(ToDo product){
        toDoDal.create(product);
    }

    public void Delete(int id){
        toDoDal.delete(id);
    }
    public void Update(ToDo product){
        toDoDal.update(product);
    }
    public ToDo Get( int id){
        return toDoDal.read(id);
    }
    public List<ToDo> GetList( ){
        return  toDoDal.readAll();
    }
    public List<ToDo> getToDosWithTagId(int id) {
        return  toDoDal.getToDosWithTagId(id);
    }

}
