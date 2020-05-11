package com.example.todolistapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todolistapp.Adapter.TagAdapter;
import com.example.todolistapp.Entities.Tag;
import com.example.todolistapp.Entities.ToDo;
import com.example.todolistapp.MainActivity;
import com.example.todolistapp.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TagActivity extends AppCompatActivity {

    private Toolbar toolbarTag;
    private RecyclerView rvTag;
    private ArrayList<Tag> tags ;
    private TagAdapter tagAdapter;
    private FloatingActionButton floatBottomAddTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);


         tags = new ArrayList<>();

         Tag t1 = new Tag(0,"#Alışveriş");
         Tag t2 = new Tag(1,"#Proje");
         Tag t3 = new Tag(2,"#İş");

         tags.add(t1);
         tags.add(t2);
         tags.add(t3);

        rvTag=findViewById(R.id.rv_tag);


        tagAdapter=new TagAdapter(this,tags);

        rvTag.setLayoutManager(new LinearLayoutManager(this));

        rvTag.setAdapter(tagAdapter);



        toolbarTag=findViewById(R.id.toolbar_tag);
        toolbarTag.setTitle("TO DO LİST");
        toolbarTag.setLogo(R.drawable.note);

        setSupportActionBar(toolbarTag);




        floatBottomAddTag=findViewById(R.id.floatingActionButton_addTag);

        floatBottomAddTag.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(TagActivity.this);
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
