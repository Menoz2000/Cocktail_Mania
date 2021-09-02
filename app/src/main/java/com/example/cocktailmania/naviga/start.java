package com.example.cocktailmania.naviga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.cocktailmania.R;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(start.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 2000);
    }
}