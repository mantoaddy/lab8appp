package com.example.lab8app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout namel, emaill, passl, confl;
    private TextInputEditText namei, emaili, passi, confi;
    private MaterialButton btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        namel = findViewById(R.id.namel);
        emaill = findViewById(R.id.emaill);
        passl = findViewById(R.id.passl);
        confl = findViewById(R.id.confpass);

        namei = findViewById(R.id.namei);
        emaili = findViewById(R.id.emaili);
        passi = findViewById(R.id.passi);
        confi = findViewById(R.id.confi);

        btnreg = findViewById(R.id.btnreg);

        namei.addTextChangedListener(new twatch(this::validname));
        emaili.addTextChangedListener(new twatch(this::validemail));
        passi.addTextChangedListener(new twatch(this::validpass));
        confi.addTextChangedListener(new twatch(this::validconf));

        btnreg.setOnClickListener(v -> {
            if (validname() & validemail() & validpass() & validconf()) {
                String name = namei.getText().toString().trim();
                String email = emaili.getText().toString().trim();
                String pass = passi.getText().toString().trim();

                Users.saveuser(this, name, email, pass);
                Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, ScActivity.class));
                finish();
            }
        });

        findViewById(R.id.login).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
    private boolean validname() {
        String name = namei.getText().toString().trim();
        if (name.isEmpty() || name.length() > 20) {
            namel.setError("Введите имя (до 20 символов)");
            return false;
        } else {
            namel.setError(null);
            return true;
        }
    }
    private boolean validemail() {
        String email = emaili.getText().toString().trim();
        if (!Valid.validemail(email)) {
            emaill.setError("Неверный формат почты");
            return false;
        } else {
            emaill.setError(null);
            return true;
        }
    }
    private boolean validpass() {
        String password = passi.getText().toString().trim();
        if (!Valid.validpass(password)) {
            passl.setError("Пароль должен содержать только цифры (до 8 символов)");
            return false;
        } else {
            passl.setError(null);
            return true;
        }
    }
    private boolean validconf() {
        String password = passi.getText().toString().trim();
        String confirm = confi.getText().toString().trim();
        if (!password.equals(confirm)) {
            confl.setError("Пароли не совпадают");
            return false;
        } else {
            confl.setError(null);
            return true;
        }
    }
    private static class twatch implements TextWatcher {
        private final Runnable bc;
        twatch(Runnable bc) {
            this.bc = bc;
        }
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            bc.run();
        }
        @Override public void afterTextChanged(Editable s) { }
    }
}
