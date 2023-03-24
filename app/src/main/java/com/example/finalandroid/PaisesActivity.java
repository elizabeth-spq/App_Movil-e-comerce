package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;

import com.example.finalandroid.adapter.ContactoAdapter;
import com.example.finalandroid.adapter.PaisAdapter;
import com.example.finalandroid.databinding.ActivityContactosBinding;
import com.example.finalandroid.databinding.ActivityPaisesBinding;
import com.example.finalandroid.datos.DatosSQLite;

import java.util.ArrayList;
import java.util.HashMap;

public class PaisesActivity extends AppCompatActivity {
    private ActivityPaisesBinding binding;

    ArrayList arrayList = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaisesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LeerDatos();

        PaisAdapter paisAdapter = new PaisAdapter(arrayList);
        binding.rvpaises.setAdapter(paisAdapter);
        binding.rvpaises.setLayoutManager(new LinearLayoutManager(this));
    }

    private void LeerDatos() {
        DatosSQLite datosSQLite = new DatosSQLite(this);
        Cursor cursor = datosSQLite.paisesSelect(datosSQLite);
        arrayList.clear();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    HashMap<String,String> map = new HashMap<>();
                    map.put("idpais", cursor.getString(cursor.getColumnIndexOrThrow("idpais")));
                    map.put("nombre", cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                    map.put("capital", cursor.getString(cursor.getColumnIndexOrThrow("capital")));
                    map.put("poblacion", cursor.getString(cursor.getColumnIndexOrThrow("poblacion")));
                    map.put("area", cursor.getString(cursor.getColumnIndexOrThrow("area")));

                    arrayList.add(map);


                }

                while(cursor.moveToNext());

            }


        }

    }

}
