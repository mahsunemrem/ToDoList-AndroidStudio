package com.example.todolistapp.DataAccess;

import android.content.Context;

import com.example.todolistapp.Core.PersistenceManager;
import com.example.todolistapp.Entities.Tag;
import com.example.todolistapp.Entities.ToDo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDal extends PersistenceManager<Tag> {


    public TagDal(Context context) {
        super(context, Tag.class);
    }


    public List<Tag> getProductsWithName(String name) {

        try {
            return dao.query(dao.queryBuilder().where().eq("name", name).prepare());

        } catch (SQLException e) {

            e.printStackTrace();
            return new ArrayList<Tag>();
        }
    }

}