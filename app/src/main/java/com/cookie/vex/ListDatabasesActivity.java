package com.cookie.vex;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.richit.easiestsqllib.EasiestDB;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ListDatabasesActivity extends AppCompatActivity {

  private LinearLayoutManager lista_layout;
  private ListaAdapter lista_adapter;
  private EasiestDB dataBase;
  private File localFile;
  private String mail = "";
  private StringBuilder text;

  private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> imported_list = new ArrayList<>();

  private LinearLayout linear1;
  private RecyclerView lista;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.list_databases);
    initialize(_savedInstanceState);
  }

  private void initialize(Bundle _savedInstanceState) {
    linear1 = findViewById(R.id.linear1);
    lista = findViewById(R.id.lista);
  }

  @Override
  public void onBackPressed() {
    finish();
  }

  public void _createSnackBar(final String _message) {
    ViewGroup parentLayout = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
    Snackbar.make(parentLayout, _message, Snackbar.LENGTH_LONG)
      .setAction("OK", new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
      }).show();
  }

  public void _Prog_Dialogue_show(final boolean _ifShow, final String _title, final String _message) {
    if (_ifShow) {
      if (prog == null) {
        prog = new ProgressDialog(this);
        prog.setMax(100);
        prog.setIndeterminate(true);
        prog.setCancelable(false);
        prog.setCanceledOnTouchOutside(false);
      }
      prog.setMessage(_message);
      prog.show();
    } else {
      if (prog != null) {
        prog.dismiss();
      }
    }
  }

  private ProgressDialog prog;
}
