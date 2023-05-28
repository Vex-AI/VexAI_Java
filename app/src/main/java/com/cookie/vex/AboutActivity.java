package com.cookie.vex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

public class AboutActivity extends AppCompatActivity {

  private File bg;

  private FrameLayout frame;
  private ImageView image_background;
  private LinearLayout base;
  private LinearLayout base_text;
  private MaterialButton politica;
  private MaterialButton termos;
  private CardView card_img;
  private TextView vexname;
  private TextView about;
  private ImageView vector;

  private Intent i = new Intent();

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.about);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    frame = findViewById(R.id.frame);
    image_background = findViewById(R.id.image_background);
    base = findViewById(R.id.base);
    base_text = findViewById(R.id.base_text);
    politica = findViewById(R.id.politica);
    termos = findViewById(R.id.termos);
    card_img = findViewById(R.id.card_img);
    vexname = findViewById(R.id.vexname);
    about = findViewById(R.id.about);
    vector = findViewById(R.id.vector);

    politica.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://cookieukw.blogspot.com/2021/11/politica-de-privacidade.html?m=1"));
        startActivity(i);
      }
    });

    termos.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://cookieukw.blogspot.com/2021/11/termos-de-uso-e-condicoes.html?m=1"));
        startActivity(i);
      }
    });
  }

  private void initializeLogic() {

    bg = CookieUtils.giver(AboutActivity.this);
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
