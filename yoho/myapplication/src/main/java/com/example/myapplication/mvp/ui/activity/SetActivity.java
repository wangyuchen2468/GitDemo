package com.example.myapplication.mvp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.myapplication.R;

public class SetActivity extends AppCompatActivity {
    Button tuichu;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        tuichu=findViewById(R.id.set_tuichu_activity);
        back=findViewById(R.id.set_back_activity);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences islogin =  getSharedPreferences("userTag", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = islogin.edit();
                edit.putBoolean("login", false);
                edit.commit();
                finish();
                Intent intent = new Intent(SetActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
