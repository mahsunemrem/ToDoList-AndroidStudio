package com.example.todolistapp.Adapter;

import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.Activity.TagActivity;
import com.example.todolistapp.Activity.ToDoActivity;
import com.example.todolistapp.Business.TagManager;
import com.example.todolistapp.Entities.Tag;
import com.example.todolistapp.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagDesignHandler> {
    private Context mContext;
    private ArrayList<Tag> Tags;
    private TagManager tagManager;




    public TagAdapter(Context mContext, ArrayList<Tag> tags) {
        this.mContext = mContext;
        this.Tags = tags;
        tagManager=new TagManager( mContext );
    }

    @NonNull
    @Override
    public TagDesignHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tag,parent,false);

        return new TagAdapter.TagDesignHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TagDesignHandler holder, int position) {

        final Tag tag = Tags.get(position);

        holder.tagName.setText(tag.getTagName());
        holder.tagCard.setTag( tag.getId() );
        //holder.tagCard.setTag(0,tag.getTagName() );

        holder.tagCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, ToDoActivity.class);
                intent.putExtra("id", tag.getId());
                intent.putExtra("tagName", tag.getTagName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);

            }
        });



        holder.tagCard.setOnLongClickListener( new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onLongClick(final View v) {
                PopupMenu popupMenu=new PopupMenu( mContext ,holder.tagCard);
                popupMenu.setGravity(Gravity.END);

                popupMenu.getMenuInflater().inflate( R.menu.menu_popup_edittag,popupMenu.getMenu() );
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.action_deleteTag:{


                                Snackbar.make( v,"Silisin mi ?",Snackbar.LENGTH_LONG ).setAction( "Evet", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View t) {
                                        int id =(Integer) v.getTag();
                                        tagManager.Delete(id );
                                        Tags=(ArrayList<Tag>) tagManager.GetList();
                                        notifyDataSetChanged();
                                    }
                                } ).show();

                                break;
                            }
                            case R.id.action_editTag:{
                                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder( mContext);
                                materialAlertDialogBuilder.setTitle("Etiket Başlığını Düzenle");

                                materialAlertDialogBuilder.setBackground( mContext.getResources().getDrawable(R.drawable.alertdialog_bg,null));

                                materialAlertDialogBuilder.setIcon(R.drawable.pencil);

                                LayoutInflater inflater = LayoutInflater.from(mContext);
                                final View view = inflater.inflate(R.layout.alertview_addtag,null);
                                EditText tagName=view.findViewById(R.id.editText_tagName );
                                Tag temp = tagManager.Get((Integer) v.getTag() );
                                tagName.setText(  temp.getTagName().toString() );
                                materialAlertDialogBuilder.setView(view);



                                materialAlertDialogBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                materialAlertDialogBuilder.setPositiveButton("Değiştir", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        EditText tagName=view.findViewById(R.id.editText_tagName );
                                        if (tagName.getText().toString()!=""){

                                            Tag temp = tagManager.Get((Integer) v.getTag() );
                                            temp.setTagName(  tagName.getText().toString());
                                            tagManager.Update(temp);

                                        }
                                        ArrayList<Tag> tags=(ArrayList<Tag>) tagManager.GetList();

                                        Tags=(ArrayList<Tag>) tagManager.GetList();
                                        notifyDataSetChanged();
                                    }
                                });
                                materialAlertDialogBuilder.show();

                                break;
                            }
                        }


                        return true;
                    }
                } );

                return true;
            }
        } );


/*
        holder.tagCard.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:{
                        Toast.makeText( mContext, "click", Toast.LENGTH_SHORT ).show();
                        CardView card =(CardView) v;
                        card.getBackground().clearColorFilter();
                        card.invalidate();
                        Intent intent=new Intent(mContext, ToDoActivity.class);
                        intent.putExtra("id", tag.getId());
                        mContext.startActivity(intent);



                    }
                    case MotionEvent.ACTION_DOWN:{
                        Toast.makeText( mContext, "basılıtutma", Toast.LENGTH_SHORT ).show();
                        //basılı tutma;
                        CardView card =(CardView) v;
                        card.getBackground().setColorFilter( 0x77000000 , PorterDuff.Mode.SRC_ATOP );

                        card.invalidate();

                        PopupMenu popupMenu=new PopupMenu( mContext ,card);
                        popupMenu.getMenuInflater().inflate( R.menu.menu_popup_edittag,popupMenu.getMenu() );
                        popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()){
                                    case R.id.action_deleteTag:{
                                        Toast.makeText( mContext, "delete", Toast.LENGTH_SHORT ).show();
                                    }
                                    case R.id.action_editTag:{
                                        Toast.makeText( mContext, "edit", Toast.LENGTH_SHORT ).show();
                                    }
                                }


                                return true;
                            }
                        } );


                        return  true;
                    }




                    case MotionEvent.ACTION_CANCEL:{
                        Toast.makeText( mContext, "basılı tut çek", Toast.LENGTH_SHORT ).show();
                        CardView card =(CardView) v;
                        card.getBackground().clearColorFilter();
                        card.invalidate();
                        return  true;
                    }
                }

                return true;
            }
        } );
*/



    }

    @Override
    public int getItemCount() {
        return Tags.size();
    }


    public class TagDesignHandler extends RecyclerView.ViewHolder{


        private TextView tagName;
        private CardView tagCard;

        public TagDesignHandler(@NonNull View itemView) {
            super(itemView);
            tagCard=itemView.findViewById(R.id.cardView_tag);
            tagName=itemView.findViewById(R.id.textView_tagName);



        }
    }
}

