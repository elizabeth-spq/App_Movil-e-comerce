package com.example.finalandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.finalandroid.databinding.ActivityAgregarBinding;
import com.example.finalandroid.databinding.ActivityMainBinding;
import com.example.finalandroid.datos.DatosSQLite;

public class AgregarActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAgregarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnAgregar.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        String nombre = binding.edpais.getText().toString();
        String capital = binding.edCapital.getText().toString();
        String poblacion = binding.edPoblacion.getText().toString();
        String area = binding.edArea.getText().toString();

        DatosSQLite datosSQLite = new DatosSQLite(this);
        int autonumerico = datosSQLite.paisInsert(datosSQLite, nombre, capital, poblacion,area);

        binding.edpais.setText("");
        binding.edCapital.setText("");
        binding.edPoblacion.setText("");
        binding.edArea.setText("");

        Toast.makeText(this, "Pais registrados " + autonumerico,
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}