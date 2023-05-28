package com.cookie.vex;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import com.cookie.vex.CookieUtils;

public class DebugActivity extends AppCompatActivity {

  private File bg;

  private FrameLayout frame;
  private ImageView image_background;
  private LinearLayout base;
  private TextView info;
  private LinearLayout content;
  private ScrollView scroll;
  private TextView textview1;

  private Intent i = new Intent();

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.debug);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    frame = findViewById(R.id.frame);
    image_background = findViewById(R.id.image_background);
    base = findViewById(R.id.base);
    info = findViewById(R.id.info);
    content = findViewById(R.id.content);
    scroll = findViewById(R.id.scroll);
    textview1 = findViewById(R.id.textview1);

    textview1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        CookieUtils.mkToast(DebugActivity.this, "Erro Copiado!");
        ((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString()));
      }
    });
  }

  private void initializeLogic() {

    textview1.setText(getIntent().getStringExtra("error"));
    bg = CookieUtils.giver(DebugActivity.this);
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
