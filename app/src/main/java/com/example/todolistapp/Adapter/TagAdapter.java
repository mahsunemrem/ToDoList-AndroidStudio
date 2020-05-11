package com.example.todolistapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.Activity.ToDoActivity;
import com.example.todolistapp.Entities.Tag;
import com.example.todolistapp.R;

import java.io.Serializable;
import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagDesignHandler> {
    private Context mContext;
    private ArrayList<Tag> Tags;

    public TagAdapter(Context mContext, ArrayList<Tag> tags) {
        this.mContext = mContext;
        this.Tags = tags;
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

        holder.tagCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent=new Intent(mContext, ToDoActivity.class);
                //intent.putExtra("nesne", (Serializable) tag);
                mContext.startActivity(intent);


            }
        });
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

