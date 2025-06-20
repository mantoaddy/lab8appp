package com.example.lab8app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ScActivity extends AppCompatActivity {

    private TextInputLayout startl, endl;
    private TextInputEditText starti, endi;
    private TextView logso;
    private MaterialButton btnst, btncl, btnhis;
    private MaterialButton btntask1, btntask2, btntask3, btnTask4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainsc);

        startl = findViewById(R.id.strt);
        endl = findViewById(R.id.endd);
        starti = findViewById(R.id.strtinp);
        endi = findViewById(R.id.enddinp);
        logso = findViewById(R.id.logso);

        btnst = findViewById(R.id.btnst);
        btncl = findViewById(R.id.btncl);
        btnhis = findViewById(R.id.btnhis);

        btntask1 = findViewById(R.id.btntask1);
        btntask2 = findViewById(R.id.btntask2);
        btntask3 = findViewById(R.id.btntask3);
        btnTask4 = findViewById(R.id.btntask4);

        btntask1.setOnClickListener(v -> {
            Tasks.settask(1);
            logso.setText("Выбрана задача 1 (Цукермана)");
        });
        btntask2.setOnClickListener(v -> {
            Tasks.settask(2);
            logso.setText("Выбрана задача 2 (Нивена)");
        });
        btntask3.setOnClickListener(v -> {
            Tasks.settask(3);
            logso.setText("Выбрана задача 3 (Счастливые числа)");
        });
        btnTask4.setOnClickListener(v -> {
            Tasks.settask(4);
            logso.setText("Выбрана задача 4 (Капрекара)");
        });
        btnst.setOnClickListener(v -> start());
        btncl.setOnClickListener(v -> {
            Log.cleartasklogs();
            logso.setText("Логи очищены");
        });
        btnhis.setOnClickListener(v -> {
            Intent intent = new Intent(ScActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
    private void start() {
        String st = starti.getText().toString().trim();
        String endd = endi.getText().toString().trim();

        if (validinp(st, endd)) {
            int start = Integer.parseInt(st);
            int end = Integer.parseInt(endd);

            List<Integer> result = Tasks.run(start, end);

            StringBuilder sb = new StringBuilder();
            for (Integer number : result) {
                sb.append(number).append(" ");
            }

            logso.setText("Найденные числа:\n" + sb.toString());
        }
    }
    private boolean validinp(String start, String end) {
        boolean isValid = true;

        if (start.isEmpty()) {
            startl.setError("Введите начало диапазона");
            isValid = false;
        } else {
            startl.setError(null);
        }

        if (end.isEmpty()) {
            endl.setError("Введите конец диапазона");
            isValid = false;
        } else {
            endl.setError(null);
        }

        if (isValid) {
            try {
                int startNum = Integer.parseInt(start);
                int endNum = Integer.parseInt(end);

                if (startNum >= endNum) {
                    endl.setError("Конец диапазона должен быть больше начала");
                    isValid = false;
                } else {
                    endl.setError(null);
                }
            } catch (NumberFormatException e) {
                startl.setError("Введите корректные числа");
                endl.setError("Введите корректные числа");
                isValid = false;
            }
        }
        return isValid;
    }
}
