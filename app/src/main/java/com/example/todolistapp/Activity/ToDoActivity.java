package com.example.todolistapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.todolistapp.Adapter.TagAdapter;
import com.example.todolistapp.Adapter.ToDoAdapter;
import com.example.todolistapp.Business.ToDoManager;
import com.example.todolistapp.Entities.Tag;
import com.example.todolistapp.Entities.ToDo;
import com.example.todolistapp.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ToDoActivity extends AppCompatActivity {

    private Toolbar toolbarTodo;
    private RecyclerView rvTodo;
    private ArrayList<ToDo> todos ;
    private ToDoAdapter todoAdapter;
    private FloatingActionButton floatBottomAddTodo;
    private int TagId;
    private String TagName;
    private ToDoManager toDoManager;
    private TextView textViewTagName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);






        toDoManager=new ToDoManager( this );
        todos = new ArrayList<>();

         TagId=getIntent().getIntExtra( "id",0 );
         TagName=getIntent().getStringExtra( "tagName" );
        textViewTagName=findViewById( R.id.textView_tagNameTodo );
        textViewTagName.setText( TagName.toString() );

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
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(ToDoActivity.this);
                materialAlertDialogBuilder.setTitle("Aktivite");

                materialAlertDialogBuilder.setBackground(getResources().getDrawable(R.drawable.alertdialog_bg,null));

                materialAlertDialogBuilder.setIcon(R.drawable.pencil);

                LayoutInflater inflater = ToDoActivity.this.getLayoutInflater();
                final View view = inflater.inflate(R.layout.alertview_addtodo,null);


                final TextView textView_date=view.findViewById(R.id.textView_date);

                final TimePicker picker=view.findViewById(R.id.spinner_date);
                picker.setIs24HourView(true);

                final Calendar c;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    picker.setHour(mHour);
                    picker.setMinute(mMinute);
                    Date date = c.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy ");
                    String formattedDate = format.format(date);
//may 23,2020
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DATE);
                    int year = c.get(Calendar.YEAR);
                    //textView_date.setText(" "+month +" "+day+" "+ year);
                    textView_date.setText(new DateFormatSymbols().getMonths()[month]+" "+day+","+year);
                }






                MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText( "Tarihi seçin" );

                final MaterialDatePicker materialDatePicker =builder.build();

                materialDatePicker.addOnPositiveButtonClickListener( new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        textView_date.setText( materialDatePicker.getHeaderText() +"");

                    }
                } );

                textView_date.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        materialDatePicker.show( getSupportFragmentManager(),"DATE_PICKER" );
                    }
                } );
                materialAlertDialogBuilder.setView(view);
                EditText tagName=view.findViewById(R.id.editText_todoName );
                tagName.setHint( "Aktivite Adı" );
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
                        todos=(ArrayList<ToDo>) toDoManager.getToDosWithTagId( TagId );
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
