// Generated by view binder compiler. Do not edit!
package com.cookie.vex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cookie.vex.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListViewBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button install;

  @NonNull
  public final LinearLayout linear1;

  @NonNull
  public final LinearLayout linear2;

  @NonNull
  public final LinearLayout linear3;

  @NonNull
  public final TextView nome;

  @NonNull
  public final TextView numberWords;

  @NonNull
  public final TextView textview3;

  @NonNull
  public final TextView textview4;

  private ListViewBinding(@NonNull LinearLayout rootView, @NonNull Button install,
      @NonNull LinearLayout linear1, @NonNull LinearLayout linear2, @NonNull LinearLayout linear3,
      @NonNull TextView nome, @NonNull TextView numberWords, @NonNull TextView textview3,
      @NonNull TextView textview4) {
    this.rootView = rootView;
    this.install = install;
    this.linear1 = linear1;
    this.linear2 = linear2;
    this.linear3 = linear3;
    this.nome = nome;
    this.numberWords = numberWords;
    this.textview3 = textview3;
    this.textview4 = textview4;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ListViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.install;
      Button install = ViewBindings.findChildViewById(rootView, id);
      if (install == null) {
        break missingId;
      }

      id = R.id.linear1;
      LinearLayout linear1 = ViewBindings.findChildViewById(rootView, id);
      if (linear1 == null) {
        break missingId;
      }

      id = R.id.linear2;
      LinearLayout linear2 = ViewBindings.findChildViewById(rootView, id);
      if (linear2 == null) {
        break missingId;
      }

      id = R.id.linear3;
      LinearLayout linear3 = ViewBindings.findChildViewById(rootView, id);
      if (linear3 == null) {
        break missingId;
      }

      id = R.id.nome;
      TextView nome = ViewBindings.findChildViewById(rootView, id);
      if (nome == null) {
        break missingId;
      }

      id = R.id.number_words;
      TextView numberWords = ViewBindings.findChildViewById(rootView, id);
      if (numberWords == null) {
        break missingId;
      }

      id = R.id.textview3;
      TextView textview3 = ViewBindings.findChildViewById(rootView, id);
      if (textview3 == null) {
        break missingId;
      }

      id = R.id.textview4;
      TextView textview4 = ViewBindings.findChildViewById(rootView, id);
      if (textview4 == null) {
        break missingId;
      }

      return new ListViewBinding((LinearLayout) rootView, install, linear1, linear2, linear3, nome,
          numberWords, textview3, textview4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
