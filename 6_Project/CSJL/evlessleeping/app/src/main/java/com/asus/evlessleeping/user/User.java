package com.asus.evlessleeping.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.asus.evlessleeping.R;

public class User extends AppCompatActivity {
    private Button mReturnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        mReturnButton = (Button)findViewById(R.id.returnback);
    }
    public void back_to_login(View view) {
        Intent intent3 = new Intent(User.this,Login.class) ;
        startActivity(intent3);
        finish();
    }
}