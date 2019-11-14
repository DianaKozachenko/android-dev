package com.example.androiddev4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, new IntentFilter("MainActivity"));

        final Button button = findViewById(R.id.button_ok);
        button.setOnClickListener(v -> {
            final EditText editText = findViewById(R.id.editText);
            int number = Integer.valueOf(editText.getText().toString());
            Intent intent = new Intent(MainActivity.this, MainIntentService.class);
            intent.putExtra("number", number);
            startService(intent);
            button.setEnabled(false);
        });
    }

    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String numbers = intent.getStringExtra("primeNumbers");
            int countNumbers = intent.getIntExtra("amount", 0);
            final TextView countTextView = findViewById(R.id.textView);
            countTextView.setText(Integer.toString(countNumbers));
            final TextView allNumbersTextView = findViewById(R.id.resultTextView);
            allNumbersTextView.setText(numbers);
            final Button button = findViewById(R.id.button_ok);
            button.setEnabled(true);
        }
    };
}