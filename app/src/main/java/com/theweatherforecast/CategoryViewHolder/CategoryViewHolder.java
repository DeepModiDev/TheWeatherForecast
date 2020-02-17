package com.theweatherforecast.CategoryViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.theweatherforecast.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView finalMaxTempTextView;
    public TextView finalMinTempTextView;
    public TextView finalTempTime;
    public TextView city;
    public TextView condition;
    public ConstraintLayout constraintLayout;
    public ImageView img;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        finalMaxTempTextView = (TextView)itemView.findViewById(R.id.text_max_temp);
        finalMinTempTextView = (TextView)itemView.findViewById(R.id.text_min_layout_temp);
        constraintLayout = (ConstraintLayout)itemView.findViewById(R.id.constraint_layout);
        finalTempTime = (TextView)itemView.findViewById(R.id.Day_text);
        city = (TextView)itemView.findViewById(R.id.city);
        condition = (TextView)itemView.findViewById(R.id.condition);
        img = itemView.findViewById(R.id.ic_icon_condition);
    }
}
