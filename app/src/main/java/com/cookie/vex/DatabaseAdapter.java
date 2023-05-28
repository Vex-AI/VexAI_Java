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

import org.richit.easiestsqllib.Column;
import org.richit.easiestsqllib.EasiestDB;

import java.util.ArrayList;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseViewHolder> {

  private ArrayList<DatabaseModel> items;
  private Context context;
  private RippleDrawable myuser;
  private RippleDrawable other_user;
  private SharedPreferences sd;
  private EasiestDB db;

  public DatabaseAdapter(Context context, ArrayList<DatabaseModel> data) {
    this.items = data;
    this.context = context;
    this.sd = context.getSharedPreferences("sr", context.MODE_PRIVATE);
  }

  @Override
  public DatabaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    this.db = EasiestDB.init(this.context).addTableColumns("DataBase", new Column("pgs", "text"), new Column("rps", "text")).doneAddingTables();

    View view = LayoutInflater.from(context).inflate(R.layout.database, parent, false);

    DatabaseViewHolder viewHolder = new DatabaseViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final DatabaseViewHolder holder, final int pos) {

    final DatabaseModel list_pos = this.items.get(pos);

    holder.base.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      }
    });

    holder.text_vex.setText(list_pos.getAnswer());

    if(sd.getInt("other_text_color", -1) != -1) {
      holder.text_vex.setTextColor(sd.getInt("other_text_color", -1));
    }

    CreateShapeVex();
    if(sd.getInt("other_color", -1) != -1) {
      holder.linear_vex.setBackground(other_user);
    }


    holder.text_user.setText(list_pos.getMessage());

    if(sd.getInt("user_text_color", -1) != -1) {
      holder.text_user.setTextColor(sd.getInt("user_text_color", -1));

    }
    CreateShapeUser();
    if(sd.getInt("user_color", -1) != -1) {
      holder.linear_user.setBackground(myuser);
    }

    holder.delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        boolean del = db.deleteRow(0, Integer.parseInt(list_pos.getID()));

        if(del) {
          removeItem(pos);
        }
      }
    });


  }

  @Override
  public int getItemCount() {
    return this.items.size();
  }

  public void update(ArrayList<DatabaseModel> data) {
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