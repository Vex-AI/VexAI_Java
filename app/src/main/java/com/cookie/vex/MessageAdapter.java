package com.cookie.vex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

  private ArrayList<MessageListModel> items;
  private Context context;
  private RippleDrawable myuser;
  private RippleDrawable other_user;
  private SharedPreferences sd;

  public MessageAdapter(Context context, ArrayList<MessageListModel> data) {
    this.items = data;
    this.context = context;
    this.sd = context.getSharedPreferences("sr", context.MODE_PRIVATE);
  }

  @Override
  public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


    View view = LayoutInflater.from(context).inflate(R.layout.chat, parent, false);

    MessageViewHolder viewHolder = new MessageViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final MessageViewHolder holder, final int pos) {

    holder.base.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      }
    });

    final MessageListModel list_pos = this.items.get(pos);

    if(list_pos.getKey().equals("vex")) {

      holder.linear_user.setVisibility(View.GONE);
      holder.linear_vex.setVisibility(View.VISIBLE);
      holder.text_vex.setText(list_pos.getMessage());
      holder.hour_vex.setText(list_pos.getHour());

      if(sd.getInt("other_text_color", -1) != -1) {
        holder.text_vex.setTextColor(sd.getInt("other_text_color", -1));
        holder.hour_user.setTextColor(sd.getInt("other_text_color", -1));

      }

      CreateShapeVex();
      if(sd.getInt("other_color", -1) != -1) {
        holder.linear_vex.setBackground(other_user);
      }
    } else {
      holder.linear_vex.setVisibility(View.GONE);
      holder.linear_user.setVisibility(View.VISIBLE);
      holder.text_user.setText(list_pos.getMessage());
      holder.hour_user.setText(list_pos.getHour());

      if(sd.getInt("user_text_color", -1) != -1) {
        holder.text_user.setTextColor(sd.getInt("user_text_color", -1));
        holder.hour_user.setTextColor(sd.getInt("user_text_color", -1));

      }
      CreateShapeUser();
      if(sd.getInt("user_color", -1) != -1) {
        holder.linear_user.setBackground(myuser);
      }
    }


  }

  @Override
  public int getItemCount() {
    return this.items.size();
  }

  public void update(ArrayList<MessageListModel> data) {
    this.items = data;
    notifyDataSetChanged();
  }

  public void updateItem(int position) {
    notifyItemChanged(position);
  }

  public void removeItem(int pos) {
    this.items.remove(pos);
    notifyItemRemoved(pos);
  }

  public void CreateShapeUser() {
    if(sd.getInt("user_color", -1) != -1) {
      GradientDrawable temp_myuser = new GradientDrawable();
      temp_myuser.setShape(GradientDrawable.RECTANGLE);
      int top1 = sd.getInt("top1_user", 15);
      int top2 = sd.getInt("top2_user", 15);
      int top3 = sd.getInt("top3_user", 15);
      int top4 = sd.getInt("top4_user", 15);
      temp_myuser.setCornerRadii(new float[]{top1, top1, top2, top2, top3, top3, top4, top4});
      temp_myuser.setColor(sd.getInt("user_color", -1));
      if(sd.getInt("user_stroke_size", 0) != 0) {
        temp_myuser.setStroke(sd.getInt("user_stroke_size", -1), sd.getInt("user_stroke_color", -1));
      }
      ColorStateList csl = new ColorStateList(new int[][]{new int[]{}}, new int[]{sd.getInt("user_ripple_color", 0)});
      myuser = new RippleDrawable(csl, temp_myuser, null);
    }
  }

  public void CreateShapeVex() {
    if(sd.getInt("other_color", -1) != -1) {
      GradientDrawable temp_other = new GradientDrawable();
      temp_other.setShape(GradientDrawable.RECTANGLE);
      int top1_other = sd.getInt("top1_other", 15);
      int top2_other = sd.getInt("top2_other", 15);
      int top3_other = sd.getInt("top3_other", 15);
      int top4_other = sd.getInt("top4_other", 15);
      temp_other.setCornerRadii(new float[]{top1_other, top1_other, top2_other, top2_other, top3_other, top3_other, top4_other, top4_other});
      temp_other.setColor(sd.getInt("other_color", -1));
      if(sd.getInt("other_stroke_size", 0) != 0) {
        temp_other.setStroke(sd.getInt("other_stroke_size", -1), sd.getInt("other_stroke_color", -1));
      }
      ColorStateList csl2 = new ColorStateList(new int[][]{new int[]{}}, new int[]{sd.getInt("other_ripple_color", 0)});
      other_user = new RippleDrawable(csl2, temp_other, null);
    }
  }

}