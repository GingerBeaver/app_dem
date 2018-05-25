package com.example.sebastien.demenagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AppCompatActivity {
    private String hash;
    private MyDBHandler dbhandler = new MyDBHandler();
    private static final String FILENAME = "CURRENT_USER";

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
        // Vérification de la présence du fichier CURRENT_USER et si c'est le cas, on passe l'identification
        File f = new File("/data/data/" + getPackageName() +  "/shared_prefs/" + FILENAME + ".xml");
        if (f.exists()) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void userConnection(View v) {
        EditText login, password;
        Statement st;
        ResultSet res;
        SharedPreferences.Editor editor;
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
                        // Création d'un fichier SharedPreferences pour l'utilisateur authentifié
                        editor = getSharedPreferences("CURRENT_USER", MODE_PRIVATE).edit();
                        editor.putInt("num_adr", res.getInt(3));
                        editor.putString("adress", res.getString(4));
                        editor.putString("cplt_adr", res.getString(5));
                        editor.putInt("zip_code", res.getInt(6));
                        editor.putString("city", res.getString(7));
                        editor.putString("name", res.getString(8));
                        editor.putString("fname", res.getString(9));
                        editor.putString("email", res.getString(10));
                        editor.putString("birth", res.getString(11));
                        editor.putString("sex", res.getString(12));
                        editor.putInt("profil", res.getInt(13));
                        editor.apply();
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
