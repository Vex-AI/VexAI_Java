package com.cookie.vex;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cookie.vex.CookieUtils;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stdio.swipetoreply.ISwipeControllerActions;
import com.stdio.swipetoreply.SwipeController;

import org.richit.easiestsqllib.Column;
import org.richit.easiestsqllib.Datum;
import org.richit.easiestsqllib.EasiestDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;


public class HomeActivity extends AppCompatActivity {

  private Timer _timer = new Timer();

  private long backTime = 0;
  private ArrayList<MessageListModel> items = new ArrayList<MessageListModel>();
  private MessageAdapter vex_adapter;
  private File bg;
  private Toolbar _toolbar;
  private AppBarLayout _app_bar;
  private CoordinatorLayout _coordinator;
  private DrawerLayout _drawer;
  private HashMap<String, Object> msg = new HashMap<>();
  private String id = "";
  private LinearLayoutManager vex_layout;
  private Gson gson = new Gson();
  private EasiestDB cookieDB;
  private String[] response;

  private ArrayList<String> soft = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> import_list = new ArrayList<>();

  private FrameLayout background;
  private ImageView image_background;
  private LinearLayout relative;
  private LinearLayout content_profile;
  private RecyclerView chat;
  private CardView card_type;
  private CardView card_profile;
  private TextView vex_name;
  private CardView card_online;
  private ImageView vex_profile;
  private LinearLayout content_type;
  private LinearLayout tipying;
  private LinearLayout linear_text;
  private LottieAnimationView lottie1;
  private TextView type_text;
  private ImageView drawable_save;
  private LinearLayout type_base;
  private EditText edittext_msg;
  private ImageView drawable_send;
  private LinearLayout _drawer_base;
  private LinearLayout _drawer_contribution;
  private LinearLayout _drawer_customizar;
  private LinearLayout _drawer_database;
  private LinearLayout _drawer_settings;
  private LinearLayout _drawer_clear;
  private LinearLayout _drawer_tutorial;
  private LinearLayout _drawer_about;
  private ImageView _drawer_contribution_icon;
  private TextView _drawer_text_ads;
  private Switch _drawer_donation;
  private ImageView _drawer_customize_icon;
  private TextView _drawer_text_chat_customize;
  private ImageView _drawer_database_icon;
  private TextView _drawer_text_vex_learn;
  private ImageView _drawer_functions_icon;
  private TextView _drawer_text_learn_functions;
  private ImageView _drawer_clear_icon;
  private TextView _drawer_text_clear_chat;
  private ImageView _drawer_tutorial_icon;
  private TextView _drawer_text_tutorial;
  private ImageView _drawer_about_icon;
  private TextView _drawer_text_about;

  private TimerTask time_msg;
  private Intent i = new Intent();
  private SharedPreferences sd;
  private Calendar data = Calendar.getInstance();

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.home);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    _app_bar = findViewById(R.id._app_bar);
    _coordinator = findViewById(R.id._coordinator);
    _toolbar = findViewById(R.id._toolbar);
    setSupportActionBar(_toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    _toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _v) {
        onBackPressed();
      }
    });
    _drawer = findViewById(R.id._drawer);
    ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
    _drawer.addDrawerListener(_toggle);
    _toggle.syncState();

    LinearLayout _nav_view = findViewById(R.id._nav_view);

    background = findViewById(R.id.background);
    image_background = findViewById(R.id.image_background);
    relative = findViewById(R.id.relative);
    content_profile = findViewById(R.id.content_profile);
    chat = findViewById(R.id.chat);
    card_type = findViewById(R.id.card_type);
    card_profile = findViewById(R.id.card_profile);
    vex_name = findViewById(R.id.vex_name);
    card_online = findViewById(R.id.card_online);
    vex_profile = findViewById(R.id.vex_profile);
    content_type = findViewById(R.id.content_type);
    tipying = findViewById(R.id.tipying);
    linear_text = findViewById(R.id.linear_text);
    lottie1 = findViewById(R.id.lottie1);
    type_text = findViewById(R.id.type_text);
    drawable_save = findViewById(R.id.drawable_save);
    type_base = findViewById(R.id.type_base);
    edittext_msg = findViewById(R.id.edittext_msg);
    drawable_send = findViewById(R.id.drawable_send);
    _drawer_base = _nav_view.findViewById(R.id.base);
    _drawer_contribution = _nav_view.findViewById(R.id.contribution);
    _drawer_customizar = _nav_view.findViewById(R.id.customizar);
    _drawer_database = _nav_view.findViewById(R.id.database);
    _drawer_settings = _nav_view.findViewById(R.id.settings);
    _drawer_clear = _nav_view.findViewById(R.id.clear);
    _drawer_tutorial = _nav_view.findViewById(R.id.tutorial);
    _drawer_about = _nav_view.findViewById(R.id.about);
    _drawer_contribution_icon = _nav_view.findViewById(R.id.contribution_icon);
    _drawer_text_ads = _nav_view.findViewById(R.id.text_ads);
    _drawer_donation = _nav_view.findViewById(R.id.donation);
    _drawer_customize_icon = _nav_view.findViewById(R.id.customize_icon);
    _drawer_text_chat_customize = _nav_view.findViewById(R.id.text_chat_customize);
    _drawer_database_icon = _nav_view.findViewById(R.id.database_icon);
    _drawer_text_vex_learn = _nav_view.findViewById(R.id.text_vex_learn);
    _drawer_functions_icon = _nav_view.findViewById(R.id.functions_icon);
    _drawer_text_learn_functions = _nav_view.findViewById(R.id.text_learn_functions);
    _drawer_clear_icon = _nav_view.findViewById(R.id.clear_icon);
    _drawer_text_clear_chat = _nav_view.findViewById(R.id.text_clear_chat);
    _drawer_tutorial_icon = _nav_view.findViewById(R.id.tutorial_icon);
    _drawer_text_tutorial = _nav_view.findViewById(R.id.text_tutorial);
    _drawer_about_icon = _nav_view.findViewById(R.id.about_icon);
    _drawer_text_about = _nav_view.findViewById(R.id.text_about);
    sd = getSharedPreferences("sr", Activity.MODE_PRIVATE);

    vex_name.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        final AlertDialog changeName = new AlertDialog.Builder(HomeActivity.this).create();
        //LayoutInflater lay = getLayoutInflater();
        View inflate = LayoutInflater.from(HomeActivity.this).inflate(R.layout.setname, null);
        final TextInputLayout lay = (TextInputLayout) inflate.findViewById(R.id.text_content);
        final TextInputEditText name = (TextInputEditText) inflate.findViewById(R.id.name);
        final MaterialButton save = (MaterialButton) inflate.findViewById(R.id.save);
        final MaterialButton cancel = (MaterialButton) inflate.findViewById(R.id.cancel);
        changeName.setView(inflate);
        if(!sd.getString("vex_name", "").isEmpty()) {
          name.setText(sd.getString("vex_name", ""));
        }
        name.addTextChangedListener(new TextWatcher() {
          @Override
          public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
          }

          @Override
          public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {
          }

          @Override
          public void afterTextChanged(Editable p1) {
            if(name.getError() != null) {
              name.setError(null);
            }
          }
        });
        save.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
            String name_content = name.getText().toString().trim();
            if(!name_content.isEmpty()) {
              sd.edit().putString("vex_name", name_content).commit();
              vex_name.setText(name_content);
              type_text.setText(name_content.concat(" está digitando..."));
              changeName.dismiss();
            } else {
              name.setError(getString(R.string.setname));
            }
          }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
            changeName.dismiss();
          }
        });
        changeName.setCancelable(false);
        changeName.show();
      }
    });

    vex_profile.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View _view) {
        File profile = new File(bg, "profile.png");
        if(bg.isDirectory() && profile.exists()) {
          profile.delete();
          vex_profile.setImageResource(R.drawable.app_icon);
          vex_profile.setScaleType(ScaleType.CENTER_INSIDE);
        } else {
          bg.mkdirs();
        }
        return true;
      }
    });

    vex_profile.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, "Escolha a imagem"), 1);
      }
    });

    drawable_save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        if(sd.getBoolean("status", true)) {
          i.setClass(getApplicationContext(), AddActivity.class);
          startActivity(i);
        } else {
          _createSnackBar("Função desabilitada para esta conta!");
        }
      }
    });

    drawable_send.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        if(!edittext_msg.getText().toString().trim().isEmpty()) {
          if(sd.getString("nick", "").isEmpty()) {
            i.setClass(getApplicationContext(), NicknameActivity.class);
            startActivity(i);
          } else {
            _Analyzer(edittext_msg);
          }
        }
      }
    });

    _drawer_contribution.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {

      }
    });

    _drawer_customizar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        i.setClass(getApplicationContext(), CustomizeActivity.class);
        startActivity(i);
      }
    });

    _drawer_database.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setClass(getApplicationContext(), BancoActivity.class);
        startActivity(i);
      }
    });

    _drawer_settings.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        i.setClass(getApplicationContext(), SettingsActivity.class);
        startActivity(i);
      }
    });

    _drawer_clear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        cookieDB.deleteAllDataFrom("ChatMessages");
        items.clear();
        vex_adapter.update(items);
      }
    });

    _drawer_tutorial.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        i.setClass(getApplicationContext(), TutorialActivity.class);
        startActivity(i);
      }
    });

    _drawer_about.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View _view) {
        i.setClass(getApplicationContext(), AboutActivity.class);
        startActivity(i);
      }
    });

    _drawer_donation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
        final boolean _isChecked = _param2;
        if(_isChecked) {
          sd.edit().putBoolean("contribution", true).apply();
        } else {
          sd.edit().putBoolean("contribution", false).apply();
        }
      }
    });
  }

  private void initializeLogic() {


    vex_layout =
      new LinearLayoutManager(this);
    vex_layout.setStackFromEnd(true);
    chat.setLayoutManager(vex_layout);
    vex_adapter = new MessageAdapter(HomeActivity.this, items);
    chat.setAdapter(vex_adapter);
    getSupportActionBar().hide();
    _Adjust();
    cookieDB = EasiestDB.init(this)
      .addTableColumns("ChatMessages",
        new Column("msg", "text"),
        new Column("type", "text"),
        new Column("hour", "text")
      )
      .addTableColumns("DataBase",
        new Column("pg", "text"),
        new Column("rp", "text")
      )
      .doneAddingTables();
    response = getResources().getStringArray(R.array.response);
    if(sd.getBoolean("contribution", true)) {
      _drawer_donation.setChecked(true);
    } else {
      _drawer_donation.setChecked(false);
    }
    new Thread(new Runnable() {
      public void run() {
        
        Cursor res = cookieDB.getAllDataFrom("ChatMessages");
        if(res.getCount() != 0) {
          while(res.moveToNext()) {
            items.add(new MessageListModel(res.getString(1),
              res.getString(0),
              res.getString(3), res.getString(2)));
          }
          vex_adapter.update(items);
        }
        res.close();
      }
    }).start();
    if(sd.getBoolean("tuto", true)) {
      i.setClass(getApplicationContext(), TutorialActivity.class);
      startActivity(i);
      sd.edit().putBoolean("tuto", false).apply();
    }
    if(sd.getBoolean("dialog", true)) {
      new TermosVex(HomeActivity.this);
      sd.edit().putBoolean("dialog", false).apply();
    }
    bg = CookieUtils.giver(HomeActivity.this);
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
      if(profile_file.exists()) {
        try {
          fis = new FileInputStream(profile_file);
          Bitmap bm = BitmapFactory.decodeStream(fis);
          vex_profile.setImageBitmap(bm);
          vex_profile.setScaleType(ScaleType.CENTER_CROP);
          fis.close();
        } catch(Exception e) {
        }
      }
    }
    SwipeController controller = new SwipeController(HomeActivity.this, new ISwipeControllerActions() {
      @Override
      public void onSwipePerformed(int position) {
        int pos = position;
        boolean deleted = cookieDB.deleteRow(0, (Integer.parseInt(items.get(pos).getID())));
        vex_adapter.removeItem(pos);
        //vex_adapter.notifyItemRangeChanged(pos, list_map.size());
      }
    }, R.drawable.delete_icon, R.color.azul, R.color.vermelho);
    ItemTouchHelper touch = new ItemTouchHelper(controller);
    touch.attachToRecyclerView(chat);
    if(!sd.getString("vex_name", "").isEmpty()) {
      vex_name.setText(sd.getString("vex_name", ""));
      type_text.setText(sd.getString("vex_name", "").concat(" está digitando..."));
    }
    _install();
  }

  @Override
  public void onBackPressed() {

    if(backTime + 1200 > System.currentTimeMillis()) {
      finish();
    } else {
      CookieUtils.mkToast(HomeActivity.this, "Aperte novamente para sair!");
    }
    backTime = System.currentTimeMillis();
  }

  @Override
  public void onStart() {
    super.onStart();

    String img_url = sd.getString("url", "");
    if(!img_url.isEmpty()) {
      Glide.with(vex_profile)
        .load(Uri.parse(img_url))
        .apply(new RequestOptions()
          .fitCenter()
          .format(DecodeFormat.PREFER_ARGB_8888)
          .override(Target.SIZE_ORIGINAL))
        .into(vex_profile);
      if(vex_profile.getScaleType() != ScaleType.CENTER_CROP) {
        vex_profile.setScaleType(ScaleType.CENTER_CROP);
      }
    }
  }

  public void _Adjust() {
    vex_adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
      @Override
      public void onItemRangeInserted(int positionStart, int itemCount) {
        chat.smoothScrollToPosition(vex_adapter.getItemCount() + 1);
        vex_adapter.update(items);
      }
    });
    chat.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
      @Override
      public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if(bottom < oldBottom) {
          chat.smoothScrollToPosition(vex_adapter.getItemCount() + 1);
          vex_adapter.update(items);
        }
      }
    });
  }


  public void _Analyzer(final TextView _view) {
    tipying.setVisibility(View.VISIBLE);
    String text = _view.getText().toString().toLowerCase().trim();
    _view.setText("");
    sendMsg(text, "user");
    ArrayList<String> text_array = clearContent(text);
    String res = inputData(text_array);
    if(res != null) {
      sendMsg(res, "vex");
      return;
    }
    res = getAnswer(TextUtils.join(" ", text_array));
    if(res != null) {
      sendMsg(res, "vex");
      return;
    }
    sendMsg(notFound(), "vex");
  }


  public void _createSnackBar(final String _message) {
    ViewGroup parentLayout = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

    Snackbar.make(parentLayout, _message,
        Snackbar.LENGTH_LONG)
      .setAction("OK", new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
      }).show();
  }


  

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
      if(!bg.isDirectory()) {
        bg.mkdirs();
      }
      Uri uri = data.getData();
      try {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        vex_profile.setImageBitmap(bitmap);
        vex_profile.setScaleType(ScaleType.CENTER_CROP);
        String fname = "profile.png";
        File file = new File(bg, fname);
        FileOutputStream out = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        out.flush();
        out.close();
      } catch(Exception e) {
        CookieUtils.mkToast(HomeActivity.this, e.toString());

           }
    }
  }

  public ArrayList<String> clearContent(String content) {
    String data = Normalizer.normalize(content.replaceAll("[.#$\\[\\]]", ""), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").trim().toLowerCase();
    ArrayList<String> words = new ArrayList<String>(Arrays.asList(data.split("\\s")));
    words.removeAll(Collections.singleton(""));
    words.removeAll(Collections.singleton(null));
    return words;
  }

  public String getAnswer(String word) {
    Cursor check_data = cookieDB.searchInOneColumn(1, CookieUtils.encrypt(word, HomeActivity.this), 1, 1);
    if(check_data != null) {
      check_data.moveToFirst();
      String res = CookieUtils.decrypt(check_data.getString(2), HomeActivity.this);
      check_data.close();
      return res;
    }
    return null;
  }

  public void sendMsg(final String message, final String key) {
    int time = 0;
    if(key.equals("vex")) {
      time = 1600;
    }
    Boolean add = cookieDB.addDataInTable(0,
      new Datum((int) 1, message),
      new Datum((int) 2, key),
      new Datum((int) 3, new SimpleDateFormat("HH:mm").format(data.getTime()))
    );
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      public void run() {
        Cursor id = cookieDB.getAllDataFrom("ChatMessages");
        id.moveToLast();
        items.add(new MessageListModel(message,
          id.getString(0),
          new SimpleDateFormat("HH:mm").format(data.getTime()),
          key));
        id.close();
        chat.smoothScrollToPosition(vex_adapter.getItemCount() + 1);
        vex_adapter.notifyItemInserted(vex_adapter.getItemCount() - 1);
        tipying.setVisibility(View.GONE);
      }
    }, time);
  }

  public String inputData(ArrayList<String> list) {
    for(int i = 0; i < list.size(); i++) {
      String result = getAnswer(list.get(i));
      if(result != null) {
        return result;
      }
    }
    return null;
  }

  public String notFound() {
    return response[CookieUtils.getNumb(response.length)];
  }


  public void _install() {
    if(sd.getBoolean("install", true)) {
      sd.edit().putBoolean("install", false).apply();
    } else {
      return;
    }
    _Prog_Dialogue_show(true, "Aguarde", "Aguarde, estou fazendo instalações...");
    new Thread(new Runnable() {
      public void run() {
        try {
          import_list = new Gson().fromJson(CookieUtils.decrypt(CookieUtils.loadFile(getAssets().open("cookie.vex"), HomeActivity.this), HomeActivity.this), new TypeToken<ArrayList<HashMap<String, Object>>>() {
          }.getType());
          Cursor cursor = null;
          for(int i = 0; i < import_list.size(); i++) {
            cursor = cookieDB.searchInOneColumn(1, import_list.get(i).get("pg").toString(), 1, 1);
            if(cursor == null) {
              boolean added = cookieDB.addDataInTable(1,
                new Datum(1, import_list.get(i).get("pg").toString()),
                new Datum(2, import_list.get(i).get("rp").toString())
              );
            }
          }
          if(cursor != null) {
            cursor.close();
          }
          _Prog_Dialogue_show(false, "", "");
        } catch(final Exception e) {
          runOnUiThread(new Runnable() {
            public void run() {
              _Prog_Dialogue_show(false, "", "");
              _createSnackBar("Ocorreu um erro 99 " + e.toString());
            }
          });
        }
      }
    }).start();
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
