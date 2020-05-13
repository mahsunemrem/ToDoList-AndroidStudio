package com.example.todolistapp.DataAccess;

import android.content.Context;

import com.example.todolistapp.Core.PersistenceManager;
import com.example.todolistapp.Entities.ToDo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoDal  extends PersistenceManager<ToDo> {


    public ToDoDal(Context context) {
        super(context, ToDo.class);
    }


    public List<ToDo> getProductsWithName(String name) {

        try {
            return dao.query(dao.queryBuilder().where().eq("name", name).prepare());

        } catch (SQLException e) {

            e.printStackTrace();
            return new ArrayList<ToDo>();
        }
    }

    public List<ToDo> getToDosWithTagId(int id) {

        try {
            return dao.query(dao.queryBuilder().where().eq("TagId", id).prepare());

        } catch (SQLException e) {

            e.printStackTrace();
            return new ArrayList<ToDo>();
        }
    }
}