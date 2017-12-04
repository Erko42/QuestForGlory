package com.example.erik.questforglory.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.erik.questforglory.R;

public class Settings extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void onBackPressed() {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void mainMenu(View view) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
