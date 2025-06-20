package com.example.lab8app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout emaill, passl;
    private TextInputEditText emaili, passi;
    private MaterialButton btnlg;
    private TextView reg, error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emaill = findViewById(R.id.emaill);
        passl = findViewById(R.id.passl);
        emaili = findViewById(R.id.emaili);
        passi = findViewById(R.id.passi);
        btnlg = findViewById(R.id.btnlg);
        reg = findViewById(R.id.reg);
        error = findViewById(R.id.error);

        emaili.addTextChangedListener(new twatch(this::validenail));
        passi.addTextChangedListener(new twatch(this::validpass));

        btnlg.setOnClickListener(v -> {
            boolean validemail = validenail();
            boolean validpass = validpass();
            String email = emaili.getText().toString().trim();
            String password = passi.getText().toString().trim();

            if (validemail && validpass && Users.checkuser(this, email, password)) {
                startActivity(new Intent(MainActivity.this, ScActivity.class));
                finish();
            } else {
                error.setText("Проверьте введённые данные");
                error.setVisibility(TextView.VISIBLE);
            }
        });
        reg.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RegisterActivity.class))
        );
    }
    private boolean validenail() {
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
            passl.setError("Пароль должен быть цифрами, до 8 символов");
            return false;
        } else {
            passl.setError(null);
            return true;
        }
    }
    private static class twatch implements TextWatcher {
        private final Runnable bc;
        twatch(Runnable bc) { this.bc = bc; }
        @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
        @Override public void onTextChanged(CharSequence s, int st, int b, int c) { bc.run(); }
        @Override public void afterTextChanged(Editable s) {}
    }
}
