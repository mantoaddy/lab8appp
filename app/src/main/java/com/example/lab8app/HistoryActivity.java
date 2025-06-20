package com.example.lab8app;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    private TextView logs;
    private ImageButton back;
    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        logs = findViewById(R.id.logs);
        back = findViewById(R.id.back);
        user = findViewById(R.id.user);
        String username = Users.getuser(this);
        user.setText("Добро пожаловать, " + username + "! ;)");

        logs = findViewById(R.id.logs);
        back = findViewById(R.id.back);

        StringBuilder sb = new StringBuilder();
        for (String log : Log.gettasklogs()) {
            sb.append(log).append("\n");
        }
        logs.setText(sb.toString());

        back.setOnClickListener(v -> finish());

    }
}
