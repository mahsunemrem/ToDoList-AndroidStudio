package com.example.todolistapp.Business;

import android.content.Context;

import com.example.todolistapp.DataAccess.TagDal;
import com.example.todolistapp.DataAccess.ToDoDal;
import com.example.todolistapp.Entities.Tag;
import com.example.todolistapp.Entities.ToDo;

import java.util.List;

public class TagManager {

    TagDal tagDal;

    public TagManager(Context context) {
        tagDal = new TagDal(context);
    }

    public void Add(Tag product){
        tagDal.create(product);
    }

    public void Delete(int id){
        tagDal.delete(id);
    }
    public void Update(Tag product){
        tagDal.update(product);
    }
    public Tag Get( int id){
        return tagDal.read(id);
    }
    public List<Tag> GetList( ){
        return  tagDal.readAll();
    }

}
