package com.example.sharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailSrc, passSrc;
    private CheckBox remember;
    private Toast error;
    private String email, password, toastText, prefName = "data";

    private SharedPreferences db;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createAccount:
                Intent toRegister =  new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(toRegister);
                finish();
                break;
            case R.id.singInBtn:
                //TODO: input validation
                emailSrc = findViewById(R.id.emailTxt);
                passSrc = findViewById(R.id.passwordTxt);

                email = emailSrc.getText().toString();
                password = passSrc.getText().toString();

                if (email.equals("") || !email.contains("@")){ toastText = "Please enter a valid email"; }
                else if (password.equals("")) { toastText = "Please enter a password"; }
                else {
                    //TODO: login Validation
                    db = getSharedPreferences(prefName, MODE_PRIVATE);
                    if (db.contains("userEmail")){
                        String emailStored = db.getString("userEmail", null);
                        String passStored = db.getString("userPass", null);
                        if (email.equals(emailStored)){
                            if (password.equals(passStored)){
                                //login Successful
                                remember = findViewById(R.id.rememberCheckBox);
                                if (remember.isChecked()) {
                                    SharedPreferences.Editor edtPref = db.edit();
                                    edtPref.putBoolean("userLoggedIn", true);
                                    edtPref.commit();
                                }

                                toastText = "Login Successful";

                                Intent dash = new Intent(MainActivity.this, DashActivity.class);
                                startActivity(dash);
                                finish();
                            }else {
                                toastText = "Invalid Password!";
                            }
                        }else {
                            toastText = "Invalid Email!";
                        }


                    }else {
                        toastText = "No Account found! Please create an account first!";
                    }
                }

                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //exit alertBox
        AlertDialog.Builder exit = new AlertDialog.Builder(MainActivity.this);
        exit.setTitle("Exit?")
                .setMessage("Do you really want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .create()
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = getSharedPreferences(prefName, MODE_PRIVATE);

        if (db.contains("userLoggedIn") && db.getBoolean("userLoggedIn", false)){

            Intent dash = new Intent(MainActivity.this, DashActivity.class);
            startActivity(dash);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#200c53"));

        TextView createBtn = findViewById(R.id.createAccount);
        createBtn.setOnClickListener(this);

        Button sigIn = findViewById(R.id.singInBtn);
        sigIn.setOnClickListener(this);
    }
}