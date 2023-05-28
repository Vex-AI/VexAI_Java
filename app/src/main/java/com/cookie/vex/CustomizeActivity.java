package com.cookie.vex;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.cookie.vex.CookieUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.cardview.widget.CardView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;


public class CustomizeActivity extends AppCompatActivity {

  private File bg_file;
  private Bitmap bitmap;
  private String name = "background.png";
  private GradientDrawable cookie = new GradientDrawable();
  private int top1 = 15;
  private int top2 = 15;
  private int top3 = 15;
  private int top4 = 15;
  private int color;
  private int stroke_size = -1;
  private int stroke_color = Color.WHITE;
  private boolean changes = false;
  private int text_color = Color.WHITE;
  private int ripple_color = Color.WHITE;
  private RippleDrawable rip;

  private LinearLayout background;
  private LinearLayout content_preview;
  private ScrollView scroll_functions;
  private LinearLayout preview;
  private TextView text_preview;
  private LinearLayout content;
  private AppCompatCheckBox check_msg;
  private TextView text_radius;
  private LinearLayout linear_radius;
  private CardView card_radius_color;
  private CardView card_radius_text_color;
  private TextView text_stroke;
  private LinearLayout stroke_content;
  private CardView card_stroke_color;
  private TextView ripple_text;
  private CardView card_ripple_color;
  private MaterialButton save_style;
  private MaterialButton delete_style;
  private CardView card_img_preview;
  private MaterialButton pick_bg;
  private MaterialButton save_bg;
  private MaterialButton delete_bg;
  private TextView info_text;
  private LinearLayout linear_radius_1;
  private LinearLayout linear_radius_2;
  private LinearLayout linear_radius_3;
  private LinearLayout linear_radius_4;
  private TextView text_top1;
  private Slider seek_top1;
  private TextView n_top1;
  private TextView text_top2;
  private Slider seek_top2;
  private TextView n_top2;
  private TextView text_bottom1;
  private Slider seek_top4;
  private TextView n_top4;
  private TextView text_bottom2;
  private Slider seek_top3;
  private TextView n_top3;
  private LinearLayout linear_radius_content;
  private TextView text_radius_color;
  private LinearLayout layout_color;
  private LinearLayout linear_radius_text_content;
  private TextView text_radius_color_text;
  private LinearLayout color_text;
  private Slider bordas;
  private LinearLayout linear_stroke_content;
  private TextView text_stroke_color;
  private LinearLayout border_preview;
  private LinearLayout linear_ripple_content;
  private TextView text_ripple_color;
  private LinearLayout ripple_color_preview;
  private ImageView bg_preview;

  private SharedPreferences pr;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.customize);
    initialize(_savedInstanceState);

   initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    background = findViewById(R.id.background);
    content_preview = findViewById(R.id.content_preview);
    scroll_functions = findViewById(R.id.scroll_functions);
    preview = findViewById(R.id.preview);
    text_preview = findViewById(R.id.text_preview);
    content = findViewById(R.id.content);
    check_msg = findViewById(R.id.check_msg);
    text_radius = findViewById(R.id.text_radius);
    linear_radius = findViewById(R.id.linear_radius);
    card_radius_color = findViewById(R.id.card_radius_color);
    card_radius_text_color = findViewById(R.id.card_radius_text_color);
    text_stroke = findViewById(R.id.text_stroke);
    stroke_content = findViewById(R.id.stroke_content);
    card_stroke_color = findViewById(R.id.card_stroke_color);
    ripple_text = findViewById(R.id.ripple_text);
    card_ripple_color = findViewById(R.id.card_ripple_color);
    save_style = findViewById(R.id.save_style);
    delete_style = findViewById(R.id.delete_style);
    card_img_preview = findViewById(R.id.card_img_preview);
    pick_bg = findViewById(R.id.pick_bg);
    save_bg = findViewById(R.id.save_bg);
    delete_bg = findViewById(R.id.delete_bg);
    info_text = findViewById(R.id.info_text);
    linear_radius_1 = findViewById(R.id.linear_radius_1);
    linear_radius_2 = findViewById(R.id.linear_radius_2);
    linear_radius_3 = findViewById(R.id.linear_radius_3);
    linear_radius_4 = findViewById(R.id.linear_radius_4);
    text_top1 = findViewById(R.id.text_top1);
    seek_top1 = findViewById(R.id.seek_top1);
    n_top1 = findViewById(R.id.n_top1);
    text_top2 = findViewById(R.id.text_top2);
    seek_top2 = findViewById(R.id.seek_top2);
    n_top2 = findViewById(R.id.n_top2);
    text_bottom1 = findViewById(R.id.text_bottom1);
    seek_top4 = findViewById(R.id.seek_top4);
    n_top4 = findViewById(R.id.n_top4);
    text_bottom2 = findViewById(R.id.text_bottom2);
    seek_top3 = findViewById(R.id.seek_top3);
    n_top3 = findViewById(R.id.n_top3);
    linear_radius_content = findViewById(R.id.linear_radius_content);
    text_radius_color = findViewById(R.id.text_radius_color);
    layout_color = findViewById(R.id.layout_color);
    linear_radius_text_content = findViewById(R.id.linear_radius_text_content);
    text_radius_color_text = findViewById(R.id.text_radius_color_text);
    color_text = findViewById(R.id.color_text);
    bordas = findViewById(R.id.bordas);
    linear_stroke_content = findViewById(R.id.linear_stroke_content);
    text_stroke_color = findViewById(R.id.text_stroke_color);
    border_preview = findViewById(R.id.border_preview);
    linear_ripple_content = findViewById(R.id.linear_ripple_content);
    text_ripple_color = findViewById(R.id.text_ripple_color);
    ripple_color_preview = findViewById(R.id.ripple_color_preview);
    bg_preview = findViewById(R.id.bg_preview);
    pr = getSharedPreferences("sr", Activity.MODE_PRIVATE);

    preview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {

      }
    });

    check_msg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {

      }
    });

    check_msg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
        final boolean _isChecked = _param2;
        if(_isChecked) {
          text_preview.setText("Esta é sua mensagem");
          pr.edit().putBoolean("linear", true).apply();
        } else {
          text_preview.setText("Esta é a mensagem da Vex");
          pr.edit().putBoolean("linear", false).apply();
        }
        _setColor(_isChecked, pr);
      }
    });

    save_style.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        SharedPreferences.Editor editor = pr.edit();
        if(check_msg.isChecked()) {
          editor.putInt("top1_user", top1);
          editor.putInt("top2_user", top2);
          editor.putInt("top3_user", top3);
          editor.putInt("top4_user", top4);
          editor.putInt("user_color", color);
          if(text_color != 0) {
            editor.putInt("user_text_color", text_color);
          }
          editor.putInt("user_ripple_color", ripple_color);
        } else {
          editor.putInt("top1_other", top1);
          editor.putInt("top2_other", top2);
          editor.putInt("top3_other", top3);
          editor.putInt("top4_other", top4);
          editor.putInt("other_color", color);
          if(text_color != 0) {
            editor.putInt("other_text_color", text_color);
          }
          editor.putInt("other_ripple_color", ripple_color);
        }
        if(stroke_size != -1) {
          if(check_msg.isChecked()) {
            editor.putInt("user_stroke_size", stroke_size);
            editor.putInt("user_stroke_color", stroke_color);
          } else {
            editor.putInt("other_stroke_size", stroke_size);
            editor.putInt("other_stroke_color", stroke_color);
          }
        }
        changes = true;
        editor.commit();
        CookieUtils.mkToast(CustomizeActivity.this, "Salvo!");
      }
    });

    delete_style.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        if(check_msg.isChecked()) {
          pr.edit().remove("user_color").commit();
          pr.edit().remove("user_stroke_size").commit();
          pr.edit().remove("user_text_color").commit();
          pr.edit().remove("top1_user").commit();
          pr.edit().remove("top2_user").commit();
          pr.edit().remove("top3_user").commit();
          pr.edit().remove("top4_user").commit();
          pr.edit().remove("user_stroke_color").commit();
        } else {
          pr.edit().remove("other_color").commit();
          pr.edit().remove("other_stroke_size").commit();
          pr.edit().remove("other_text_color").commit();
          pr.edit().remove("top1_other").commit();
          pr.edit().remove("top2_other").commit();
          pr.edit().remove("top3_other").commit();
          pr.edit().remove("top4_other").commit();
          pr.edit().remove("other_stroke_color").commit();
        }
        changes = true;
        CookieUtils.mkToast(CustomizeActivity.this, "Estilo Deletado!");
      }
    });

    pick_bg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, "Escolha uma imagem"), 1);
      }
    });

    save_bg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        if(bitmap == null) {
          _createSnackBar("Nenhuma imagem encontrada :(");
          return;
        }
        try {
          File file = new File(bg_file, name);
          FileOutputStream out = new FileOutputStream(file);
          bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
          out.flush();
          out.close();
          _createSnackBar("Background salvo!");
          changes = true;
        } catch(Exception e) {
          _createSnackBar("Ocorreu o erro: " + e.toString());
        }
      }
    });

    delete_bg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        File bg_img = new File(bg_file, "background.png");
        if(bg_file.isDirectory() && bg_img.exists()) {
          bg_img.delete();
          bg_preview.setImageResource(R.drawable.wpp);
          changes = true;
          _createSnackBar("Feito!");
        } else {
          bg_file.mkdirs();
          _createSnackBar("Nenhum background encontrado :(");
        }
      }
    });

    layout_color.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        ColorPickerDialogBuilder.
          with(CustomizeActivity.this)
          .setTitle("")
          .initialColor(color)
          .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
          .density(12)
          .setOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int selectedColor) {
              layout_color.setBackgroundColor(selectedColor);
              cookie.setColor(selectedColor);
              rip = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{ripple_color}), cookie, null);
              preview.setBackground(rip);
            }
          })
          .setPositiveButton("OK", new ColorPickerClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
              layout_color.setBackgroundColor(selectedColor);
              cookie.setColor(selectedColor);
              color = selectedColor;
            }
          })
          .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
          })
          .build()
          .show();
      }
    });

    color_text.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        ColorPickerDialogBuilder.
          with(CustomizeActivity.this)
          .setTitle("")
          .initialColor(text_color)
          .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
          .density(12)
          .setOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int selectedColor) {
              text_preview.setTextColor(selectedColor);
              color_text.setBackgroundColor(selectedColor);
              text_color = selectedColor;
            }
          })
          .setPositiveButton("OK", new ColorPickerClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
              text_preview.setTextColor(selectedColor);
              color_text.setBackgroundColor(selectedColor);
              text_color = selectedColor;
            }
          })
          .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
          })
          .build()
          .show();

      }
    });

    border_preview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        ColorPickerDialogBuilder.
          with(CustomizeActivity.this)
          .setTitle("")
          .initialColor(stroke_color)
          .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
          .density(12)
          .setOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int selectedColor) {
              stroke_color = selectedColor;
              if(stroke_size != 0) {
                cookie.setStroke((int) stroke_size, stroke_color);
              }
            }
          })
          .setPositiveButton("OK", new ColorPickerClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
              stroke_color = selectedColor;
              if(stroke_size != 0) {
                cookie.setStroke((int) stroke_size, stroke_color);
              }
              border_preview.setBackgroundColor(selectedColor);
              rip = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{ripple_color}), cookie, null);
              preview.setBackground(rip);
            }
          })
          .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
          })
          .build()
          .show();
      }
    });

    ripple_color_preview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        ColorPickerDialogBuilder.
          with(CustomizeActivity.this)
          .setTitle("")
          .initialColor(ripple_color)
          .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
          .density(12)
          .setOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int selectedColor) {
              ripple_color = selectedColor;
              ripple_color_preview.setBackgroundColor(selectedColor);
              rip = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{selectedColor}), cookie, null);
              preview.setBackground(rip);
            }
          })
          .setPositiveButton("OK", new ColorPickerClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
              ripple_color = selectedColor;
              ripple_color_preview.setBackgroundColor(selectedColor);
              rip = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{selectedColor}), cookie, null);
              preview.setBackground(rip);
            }
          })
          .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
          })
          .build()
          .show();
      }
    });
  }

  private void initializeLogic() {

    bg_file = CookieUtils.giver(CustomizeActivity.this);
    color = Color.RED;
    cookie.setShape(GradientDrawable.RECTANGLE);
    cookie.setColor(color);
    _setColor(pr.getBoolean("linear", true), pr);
    check_msg.setChecked(pr.getBoolean("linear", true));
    _SeekBars();
    try {
      FileInputStream fis = new FileInputStream(bg_file + "/background.png");
      Bitmap bit_bg = BitmapFactory.decodeStream(fis);
      bg_preview.setImageBitmap(bit_bg);
      fis.close();
    } catch(Exception e) {
    }
  }


  @Override
  public void onBackPressed() {
    if(changes) {
      finishAffinity();
    } else {
      finish();
    }
  }

  public void _elevation(final double _num) {


  }

  public void Gradient(final int top1, final int top2, final int top3, final int top4) {
    cookie.setCornerRadii(new float[]{top1, top1, top2, top2, top3, top3, top4, top4});
    rip = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{ripple_color}), cookie, null);
    preview.setBackground(rip);

  }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
      if(!bg_file.isDirectory()) {
        bg_file.mkdirs();
      }
      Uri uri = data.getData();
      try {
        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        bg_preview.setImageBitmap(bitmap);
      } catch(Exception e) {
        CookieUtils.mkToast(CustomizeActivity.this, e.toString());
      }
    }
  }


  public void _setColor(final boolean _user, final SharedPreferences _share) {
    if(_user) {
      if(_share.getInt("user_color", -1) != -1) {
        text_preview.setText("Esta é sua mensagem");
        color = _share.getInt("user_color", -1);
        cookie.setColor(color);
        layout_color.setBackgroundColor(color);
        top1 = _share.getInt("top1_user", 15);
        top2 = _share.getInt("top2_user", 15);
        top3 = _share.getInt("top3_user", 15);
        top4 = _share.getInt("top4_user", 15);
        if(_share.getInt("user_stroke_size", 0) != 0) {
          stroke_size = _share.getInt("user_stroke_size", -1);
          stroke_color = _share.getInt("user_stroke_color", -1);
          cookie.setStroke(stroke_size, stroke_color);
          bordas.setValue((float) stroke_size);

          border_preview.setBackgroundColor(stroke_color);
        }
        if(_share.getInt("user_text_color", -1) != -1) {
          text_color = _share.getInt("user_text_color", -1);
          text_preview.setTextColor(text_color);
          color_text.setBackgroundColor(text_color);
        }
      }
      if(_share.getInt("user_ripple_color", 0) != 0) {
        ripple_color = _share.getInt("user_ripple_color", 0);
      }
    } else {
      if(_share.getInt("other_color", -1) != -1) {
        text_preview.setText("Esta é a mensagem da Vex");
        color = _share.getInt("other_color", -1);
        layout_color.setBackgroundColor(color);
        cookie.setColor(color);
        top1 = _share.getInt("top1_other", 15);
        top2 = _share.getInt("top2_other", 15);
        top3 = _share.getInt("top3_other", 15);
        top4 = _share.getInt("top4_other", 15);
        if(_share.getInt("other_stroke_size", 0) != 0) {
          stroke_size = _share.getInt("other_stroke_size", -1);
          stroke_color = _share.getInt("other_stroke_color", -1);
          cookie.setStroke(stroke_size, stroke_color);
          bordas.setValue((float) stroke_size);

          border_preview.setBackgroundColor(stroke_color);
        }
        if(_share.getInt("other_text_color", -1) != -1) {
          text_color = _share.getInt("other_text_color", -1);
          text_preview.setTextColor(text_color);
          color_text.setBackgroundColor(text_color);
        }
      }
      if(_share.getInt("other_ripple_color", 0) != 0) {
        ripple_color = _share.getInt("other_ripple_color", 0);
      }
    }
    seek_top1.setValue((float) top1);
    seek_top2.setValue((float) top2);
    seek_top3.setValue((float) top3);
    seek_top4.setValue((float) top4);

    n_top1.setText(String.valueOf((long) top1));
    n_top2.setText(String.valueOf((long) top2));
    n_top3.setText(String.valueOf((long) top3));
    n_top4.setText(String.valueOf((long) top4));
    ripple_color_preview.setBackgroundColor(ripple_color);
    Gradient(top1, top2, top3, top4);
  }


  public void _SeekBars() {
    seek_top1.addOnChangeListener(new Slider.OnChangeListener() {
      @Override
      public void onValueChange(Slider slider, float value, boolean fromUser) {
        n_top1.setText(String.valueOf((long) value));
        top1 = (int) value;
        Gradient(top1, top2, top3, top4);
      }
    });

    seek_top2.addOnChangeListener(new Slider.OnChangeListener() {
      @Override
      public void onValueChange(Slider slider, float value, boolean fromUser) {
        n_top2.setText(String.valueOf((long) value));
        top2 = (int) value;
        Gradient(top1, top2, top3, top4);
      }
    });

    seek_top3.addOnChangeListener(new Slider.OnChangeListener() {
      @Override
      public void onValueChange(Slider slider, float value, boolean fromUser) {
        n_top3.setText(String.valueOf((long) value));
        top3 = (int) value;
        Gradient(top1, top2, top3, top4);
      }
    });

    seek_top4.addOnChangeListener(new Slider.OnChangeListener() {
      @Override
      public void onValueChange(Slider slider, float value, boolean fromUser) {
        n_top4.setText(String.valueOf((long) value));
        top4 = (int) value;
        Gradient(top1, top2, top3, top4);
      }
    });

    bordas.addOnChangeListener(new Slider.OnChangeListener() {
      @Override
      public void onValueChange(Slider slider, float value, boolean fromUser) {
        stroke_size = (int) value;
        if(stroke_color == 0) {
          cookie.setStroke((int) value, Color.parseColor("#ffffff"));
        } else {
          cookie.setStroke((int) value, stroke_color);
        }
        Gradient(top1, top2, top3, top4);
      }
    });
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
