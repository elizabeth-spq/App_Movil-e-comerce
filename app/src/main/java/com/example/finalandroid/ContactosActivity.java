package com.example.finalandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalandroid.adapter.ContactoAdapter;
import com.example.finalandroid.databinding.ActivityContactosBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactosActivity extends AppCompatActivity implements ContactoAdapter.OnItemClickListener {
    private ActivityContactosBinding binding;
    ArrayList arrayList = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        leerServicio();
    }
    private void leerServicio() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://servicios.campus.pe/servicioenvios.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("DATOS", response);
                        llenarLista(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("DATOS", error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
    private void llenarLista(String response) {

        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i= 0; i< jsonArray.length(); i++){
                //getJSONObject permite obtener la fila de un JSONArray
                String idempresaenvio = jsonArray.getJSONObject(i).getString("idempresaenvio");
                String nombre = jsonArray.getJSONObject(i).getString("nombre");
                String telefono = jsonArray.getJSONObject(i).getString("telefono");
                HashMap<String,String> map = new HashMap<>();
                map.put("idempresaenvio",idempresaenvio);
                map.put("nombre",nombre);
                map.put("telefono",telefono);

                arrayList.add(map);//Asi se añade el HashMap al arraylist
            }

            ContactoAdapter contactoAdapter = new ContactoAdapter(arrayList);
            binding.rvListar.setAdapter(contactoAdapter);
            binding.rvListar.setLayoutManager(new LinearLayoutManager(this));
            contactoAdapter.setOnItemClickListener(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        HashMap<String,String> map = (HashMap<String, String>) arrayList.get(position);
        String idempresaenvio = map.get("idempresaenvio");
        String nombre = map.get("nombre");
        String telefono = map.get("telefono");
        Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putString("idempresaenvio",idempresaenvio);
        bundle.putString("nombre",nombre);
        bundle.putString("telefono",telefono);

        Intent intent = new Intent(this, PedidoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}