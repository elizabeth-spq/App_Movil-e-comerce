package com.example.finalandroid.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalandroid.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList;

    public static OnItemClickListener onItemClickListener;

    public ContactoAdapter(ArrayList<HashMap<String,String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contacto, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String,String> map = arrayList.get(position);
        holder.mtvEmpresaNombre.setText(map.get("nombre"));
        holder.mtvEmpresaTelefono.setText(map.get("telefono"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mtvEmpresaNombre,mtvEmpresaTelefono;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvEmpresaNombre = itemView.findViewById(R.id.tvNombreEmpresa);
            mtvEmpresaTelefono = itemView.findViewById(R.id.tvTelefono);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("POSICION", String.valueOf(getLayoutPosition()));
            onItemClickListener.onItemClick(getLayoutPosition());
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        ContactoAdapter.onItemClickListener = onItemClickListener;
    }
}
