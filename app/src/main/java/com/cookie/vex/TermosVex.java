package com.cookie.vex;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class TermosVex {

  public TermosVex(final Context privacy) {


    final AlertDialog dialog = new AlertDialog.Builder(privacy).create();

    View melancia = LayoutInflater.from(privacy).inflate(R.layout.terms, null);
    dialog.setView(melancia);
    final Intent i = new Intent();

    final MaterialButton termos = (MaterialButton) melancia.findViewById(R.id.termos);

    final MaterialButton politica = (MaterialButton) melancia.findViewById(R.id.politica);

    final MaterialButton back = (MaterialButton) melancia.findViewById(R.id.back);

    final TextView texto = (TextView) melancia.findViewById(R.id.texto);

    final OnMyDialogResult mDialogResult;

    final SharedPreferences sd = PreferenceManager.getDefaultSharedPreferences(privacy);

    final SharedPreferences.Editor shar = sd.edit();

    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View melancia) {

        dialog.dismiss();
      }
    });
    politica.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View melancia) {
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://cookieukw.blogspot.com/2021/11/politica-de-privacidade.html?m=1"));
        privacy.startActivity(i);


        dialog.dismiss();

      }
    });


    termos.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View melancia) {
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://cookieukw.blogspot.com/2021/11/termos-de-uso-e-condicoes.html?m=1"));
        privacy.startActivity(i);

        dialog.dismiss();
      }
    });

    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    dialog.setCancelable(false);
    dialog.show();
  }

  public void setDialogResult(OnMyDialogResult dialogResult) {

  }

  public interface OnMyDialogResult {
    void finish(String result);
  }
}