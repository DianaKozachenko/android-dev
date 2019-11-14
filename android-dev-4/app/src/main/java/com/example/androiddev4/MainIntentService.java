package com.example.androiddev4;

import android.app.IntentService;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainIntentService extends IntentService {

    public MainIntentService() {
        super("MainIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int number = intent.getIntExtra("number", 0);
        List<Integer> primeNumbers = getPrimaryNumbers(number);
        String result = primeNumbers
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        send(primeNumbers.size(), result);
    }

    private void send(int total, String allNumbers) {
        Intent intent = new Intent("MainActivity");
        intent.putExtra("primeNumbers", allNumbers);
        intent.putExtra("amount", total);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private List getPrimaryNumbers(Integer number) {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 0; i <= number; i++) {
            if (isPrime(i))
                primeNumbers.add(i);
        }
        return primeNumbers;
    }

    public boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
