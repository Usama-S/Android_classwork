package com.example.sharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaDescrambler;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.MissingFormatArgumentException;

public class DashActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences db;
    private SharedPreferences.Editor edtPref;
    private String prefName = "data";
    private Intent logout;


    @Override
    public void onClick(View v) {
        db = getSharedPreferences(prefName, MODE_PRIVATE);
        edtPref = db.edit();
        switch (v.getId()){
            case R.id.logoutBtn:
                //remove logged in status
                //show alert
                AlertDialog.Builder build = new AlertDialog.Builder(DashActivity.this);
                build.setTitle("Log out?")
                        .setMessage("Are you sure you want to Log out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                edtPref.remove("userLoggedIn");
                                edtPref.commit();

                                logout = new Intent(DashActivity.this, MainActivity.class);
                                startActivity(logout);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { }
                        })
                        .create()
                        .show();
                break;
            case R.id.deleteAccountBtn:

                //show alert
                AlertDialog.Builder delete = new AlertDialog.Builder(DashActivity.this);
                delete.setTitle("Remove Account?")
                        .setMessage("Are you sure you want to delete your account?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //delete data upon confirmation
                                edtPref.remove("userLoggedIn");
                                edtPref.remove("userName");
                                edtPref.remove("userEmail");
                                edtPref.remove("userPhone");
                                edtPref.remove("userPass");
                                edtPref.commit();

                                logout = new Intent(DashActivity.this, MainActivity.class);
                                startActivity(logout);
                                finish();

                                Toast.makeText(DashActivity.this, "Account deleted successfully!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { }
                        })
                        .create()
                        .show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (db.getBoolean("userLoggedIn", false)) {
            AlertDialog.Builder exit = new AlertDialog.Builder(DashActivity.this);
            exit.setTitle("Exit?")
                    .setMessage("Are you sure you want to Exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create()
                    .show();
        }else {
            AlertDialog.Builder exit = new AlertDialog.Builder(DashActivity.this);
            exit.setTitle("Log Out?")
                    .setMessage("Are you sure you want to Exit? You will be logged out!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create()
                    .show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        Button logout = findViewById(R.id.logoutBtn);
        logout.setOnClickListener(this);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.red));

        Button deleteAccount = findViewById(R.id.deleteAccountBtn);
        deleteAccount.setOnClickListener(this);

        db = getSharedPreferences(prefName, MODE_PRIVATE);
        String userName = db.getString("userName", null);

        TextView userNameTxt = findViewById(R.id.dashUserName);
        userNameTxt.setText("Hello, " + userName);
    }

}