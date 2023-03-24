package com.example.finalandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalandroid.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PaisAdapter extends RecyclerView.Adapter<PaisAdapter.ViewHolder> {

    ArrayList<HashMap<String,String>> arrayList;

    public PaisAdapter(ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PaisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pais, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaisAdapter.ViewHolder holder, int position) {
        HashMap<String,String> map = arrayList.get(position);

        holder.mtvNombrePais.setText(map.get("nombre"));
        holder.mtvCapital.setText("Capital: "+ map.get("capital"));
        holder.mtvPoblacion.setText("Población: "+ map.get("poblacion")+" millones");
        holder.mtvArea.setText("Área: "+map.get("area") + " km2");

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mtvNombrePais,mtvCapital,mtvPoblacion,mtvArea;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvNombrePais = itemView.findViewById(R.id.tvpais);
            mtvCapital = itemView.findViewById(R.id.tvCapital);
            mtvPoblacion = itemView.findViewById(R.id.tvPoblacion);
            mtvArea = itemView.findViewById(R.id.tvArea);
        }
    }
}
