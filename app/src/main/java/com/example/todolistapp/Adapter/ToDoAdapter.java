package com.example.todolistapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.Activity.ToDoActivity;
import com.example.todolistapp.Business.ToDoManager;
import com.example.todolistapp.Entities.Tag;
import com.example.todolistapp.Entities.ToDo;
import com.example.todolistapp.R;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoDesignHandler> {
    private Context mContext;
    private ArrayList<ToDo> ToDos;


    ToDoManager toDoManager;
    public ToDoAdapter(Context mContext, ArrayList<ToDo> toDos) {
        this.mContext = mContext;
        this.ToDos = toDos;

        toDoManager=new ToDoManager(  mContext);
    }

    @NonNull
    @Override
    public ToDoAdapter.ToDoDesignHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_todo,parent,false);

        return new ToDoAdapter.ToDoDesignHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ToDoAdapter.ToDoDesignHandler holder, int position) {

         final ToDo toDo = ToDos.get(position);

        holder.todoName.setText(toDo.getTodoName());

        holder.isDone.setChecked(toDo.isDone());


        holder.isDone.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    toDo.setDone( isChecked );
                    toDoManager.Update( toDo );

                ToDos=(ArrayList<ToDo>) toDoManager.getToDosWithTagId( toDo.getTagId() );
                notifyDataSetChanged();
            }
        } );


    }

    @Override
    public int getItemCount() {
        return ToDos.size();
    }


    public class ToDoDesignHandler extends RecyclerView.ViewHolder{


        private TextView todoName;
        private CardView todoCard;
        private CheckBox isDone;

        public ToDoDesignHandler(@NonNull View itemView) {
            super(itemView);
            todoCard=itemView.findViewById(R.id.cardView_todo);
            todoName=itemView.findViewById(R.id.textView_todo);
            isDone=itemView.findViewById(R.id.checkBox_isDone);



        }
    }
}
