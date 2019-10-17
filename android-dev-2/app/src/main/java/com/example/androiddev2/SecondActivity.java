package com.example.androiddev2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final TextView textView = findViewById(R.id.textView);
        final EditText answer = findViewById(R.id.editText);
        final Button button = findViewById(R.id.button2);
        textView.setText(getIntent().getStringExtra("question"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("answer", answer.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
