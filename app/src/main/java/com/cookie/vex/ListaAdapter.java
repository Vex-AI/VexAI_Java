package com.cookie.vex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

  ArrayList<HashMap<String, Object>> _data;

  public ListaAdapter(ArrayList<HashMap<String, Object>> _arr) {
    _data = _arr;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater _inflater = LayoutInflater.from(parent.getContext());
    View _v = _inflater.inflate(R.layout.list_view, parent, false);
    return new ViewHolder(_v);
  }

  @Override
  public void onBindViewHolder(ViewHolder _holder, final int _position) {
    View _view = _holder.itemView;

    final LinearLayout linear2 = _view.findViewById(R.id.linear2);
    final LinearLayout linear1 = _view.findViewById(R.id.linear1);
    final LinearLayout linear3 = _view.findViewById(R.id.linear3);
    final Button install = _view.findViewById(R.id.install);
    final TextView textview3 = _view.findViewById(R.id.textview3);
    final TextView nome = _view.findViewById(R.id.nome);
    final TextView textview4 = _view.findViewById(R.id.textview4);
    final TextView number_words = _view.findViewById(R.id.number_words);
  }

  @Override
  public int getItemCount() {
    return _data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View v) {
      super(v);
    }
  }
}
