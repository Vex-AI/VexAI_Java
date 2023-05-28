// Generated by view binder compiler. Do not edit!
package com.cookie.vex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cookie.vex.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AboutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView about;

  @NonNull
  public final LinearLayout base;

  @NonNull
  public final LinearLayout baseText;

  @NonNull
  public final CardView cardImg;

  @NonNull
  public final FrameLayout frame;

  @NonNull
  public final ImageView imageBackground;

  @NonNull
  public final MaterialButton politica;

  @NonNull
  public final MaterialButton termos;

  @NonNull
  public final ImageView vector;

  @NonNull
  public final TextView vexname;

  private AboutBinding(@NonNull LinearLayout rootView, @NonNull TextView about,
      @NonNull LinearLayout base, @NonNull LinearLayout baseText, @NonNull CardView cardImg,
      @NonNull FrameLayout frame, @NonNull ImageView imageBackground,
      @NonNull MaterialButton politica, @NonNull MaterialButton termos, @NonNull ImageView vector,
      @NonNull TextView vexname) {
    this.rootView = rootView;
    this.about = about;
    this.base = base;
    this.baseText = baseText;
    this.cardImg = cardImg;
    this.frame = frame;
    this.imageBackground = imageBackground;
    this.politica = politica;
    this.termos = termos;
    this.vector = vector;
    this.vexname = vexname;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AboutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AboutBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.about, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AboutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.about;
      TextView about = ViewBindings.findChildViewById(rootView, id);
      if (about == null) {
        break missingId;
      }

      id = R.id.base;
      LinearLayout base = ViewBindings.findChildViewById(rootView, id);
      if (base == null) {
        break missingId;
      }

      id = R.id.base_text;
      LinearLayout baseText = ViewBindings.findChildViewById(rootView, id);
      if (baseText == null) {
        break missingId;
      }

      id = R.id.card_img;
      CardView cardImg = ViewBindings.findChildViewById(rootView, id);
      if (cardImg == null) {
        break missingId;
      }

      id = R.id.frame;
      FrameLayout frame = ViewBindings.findChildViewById(rootView, id);
      if (frame == null) {
        break missingId;
      }

      id = R.id.image_background;
      ImageView imageBackground = ViewBindings.findChildViewById(rootView, id);
      if (imageBackground == null) {
        break missingId;
      }

      id = R.id.politica;
      MaterialButton politica = ViewBindings.findChildViewById(rootView, id);
      if (politica == null) {
        break missingId;
      }

      id = R.id.termos;
      MaterialButton termos = ViewBindings.findChildViewById(rootView, id);
      if (termos == null) {
        break missingId;
      }

      id = R.id.vector;
      ImageView vector = ViewBindings.findChildViewById(rootView, id);
      if (vector == null) {
        break missingId;
      }

      id = R.id.vexname;
      TextView vexname = ViewBindings.findChildViewById(rootView, id);
      if (vexname == null) {
        break missingId;
      }

      return new AboutBinding((LinearLayout) rootView, about, base, baseText, cardImg, frame,
          imageBackground, politica, termos, vector, vexname);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}