package com.example.finalandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalandroid.adapter.PedidoAdapter;
import com.example.finalandroid.databinding.ActivityContactosBinding;
import com.example.finalandroid.databinding.ActivityPedidoBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PedidoActivity extends AppCompatActivity {
    private ActivityPedidoBinding binding;

    ArrayList arrayList = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        String idempresaenvio = bundle.getString("idempresaenvio");
        String nombre = bundle.getString("nombre");
        String telefono = bundle.getString("telefono");

        binding.tvcate.setText(nombre);
        binding.tvnumero.setText(telefono);

        //this.setTitle(nombre);
        //getSupportActionBar().setSubtitle(telefono);
        leerServicio(idempresaenvio);
    }
    private void leerServicio(String idempresaenvio) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://servicios.campus.pe/pedidosenvio.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("idempresaenvio",idempresaenvio);
                return map;
            }
        };
        queue.add(stringRequest);
    }
    private void llenarLista(String response) {

        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                String idpedido = jsonArray.getJSONObject(i).getString("idpedido");
                String cliente = jsonArray.getJSONObject(i).getString("cliente");
                String paisdestinatario = jsonArray.getJSONObject(i).getString("paisdestinatario");
                String ciudaddestinatario  = jsonArray.getJSONObject(i).getString("ciudaddestinatario");

                HashMap<String, String> map = new HashMap<>();
                map.put("idpedido", idpedido);
                map.put("cliente", cliente);
                map.put("paisdestinatario", paisdestinatario);
                map.put("ciudaddestinatario", ciudaddestinatario);

                arrayList.add(map);
            }

            PedidoAdapter pedidoAdapter = new PedidoAdapter(arrayList);
            binding.rvPedidos.setAdapter(pedidoAdapter);
            binding.rvPedidos.setLayoutManager(new GridLayoutManager(this,2));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}