package com.example.android_dev_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_dev_5.db.AppDatabase;
import com.example.android_dev_5.db.User;
import com.example.android_dev_5.db.UserDao;

public class MainActivity extends AppCompatActivity {

    private UserDao userDao;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private TextView textView;
    private EditText birthdayEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.firstNameEditText = findViewById(R.id.firstName);
        this.lastNameEditText = findViewById(R.id.lastName);
        this.textView = findViewById(R.id.textView);
        this.birthdayEditText = findViewById(R.id.birthday);
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        this.userDao = db.userDao();
    }

    public void insert(View view) {
        User user = new User();
        user.setFirstName(this.firstNameEditText.getText().toString());
        user.setLastName(this.lastNameEditText.getText().toString());
        user.setBirthDate(this.birthdayEditText.getText().toString());
        userDao.insert(user);
    }

    public void select(View view) {
        String firstName = this.firstNameEditText.getText().toString();
        String lastName = this.lastNameEditText.getText().toString();
        textView.setText((userDao.findByName(firstName, lastName)).toString());
    }
}
