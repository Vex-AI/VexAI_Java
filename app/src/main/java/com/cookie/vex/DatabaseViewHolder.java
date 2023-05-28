package com.cookie.vex;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DatabaseViewHolder extends RecyclerView.ViewHolder {

  TextView text_user, text_vex;
  LinearLayout linear_user, linear_vex, base;
  ImageView delete;

  public DatabaseViewHolder(View item) {
    super(item);

    text_vex = item.findViewById(R.id.text_vex);
    text_user = item.findViewById(R.id.text_user);
    ;
    linear_user = item.findViewById(R.id.linear_user);
    linear_vex = item.findViewById(R.id.linear_vex);
    base = item.findViewById(R.id.base);
    delete = item.findViewById(R.id.delete);
  }

}
