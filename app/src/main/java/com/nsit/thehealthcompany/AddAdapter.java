package com.nsit.thehealthcompany;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nsit.thehealthcompany.DTO.Item;

import java.util.ArrayList;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.AddViewHolder> {

    private ArrayList<Item> items;
    private Context context;

    AddAdapter(ArrayList<Item> items, Context context) {
        this.items=items;
        this.context=context;
    }

    @NonNull
    @Override
    public AddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.plans_card_design,parent,false);
        return new AddViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddViewHolder holder, int position) {
        Item i=items.get(position);
        holder.first_image.setImageResource(i.getFirst_image());
        holder.first_text.setText(i.getFirst_text());
        holder.second_text.setText(i.getSecond_text());

        switch (position){
            case 2 : holder.addImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MealNurtitionDescription.class);
                    context.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class AddViewHolder extends RecyclerView.ViewHolder {
        private ImageView first_image;
        private TextView first_text;
        private TextView second_text;
        private ImageView addImageIcon;

        AddViewHolder(View itemView) {
            super(itemView);
            first_image=itemView.findViewById(R.id.first_image);
            first_text=itemView.findViewById(R.id.first_text);
            second_text=itemView.findViewById(R.id.second_text);
            addImageIcon = itemView.findViewById(R.id.second_image);
        }
    }
}
