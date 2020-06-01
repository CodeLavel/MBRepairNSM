package com.example.nsmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    SessionManager sessionManager;
    private TextView empName,empCode,empTel,empMail,userLevel;
    private Button btn_fix,btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        empName = findViewById(R.id.empName);
        empCode = findViewById(R.id.empCode);
        empTel = findViewById(R.id.empTel);
        userLevel = findViewById(R.id.userLevel);
        empMail = findViewById(R.id.empMail);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String FName = user.get(sessionManager.EMP_FRISTNAME);
        String LName = user.get(sessionManager.EMP_LASTNAME);
        String Code = user.get(sessionManager.EMP_CODE);
        String Email = user.get(sessionManager.EMP_EMAIL);
        String Tel = user.get(sessionManager.EMP_TEL);
        String Level = user.get(sessionManager.USERLEVEL);

        empName.setText(FName+" "+LName);
        empCode.setText(Code);
        empTel.setText(Tel);
        userLevel.setText(Level);
        empMail.setText(Email);

        btn_logout = findViewById(R.id.btn_logout);
        btn_fix = findViewById(R.id.btn_fix);

        btn_fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AccList.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });
    }
}
