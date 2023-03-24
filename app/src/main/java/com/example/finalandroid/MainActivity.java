package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivbd.setOnClickListener(this);
        binding.ivbe.setOnClickListener(this);
        binding.ivbf.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivbd:
                startActivity(new Intent(this, ContactosActivity.class));

                break;
            case R.id.ivbe:
                startActivity(new Intent(this, AgregarActivity.class));

                break;
            case R.id.ivbf:
                startActivity(new Intent(this, PaisesActivity.class));

                break;


        }
    }
}