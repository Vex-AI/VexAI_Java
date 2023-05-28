package com.cookie.vex;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.iitr.kaishu.nsidedprogressbar.NSidedProgressBar;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.itsaky.androidide.logsender.LogSender;

public class SplashActivity extends AppCompatActivity {

  private File bg;
  private boolean add = false;
  private TimerTask task;
  private Timer timer = new Timer();
  private String[] frases;
  private FrameLayout frame;
  private ImageView image_background;
  private LinearLayout base;
  private MaterialCardView card_vector;
  private NSidedProgressBar pg_bar;
  private TextView dica_txt;
  private ImageView vector;

  private Intent i = new Intent();
  private SharedPreferences sr;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.main);
    initialize(_savedInstanceState);
        LogSender.startLogging(this);
        initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    frame = findViewById(R.id.frame);
    image_background = findViewById(R.id.image_background);
    base = findViewById(R.id.base);
    card_vector = findViewById(R.id.card_vector);
    pg_bar = findViewById(R.id.pg_bar);
    dica_txt = findViewById(R.id.dica_txt);
    vector = findViewById(R.id.vector);
    sr = getSharedPreferences("sr", Activity.MODE_PRIVATE);
  }

  private void initializeLogic() {

    bg = CookieUtils.giver(SplashActivity.this);
    if(!bg.isDirectory()) {
      bg.mkdirs();
    } else {
      File bg_file = new File(bg, "/background.png");
      File profile_file = new File(bg, "/profile.png");
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
    frases = getResources().getStringArray(R.array.frases);
    dica_txt.setText(frases[CookieUtils.getNumb(frases.length)]);
    Animation animFadein = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
    dica_txt.startAnimation(animFadein);
    task = new TimerTask() {
      @Override
      public void run() {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            i.setClass(getApplicationContext(), HomeActivity.class);
            startActivity(i);
            finish();
          }
        });
      }
    };
    timer.schedule(task, 1200);
  }

}
