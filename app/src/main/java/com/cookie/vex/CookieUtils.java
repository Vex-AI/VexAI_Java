package com.cookie.vex;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public final class CookieUtils {
  private static final String IV = "iristhu5";
  private static final String KEY = "cookieukw";

  public static void request(Context context, String link, final VolleyListener listener) {

    Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, link, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        listener.onSuccess(response);
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        listener.onException(error);
      }
    }));

  }

  public static String encrypt(String value, Context ctx) {

    try {

      SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ctx.getString(R.string.ALGORITHM));

      Cipher cipher = Cipher.getInstance(ctx.getString(R.string.MODE));

      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV.getBytes()));

      byte[] values = cipher.doFinal(value.getBytes());

      return Base64.encodeToString(values, Base64.DEFAULT);

    } catch(Exception e) {
      return e.toString();
    }
  }

  public static String decrypt(String value, Context ctx) {
    try {
      byte[] values = Base64.decode(value, Base64.DEFAULT);
      SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ctx.getString(R.string.ALGORITHM));

      Cipher cipher = Cipher.getInstance(ctx.getString(R.string.MODE));

      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(IV.getBytes()));

      return new String(cipher.doFinal(values));
    } catch(Exception e) {
      return e.toString();
    }
  }


  public interface VolleyListener {
    void onSuccess(String response);

    void onException(VolleyError e);
  }

  public static Boolean isVPN(Context ctx) {

    ConnectivityManager cookie = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

    Network activeNetwork = cookie.getActiveNetwork();

    NetworkCapabilities caps = cookie.getNetworkCapabilities(activeNetwork);

    return caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
  }


  public static void deleteCache(Context context) {
    try {
      File dir = context.getCacheDir();
      deleteDir(dir);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean deleteDir(File dir) {
    if(dir != null && dir.isDirectory()) {
      String[] children = dir.list();
      for(int i = 0; i < children.length; i++) {
        boolean success = deleteDir(new File(dir, children[i]));
        if(!success) {
          return false;
        }
      }
      return dir.delete();
    } else if(dir != null && dir.isFile()) {
      return dir.delete();
    } else {
      return false;
    }
  }

  public static boolean isConnected(Context ctx) {
    ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);

    return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
  }

  public static File giver(Context ctx) {

    return new File(ctx.getFilesDir() + "/files/");

  }

  public static File base(Context ctx, String data) {
    File f = ctx.getDatabasePath(data + ".db");

    if(!f.exists()) {
      f.mkdirs();
    }
    return f;
  }

  public static Intent createFile(String name, String type) {
    return new Intent(Intent.ACTION_CREATE_DOCUMENT).addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION).putExtra(Intent.EXTRA_TITLE, name).addCategory(Intent.CATEGORY_OPENABLE).setType(type);
  }

  public static void mkToast(Context ctx, String msg) {
    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
  }

  public static String loadFile(Uri uri, Context ctx) throws Exception {

    StringBuilder text = new StringBuilder();
    BufferedReader reader;
    String line;

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

      InputStream inputStream = ctx.getContentResolver().openInputStream(uri);
      reader = new BufferedReader(new InputStreamReader(inputStream));

      if(inputStream != null) {
        while((line = reader.readLine()) != null) {
          text.append(line).append("\n");
        }
      } else {
        throw new Exception("InputStrem in null");
      }
    } else {

      try(FileReader fileReader = new FileReader(new File(uri.toString()))) {

        reader = new BufferedReader(fileReader);
        line = reader.readLine();

        while(line != null) {
          text.append(line).append("\n");
          line = reader.readLine();
        }
      }
    }
    return text.toString();
  }

  public static String loadFile(InputStream stream, Context ctx) throws Exception {

    StringBuilder text = new StringBuilder();
    BufferedReader reader;
    String line;

    reader = new BufferedReader(new InputStreamReader(stream));

    if(stream != null) {
      while((line = reader.readLine()) != null) {
        text.append(line).append("\n");
      }
    } else {
      throw new Exception("InputStrem in null");
    }

    return text.toString();
  }

  public static int getNumb(int n) {
    return new Random().nextInt(n);
  }

  public static int getInterval(int min, int max) {
    return (new Random()).nextInt((max - min) + 1) + min;
  }

}
