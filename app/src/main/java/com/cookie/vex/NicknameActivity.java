package com.cookie.vex;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;


public class NicknameActivity extends AppCompatActivity {

  private File bg;

  private FrameLayout frame;
  private ImageView image_background;
  private LinearLayout content;
  private LinearLayout linear2;
  private TextView textview1;
  private LinearLayout linear5;
  private MaterialButton mai;
  private EditText nome_user;

  private SharedPreferences sd;
  private Intent i = new Intent();

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.nickname);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    frame = findViewById(R.id.frame);
    image_background = findViewById(R.id.image_background);
    content = findViewById(R.id.content);
    linear2 = findViewById(R.id.linear2);
    textview1 = findViewById(R.id.textview1);
    linear5 = findViewById(R.id.linear5);
    mai = findViewById(R.id.mai);
    nome_user = findViewById(R.id.nome_user);
    sd = getSharedPreferences("sr", Activity.MODE_PRIVATE);

    mai.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        String data = nome_user.getText().toString().trim().toLowerCase();
        if(data.isEmpty()) {
          _createSnackBar("Preencha o campo!");
        } else {
          sd.edit().putString("nick", data).commit();
          finish();
        }
      }
    });
  }

  private void initializeLogic() {

    if(!sd.getString("nick", "").isEmpty()) {
      nome_user.setText(sd.getString("nick", ""));
    }
    bg = CookieUtils.giver(NicknameActivity.this);
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


  public void _createSnackBar(final String _message) {
    //com.google.android.material.snackbar.Snackbar
    ViewGroup parentLayout = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

    Snackbar.make(parentLayout, _message,
        Snackbar.LENGTH_LONG)
      .setAction("OK", new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
      }).show();
  }

}
