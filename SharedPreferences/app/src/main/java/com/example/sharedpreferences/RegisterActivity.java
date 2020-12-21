package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent back, error;
    private TextView nameSrc, emailSrc, phoneSrc, passSrc, confirmPassSrc;
    private String name, email, phone, pass, confirmPass, prefName = "data";
    private Button submit;

    private SharedPreferences db;
    private String userDataKey = "userData";


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerCaret:
                back = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(back);
                finish();
                break;
            case R.id.registerBtn:
                nameSrc = findViewById(R.id.registerNameTxt);
                emailSrc = findViewById(R.id.registerEmailTxt);
                phoneSrc = findViewById(R.id.registerPhoneTxt);
                passSrc = findViewById(R.id.registerPasswordTxt);
                confirmPassSrc = findViewById(R.id.registerConfirmPasswordTxt);

                name = nameSrc.getText().toString();
                email = emailSrc.getText().toString();
                phone = phoneSrc.getText().toString();
                pass = passSrc.getText().toString();
                confirmPass = confirmPassSrc.getText().toString();

                String toastText;

                //register validation
                if (name.equals("")){ toastText = "Please enter name"; }
                else if (email.equals("") || !email.contains("@")){ toastText = "Please enter a valid email"; }
                else if (phone.equals("")) { toastText = "Please enter phone"; }
                else if (pass.equals("")) { toastText = "Please enter a password"; }
                else if (phone.length() < 11){ toastText = "Please enter a valid phone number"; }
                else if (pass.length() < 8){ toastText = "Password should have at least 8 characters"; }
                else if (!pass.equals(confirmPass)){ toastText = "Passwords don't match"; }
                else {
                    db = getSharedPreferences(prefName, MODE_PRIVATE);
                    SharedPreferences.Editor edtPref = db.edit();
                    edtPref.putString("userName", name);
                    edtPref.putString("userEmail", email);
                    edtPref.putString("userPhone", phone);
                    edtPref.putString("userPass", pass);
                    edtPref.commit();

                    toastText = "Account Created successfully!";

                    back = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(back);
                    finish();
                }
                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
                //TODO: save data to sharedPreferences
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        back = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(back);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.red));

        TextView back = findViewById(R.id.registerCaret);
        back.setOnClickListener(this);

        Button register = findViewById(R.id.registerBtn);
        register.setOnClickListener(this);
    }
}