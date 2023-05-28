package com.cookie.vex;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import com.google.android.material.button.MaterialButton;


import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;


public class TutorialActivity extends AppCompatActivity {

  int flipper_pos = 0;

  private LinearLayout background;
  private ViewFlipper viewflipper;
  private LinearLayout relative_base;
  private ImageView vex_tutorial_1;
  private ImageView vex_tutorial_2;
  private ImageView vex_tutorial_5;
  private ImageView vex_tutorial_6;
  private MaterialButton previous;
  private MaterialButton go_back;
  private MaterialButton next;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.tutorial);
    initialize(_savedInstanceState);
  }

  private void initialize(Bundle _savedInstanceState) {
    background = findViewById(R.id.background);
    viewflipper = findViewById(R.id.viewflipper);
    relative_base = findViewById(R.id.relative_base);
    vex_tutorial_1 = findViewById(R.id.vex_tutorial_1);
    vex_tutorial_2 = findViewById(R.id.vex_tutorial_2);
    vex_tutorial_5 = findViewById(R.id.vex_tutorial_5);
    vex_tutorial_6 = findViewById(R.id.vex_tutorial_6);
    previous = findViewById(R.id.previous);
    go_back = findViewById(R.id.go_back);
    next = findViewById(R.id.next);

    previous.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        viewflipper.setInAnimation(TutorialActivity.this, android.R.anim.slide_in_left);
        viewflipper.setOutAnimation(TutorialActivity.this, android.R.anim.slide_out_right);
        viewflipper.showNext();
      }
    });

    go_back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        finish();
      }
    });

    next.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        viewflipper.setInAnimation(TutorialActivity.this, R.anim.slide_in_right);
        viewflipper.setOutAnimation(TutorialActivity.this, R.anim.slide_out_left);
        viewflipper.showPrevious();
      }
    });
  }

  @Override
  public void onBackPressed() {
    finish();
  }
}
