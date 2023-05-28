package com.cookie.vex;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;

import org.richit.easiestsqllib.Column;
import org.richit.easiestsqllib.EasiestDB;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class BancoActivity extends AppCompatActivity {

  private ArrayList<DatabaseModel> list_search = new ArrayList<DatabaseModel>();
  private ArrayList<DatabaseModel> items = new ArrayList<DatabaseModel>();
  private DatabaseAdapter data_adapter;
  private File bg;
  private EasiestDB dataBase;
  private LinearLayoutManager data_layout;
  
  private ArrayList<HashMap<String, Object>> itens = new ArrayList<>();
  private ArrayList<String> data = new ArrayList<>();

  private FrameLayout frame;
  private ImageView image_background;
  private LinearLayout content;
  private AutoCompleteTextView search;
  private TextView value;
  private RecyclerView datalist;
  private MaterialButton delete_all;

  private SharedPreferences sr;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.banco);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    frame = findViewById(R.id.frame);
    image_background = findViewById(R.id.image_background);
    content = findViewById(R.id.content);
    search = findViewById(R.id.search);
    value = findViewById(R.id.value);
    datalist = findViewById(R.id.datalist);
    delete_all = findViewById(R.id.delete_all);
    sr = getSharedPreferences("sr", Activity.MODE_PRIVATE);

    delete_all.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View _view) {
        dataBase.deleteAllDataFrom("DataBase");
        items.clear();
        data_adapter.update(items);
        return true;
      }
    });
  }

  private void initializeLogic() {


    data_layout = new LinearLayoutManager(this);
    datalist.setLayoutManager(data_layout);
    data_adapter = new DatabaseAdapter(BancoActivity.this, items);
    datalist.setAdapter(data_adapter);
    dataBase = EasiestDB.init(this).addTableColumns("DataBase", new Column("pgs", "text"), new Column("rps", "text")).doneAddingTables();
    new Thread(new Runnable() {
      public void run() {
        Cursor res = dataBase.getAllDataFrom("Database");
        if(res.getCount() != 0) {
          while(res.moveToNext()) {
            String pgs = CookieUtils.decrypt(res.getString(1), BancoActivity.this);
            String rps = CookieUtils.decrypt(res.getString(2), BancoActivity.this);
            items.add(new DatabaseModel(pgs, rps, res.getString(0)));
            data.add(pgs);
            data.add(rps);
          }
        }
        res.close();
      }
    }).start();
    data_adapter.update(items);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sugestion, R.id.text, data);
    search.setAdapter(adapter);

    search.addTextChangedListener(new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
      }

      @Override
      public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {
      }

      @Override
      public void afterTextChanged(Editable p1) {
        list_search.clear();
        String res = p1.toString().toLowerCase().trim();
        for(int i = 0; i < items.size(); i++) {
          if(items.get(i).getMessage().toLowerCase().contains(res)) {
            list_search.add(new DatabaseModel(items.get(i).getMessage(), items.get(i).getAnswer(), items.get(i).getID()));
          }
        }
        if(list_search.size() > 0) {
          data_adapter.update(list_search);
        } else {
          data_adapter.update(items);
        }
      }
    });
    value.setText(String.valueOf(data_adapter.getItemCount()));
    bg = CookieUtils.giver(BancoActivity.this);
    if(!bg.isDirectory()) {
      bg.mkdirs();
    } else {
      File bg_file = new File(bg, "/background.png");
      FileInputStream fis;
      if(bg_file.exists()) {
        try {
          fis = new FileInputStream(bg_file);
          Bitmap bit_bg = BitmapFactory.decodeStream(fis);
          image_background.setImageBitmap(bit_bg);
          fis.close();
        } catch(Exception e) {
        }
      }
    }
  }

  @Override
  public void onBackPressed() {
    finish();
  }


}
