package com.example.bttuan4;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tvWelcome);

        // Lấy username từ LoginActivity
        String user = getIntent().getStringExtra("username");
        if (user != null) {
            tvWelcome.setText("Xin chào, " + user + "!");
        }
    }
}
