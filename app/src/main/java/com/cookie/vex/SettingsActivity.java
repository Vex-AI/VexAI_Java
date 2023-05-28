package com.cookie.vex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.richit.easiestsqllib.Column;
import org.richit.easiestsqllib.Datum;
import org.richit.easiestsqllib.EasiestDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private File bg;
    private EasiestDB cookieDB;
    private ArrayList<HashMap<String, Object>> exportList = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> importList = new ArrayList<>();

    private FrameLayout linear1;
    private ImageView imageBackground;
    private RelativeLayout background;
    private LinearLayout ovalLinear;
    private CardView card;
    private LinearLayout config;
    private TextView configText;
    private CardView cardSinopse;
    private LinearLayout content;
    private MaterialButton importFile;
    private MaterialButton exportFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        initialize(savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle savedInstanceState) {
        linear1 = findViewById(R.id.linear1);
        imageBackground = findViewById(R.id.image_background);
        background = findViewById(R.id.background);
        ovalLinear = findViewById(R.id.oval_linear);
        card = findViewById(R.id.card);
        config = findViewById(R.id.config);
        configText = findViewById(R.id.config_text);
        cardSinopse = findViewById(R.id.card_sinopse);
        content = findViewById(R.id.content);
        importFile = findViewById(R.id.import_file);
        exportFile = findViewById(R.id.export_file);

        importFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setType("application/octet-stream");
                startActivityForResult(Intent.createChooser(intent, "Escolha o arquivo .vex"), 1);
            }
        });

        exportFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog(true, "", "Exportando...");
                exportData();
                if (exportList.size() == 0) {
                    showProgressDialog(false, "", "");
                    return;
                }
                startActivityForResult(CookieUtils.createFile("db_" + CookieUtils.getInterval(0000000, 9999999) + ".vex", "text*/"), 2);
            }
        });
    }

    private void initializeLogic() {
        cookieDB = EasiestDB.init(this)
                .addTableColumns("DataBase",
                        new Column("pg", "text"),
                        new Column("rp", "text")
                )
                .doneAddingTables();
        bg = CookieUtils.giver(SettingsActivity.this);
        if (!bg.isDirectory()) {
            bg.mkdirs();
        } else {
            File bgFile = new File(bg, "/background.png");
            FileInputStream fis;
            if (bgFile.exists()) {
                try {
                    fis = new FileInputStream(bgFile);
                    Bitmap bitBg = BitmapFactory.decodeStream(fis);
                    imageBackground.setImageBitmap(bitBg);
                    fis.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == 2) {
                try {
                    OutputStream stream = getContentResolver().openOutputStream(data.getData());
                    stream.write(CookieUtils.encrypt(new Gson().toJson(exportList), SettingsActivity.this).getBytes());
                    stream.flush();
                    stream.close();
                    CookieUtils.mkToast(SettingsActivity.this, "certo");
                    showProgressDialog(false, "", "");
                } catch (Exception e) {
                    CookieUtils.mkToast(SettingsActivity.this, e.toString());
                    showProgressDialog(false, "", "");
                }
            }
            if (requestCode == 1) {
                showProgressDialog(true, "", "Importando...");
                final Uri uri = data.getData();
                String dir = uri.toString();
                if (dir.substring(dir.lastIndexOf(".")).equals(".vex") || dir.endsWith(".vex")) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                importList = new Gson().fromJson(CookieUtils.decrypt(CookieUtils.loadFile(uri, SettingsActivity.this), SettingsActivity.this), new TypeToken<ArrayList<HashMap<String, Object>>>() {
                                }.getType());
                                Cursor cursor = null;
                                for (int i = 0; i < importList.size(); i++) {
                                    cursor = cookieDB.searchInOneColumn(1, importList.get(i).get("pg").toString(), 1, 0);
                                    if (cursor == null) {
                                        boolean added = cookieDB.addDataInTable(0,
                                                new Datum(1, importList.get(i).get("pg").toString()),
                                                new Datum(2, importList.get(i).get("rp").toString())
                                        );
                                    }
                                }
                                if (cursor != null) {
                                    cursor.close();
                                }
                            } catch (final Exception e) {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        createSnackBar("Ocorreu um erro 99 " + e.toString());
                                        showProgressDialog(false, "", "");
                                    }
                                });
                            }
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    createSnackBar("Importado com sucesso!");
                                    showProgressDialog(false, "", "");
                                }
                            });
                        }
                    }).start();
                } else {
                    createSnackBar("Escolha um arquivo com extensão \".vex\"");
                    showProgressDialog(false, "", "");
                }
            }
        } else {
            showProgressDialog(false, "", "");
        }
    }

    public void exportData() {
        final Cursor res = cookieDB.getAllDataFrom("DataBase");
        exportList.clear();
        if (res.getCount() != 0) {
            while (res.moveToNext()) {
                HashMap<String, Object> item = new HashMap<>();
                item.put("pg", res.getString(1));
                item.put("rp", res.getString(2));
                exportList.add(item);
            }
        } else {
            createSnackBar("Não há dados para exportar!");
            showProgressDialog(false, "", "");
        }
        res.close();
    }

    public void createSnackBar(final String message) {
        ViewGroup parentLayout = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                }).show();
    }

    public void showProgressDialog(final boolean ifShow, final String title, final String message) {
        if (ifShow) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setMax(100);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
            }
            progressDialog.setMessage(message);
            progressDialog.show();
        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    private ProgressDialog progressDialog;
}
