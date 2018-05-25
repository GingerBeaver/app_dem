package com.example.sebastien.demenagement;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AppCompatActivity {
    EditText login, password;
    String hash;
    MyDBHandler dbhandler = new MyDBHandler();
    Statement st;
    ResultSet res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // A étudier parce que je sais pas ce que s'est mais sans ça pas d'accés à la base distante
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            dbhandler.loadDriver();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void userConnection(View v) {
        login = findViewById(R.id.editLogin);
        password = findViewById(R.id.editPassword);
        try {
            hash = SHA1.encrypt(password.getText().toString());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            Connection cnx = dbhandler.newConnection();
            st = cnx.createStatement();
            res = st.executeQuery("SELECT * FROM users");
            while (res.next()) {
                if (login.getText().toString().equals(res.getString(1))) {
                    if (hash.equals(res.getString(2))) {
                        Toast.makeText(this,"Connexion réussie", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this,"Mot de passe incorrect", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this,"Identifiant incorrect", Toast.LENGTH_LONG).show();
                }
            }
            res.close();
            st.close();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newUser(View v) {
        Intent intent = new Intent(Login.this, NewUser.class);
        startActivity(intent);
    }
}
