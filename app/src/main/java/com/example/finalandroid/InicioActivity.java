package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        new Handler(Looper.getMainLooper()).postDelayed(() ->{
            mostrarMain();
        },3000);
    }
    private void mostrarMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}