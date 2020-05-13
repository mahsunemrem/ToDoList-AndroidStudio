package com.example.todolistapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.todolistapp.Adapter.TagAdapter;
import com.example.todolistapp.Adapter.ToDoAdapter;
import com.example.todolistapp.Business.ToDoManager;
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
    private int TagId;
    private ToDoManager toDoManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);


        toDoManager=new ToDoManager( this );
        todos = new ArrayList<>();

         TagId=getIntent().getIntExtra( "id",0 );
        if(toDoManager.getToDosWithTagId( TagId )!=null){
            todos =(ArrayList<ToDo>) toDoManager.getToDosWithTagId( TagId );
        }


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

                LayoutInflater inflater = ToDoActivity.this.getLayoutInflater();
                final View view = inflater.inflate(R.layout.alertview_addtodo,null);
                materialAlertDialogBuilder.setView(view);
                materialAlertDialogBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                materialAlertDialogBuilder.setPositiveButton("Oluştur", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText todoName=view.findViewById(R.id.editText_todoName );
                        if (todoName.getText().toString()!=""){
                            toDoManager.Add( new ToDo( 0,todoName.getText().toString() ,false,TagId));

                        }
                        todos=(ArrayList<ToDo>) toDoManager.GetList();
                        todoAdapter = new ToDoAdapter( getApplicationContext(),todos );
                        todoAdapter.notifyDataSetChanged();
                        rvTodo.setAdapter(todoAdapter);
                    }
                });



                materialAlertDialogBuilder.show();


            }
        });
    }
}
