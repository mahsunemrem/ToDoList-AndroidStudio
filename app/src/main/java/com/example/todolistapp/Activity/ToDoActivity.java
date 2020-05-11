package com.example.todolistapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.todolistapp.Adapter.TagAdapter;
import com.example.todolistapp.Adapter.ToDoAdapter;
import com.example.todolistapp.Entities.Tag;
import com.example.todolistapp.Entities.ToDo;
import com.example.todolistapp.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity {

    private Toolbar toolbarTodo;
    private RecyclerView rvTodo;
    private ArrayList<ToDo> todos ;
    private ToDoAdapter todoAdapter;
    private FloatingActionButton floatBottomAddTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);


        todos = new ArrayList<>();

        ToDo t1 = new ToDo(0,"#Alışveriş",true,0);
        ToDo t2 = new ToDo(1,"#test",false,0);


        todos.add(t1);
        todos.add(t2);


        rvTodo=findViewById(R.id.rv_todo);


        todoAdapter=new ToDoAdapter(this,todos);

        rvTodo.setLayoutManager(new LinearLayoutManager(this));

        rvTodo.setAdapter(todoAdapter);



        toolbarTodo=findViewById(R.id.toolbar_todo);
        toolbarTodo.setTitle("TO DO LİST");
        toolbarTodo.setLogo(R.drawable.note);

        setSupportActionBar(toolbarTodo);


        floatBottomAddTodo=findViewById(R.id.floatingActionButton_addTodo);

        floatBottomAddTodo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(ToDoActivity.this);
                materialAlertDialogBuilder.setTitle("Etiket Başlığı");

                materialAlertDialogBuilder.setBackground(getResources().getDrawable(R.drawable.alertdialog_bg,null));

                materialAlertDialogBuilder.setIcon(R.drawable.pencil);

                materialAlertDialogBuilder.setView(R.layout.alertview_addtag);
                materialAlertDialogBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                materialAlertDialogBuilder.setPositiveButton("Oluştur", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });



                materialAlertDialogBuilder.show();


            }
        });
    }
}
