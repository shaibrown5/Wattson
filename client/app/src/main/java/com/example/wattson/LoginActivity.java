package com.example.wattson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button m_logInButton;
    private TextView txt_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        m_logInButton = (Button) findViewById(R.id.logInButton);
        txt_signUp = (TextView) findViewById(R.id.signUpText);

        m_logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OnBoardingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
