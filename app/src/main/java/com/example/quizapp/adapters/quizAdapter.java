package com.example.quizapp.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.quiz;
import com.example.quizapp.utils.colorPicker;
import com.example.quizapp.utils.iconPicker;

import java.util.ArrayList;

public class quizAdapter extends RecyclerView.Adapter<quizAdapter.viewHolder> {

    ArrayList<quiz> quizzes;

    public quizAdapter(ArrayList<quiz> quizzes) {
        this.quizzes = quizzes;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.textViewTitle.setText(quizzes.get(position).title);
        colorPicker colorP = new colorPicker();
        iconPicker iconP = new iconPicker();
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(colorP.getColor()));
        holder.iconView.setImageResource(iconP.getIcon());
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }


    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView iconView;
        CardView cardContainer;

        public viewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.quizTitle);
            iconView = itemView.findViewById(R.id.quizIcon);
            cardContainer = itemView.findViewById(R.id.cardContainer);


        }

    }


}
