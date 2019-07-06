package com.example.securepassword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private TextView showPasswordHashTv;
    String passwordToHash;
    private SaltedMD5 saltedMD5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText passwordEd = findViewById(R.id.passwordEd);
        Button checkHashBtn = findViewById(R.id.checkHashBtn);
        showPasswordHashTv = findViewById(R.id.showPasswordHashTv);
        saltedMD5 = new SaltedMD5();
        if (passwordEd != null && passwordEd.getText().toString().equals("")) {
            passwordToHash = passwordEd.getText().toString();
        }
        checkHashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    byte[] salt = saltedMD5.getSalt();
                    String securePassword = saltedMD5.getSecurePassword(passwordToHash, salt);
                    System.out.println("---------generate-------" + securePassword); //Prints 83ee5baeea20b6c21635e4ea67847f66
                    String regeneratedPasswordToVerify = saltedMD5.getSecurePassword(passwordToHash, salt);
                    System.out.println("--------regenerate-----------" + regeneratedPasswordToVerify); //Prints 83ee5baeea20b6c21635e4ea67847f66
                    showPasswordHashTv.setText(securePassword);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
