package com.cookie.vex;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;


import java.util.Arrays;
import java.util.List;


public class UpdateActivity extends AppCompatActivity {

  private String url = "";

  private LinearLayout linear1;
  private ImageView linear3;
  private TextView aviso;
  private MaterialButton atualizar;

  private Intent intent = new Intent();
  private SharedPreferences sr;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.update);
    initialize(_savedInstanceState);
  }

  private void initialize(Bundle _savedInstanceState) {
    linear1 = findViewById(R.id.linear1);
    linear3 = findViewById(R.id.linear3);
    aviso = findViewById(R.id.aviso);
    atualizar = findViewById(R.id.atualizar);
    sr = getSharedPreferences("sr", Activity.MODE_PRIVATE);

    atualizar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
        finishAffinity();
      }
    });
  }

  @Override
  public void onBackPressed() {
    finish();
  }


 
  public void _createSnackBar(final String _message) {
    ViewGroup parentLayout = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
    com.google.android.material.snackbar.Snackbar.make(parentLayout, _message, com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
      .setAction("OK", new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
      }).show();
  }

}
