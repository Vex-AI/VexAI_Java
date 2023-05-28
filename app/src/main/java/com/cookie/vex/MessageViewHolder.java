package com.cookie.vex;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {

  TextView text_user, text_vex, hour_user, hour_vex;
  LinearLayout linear_user, linear_vex, base;

  public MessageViewHolder(View item) {
    super(item);

    text_vex = item.findViewById(R.id.text_vex);
    text_user = item.findViewById(R.id.text_user);
    hour_user = item.findViewById(R.id.hour_user);
    hour_vex = item.findViewById(R.id.hour_vex);
    linear_user = item.findViewById(R.id.linear_user);
    linear_vex = item.findViewById(R.id.linear_vex);
    base = item.findViewById(R.id.base);
  }

}
