package com.example.bttuan4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText etNewUser, etNewPass;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNewUser = findViewById(R.id.etNewUser);
        etNewPass = findViewById(R.id.etNewPass);
        btnSave   = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String newUser = etNewUser.getText().toString();
            String newPass = etNewPass.getText().toString();

            if (!newUser.isEmpty() && !newPass.isEmpty()) {
                Toast.makeText(this, "Đăng ký thành công cho " + newUser, Toast.LENGTH_SHORT).show();
                finish(); // Quay lại LoginActivity
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
