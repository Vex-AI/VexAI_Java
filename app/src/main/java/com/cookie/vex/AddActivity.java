package com.cookie.vex;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

import org.richit.easiestsqllib.Column;
import org.richit.easiestsqllib.Datum;
import org.richit.easiestsqllib.EasiestDB;

import java.io.File;
import java.io.FileInputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddActivity extends AppCompatActivity {

  private File bg;
  private EasiestDB dataBase;
  private boolean check = false;

  private ArrayList<String> xx = new ArrayList<>();

  private FrameLayout frame;
  private ImageView image_background;
  private LinearLayout base;
  private LinearLayout linear_ad;
  private TextView text_learn;
  private LinearLayout round_base;
  private MaterialButton save;
  private TextView text_pgs;
  private LinearLayout round_pgs;
  private LinearLayout linear_clear;
  private TextView text_rps;
  private LinearLayout round_rps;
  private TextView tags;
  private EditText pgs;
  private ImageView vector_delete1;
  private CheckBox clear;
  private TextView textview7;
  private EditText rps;
  private ImageView vector_delete2;

  private SharedPreferences sr;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.add);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    frame = findViewById(R.id.frame);
    image_background = findViewById(R.id.image_background);
    base = findViewById(R.id.base);
    linear_ad = findViewById(R.id.linear_ad);
    text_learn = findViewById(R.id.text_learn);
    round_base = findViewById(R.id.round_base);
    save = findViewById(R.id.save);
    text_pgs = findViewById(R.id.text_pgs);
    round_pgs = findViewById(R.id.round_pgs);
    linear_clear = findViewById(R.id.linear_clear);
    text_rps = findViewById(R.id.text_rps);
    round_rps = findViewById(R.id.round_rps);
    tags = findViewById(R.id.tags);
    pgs = findViewById(R.id.pgs);
    vector_delete1 = findViewById(R.id.vector_delete1);
    clear = findViewById(R.id.clear);
    textview7 = findViewById(R.id.textview7);
    rps = findViewById(R.id.rps);
    vector_delete2 = findViewById(R.id.vector_delete2);
    sr = getSharedPreferences("sr", Activity.MODE_PRIVATE);

    save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        _Prog_Dialogue_show(true, "", "Carregando...");
        new Thread(new Runnable() {
          public void run() {
            runOnUiThread(new Runnable() {
              public void run() {
                String input1, input2;

                if(clear.isChecked()) {
                  input1 = Normalizer.normalize(pgs.getText().toString().toLowerCase().trim().replaceAll("[.#$\\[\\]]", ""), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                } else {
                  input1 = pgs.getText().toString().toLowerCase().trim().replaceAll("[.#$\\[\\]]", "");
                }
                input2 = rps.getText().toString().toLowerCase().trim();
                if(!input1.isEmpty() && !input2.isEmpty()) {
                  String[] arr = input1.concat(" ".concat(input2)).split("\\s");
                  for(int i = 0; i < arr.length; i++) {
                    if(xx.contains(arr[i])) {
                      _createSnackBar("Esta mensagem contém conteúdo inapropriado ^w^");
                      _Prog_Dialogue_show(false, "", "");
                      pgs.setText("");
                      rps.setText("");
                      break;
                    } else {
                      if(URLUtil.isValidUrl(arr[i])) {
                        _createSnackBar("Remova o link!  nossos termos não permitem eles. ;3");
                        _Prog_Dialogue_show(false, "", "");
                        break;
                      }
                    }
                  }
                  input1 = CookieUtils.encrypt(input1, AddActivity.this);
                  input2 = CookieUtils.encrypt(input2, AddActivity.this);
                  Cursor cursor = dataBase.searchInOneColumn(1, input1, 1, 0);
                  if(cursor != null) {
                    _createSnackBar("Isto ja foi salvo, tente outra coisa^3^");
                  } else {
                    boolean added = dataBase.addDataInTable(0, new Datum(1, input1), new Datum(2, input2));
                    if(added) {
                      _createSnackBar("Salvo com sucesso!");
                      pgs.setText("");
                      rps.setText("");
                    } else {
                      _createSnackBar("Ocorreu um erro ao gravar!");
                    }
                  }
                  _Prog_Dialogue_show(false, "", "");
                } else {
                  _Prog_Dialogue_show(false, "", "");
                }
              }
            });
          }
        }).start();
      }
    });

    text_pgs.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View _view) {

        return true;
      }
    });

    vector_delete1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        pgs.setText("");
      }
    });

    vector_delete2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        rps.setText("");
      }
    });
  }

  private void initializeLogic() {

    
    dataBase = EasiestDB.init(this).addTableColumns("Database", new Column("pg", "text"), new Column("rp", "text")).doneAddingTables();
    bg = CookieUtils.giver(AddActivity.this);
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
    ViewGroup parentLayout = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
    com.google.android.material.snackbar.Snackbar.make(parentLayout, _message, com.google.android.material.snackbar.Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
      @Override
      public void onClick(View view) {

      }
    }).show();
  }


  public void _Prog_Dialogue_show(final boolean _ifShow, final String _title, final String _message) {
    if(_ifShow) {
      if(prog == null) {
        prog = new ProgressDialog(this);
        prog.setMax(100);
        prog.setIndeterminate(true);
        prog.setCancelable(false);
        prog.setCanceledOnTouchOutside(false);
      }
      prog.setMessage(_message);
      prog.show();
    } else {
      if(prog != null) {
        prog.dismiss();
      }
    }
  }

  private ProgressDialog prog;
}
