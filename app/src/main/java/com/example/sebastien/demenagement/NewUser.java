package com.example.sebastien.demenagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class NewUser extends AppCompatActivity {
    private MyDBHandler dbhandler = new MyDBHandler();
    private EditText login, password, checkpwd, email, num_ad, adress, cplt_ad, zip, city, name, fname;
    private TextView birth;
    private ArrayAdapter<CharSequence> adapter;
    private Spinner spin_gender, spin_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        // Assignation premier layout
        login = findViewById(R.id.editLogin);
        password = findViewById(R.id.editPassword);
        checkpwd = findViewById(R.id.editPassword2);
        email = findViewById(R.id.editMail);
    }

    public void nextUserLayout(View v) {
        // Batterie de vérification Login/Password/Email
        if (dbhandler.checkUser(login.getText().toString())) {
            Toast.makeText(this,"Le nom d'utilisateur \"" +login.getText()+ "\" est déjà utilisé",Toast.LENGTH_LONG).show();
        } else if (login.getText().toString().isEmpty()) {
            Toast.makeText(this,"Le champ \"nom d'utilisateur\" ne peut être vide",Toast.LENGTH_LONG).show();
        }else if (!password.getText().toString().equals(checkpwd.getText().toString())) {
            Toast.makeText(this,"Erreur lors de la vérification du mot de passe",Toast.LENGTH_LONG).show();
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this,"Le champ \"mot de passe\" ne peut être vide",Toast.LENGTH_LONG).show();
        } else if (!email.getText().toString().contains("@") || !email.getText().toString().contains(".")) {
            Toast.makeText(this, "Email invalide", Toast.LENGTH_LONG).show();
        } else if (dbhandler.checkMail(email.getText().toString())) {
                Toast.makeText(this,"L'email \"" +email.getText()+ "\" est déjà utilisé",Toast.LENGTH_LONG).show();
        } else {
            setContentView(R.layout.activity_new_user2);
            // Assignation second layout
            spin_gender = findViewById(R.id.gender);
            spin_type = findViewById(R.id.type);
            birth = findViewById(R.id.editBirth);
            num_ad = findViewById(R.id.editNum);
            adress = findViewById(R.id.editAdress);
            cplt_ad = findViewById(R.id.editAdress2);
            zip = findViewById(R.id.editZip);
            city = findViewById(R.id.editCity);
            name = findViewById(R.id.editName);
            fname = findViewById(R.id.editFirstname);
            // Création du spinner Sexe
            adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_gender.setAdapter(adapter);
            // Création du spinner Profil
            adapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_type.setAdapter(adapter);
            // Listener pour la saisie de la date de naissance
            birth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog(v);
                }
            });
        }
    }

    // Affichage de la box de dialogue de la date (voir DatePickerFragment)
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    // Enregistrement d'un nouvel utilisateur
    public void finishRegister(View v) throws ParseException {
        int profil;
        Date date;
        // Vérification des champs vides
        if (num_ad.getText().toString().isEmpty() || adress.getText().toString().isEmpty() || zip.getText().toString().isEmpty() ||
                city.getText().toString().isEmpty() || name.getText().toString().isEmpty() || fname.getText().toString().isEmpty() ||
                birth.getText().toString().isEmpty()) {
            Toast.makeText(this,"Champ(s) vide(s)",Toast.LENGTH_LONG).show();
        } else {
            // Transformation du profil en test binaire
            if (spin_type.toString().equals("Déménageur")) {
                profil = 0;
            } else {
                profil = 1;
            }
            // Formattage de la date pour être insérée dans la base de donnée en tant que java.sql.Date
            SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
            date = input.parse(birth.getText().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.US);
            int y = Integer.parseInt(sdf.format(date));
            sdf = new SimpleDateFormat("MM", Locale.US);
            int m = Integer.parseInt(sdf.format(date)) - 1;
            sdf = new SimpleDateFormat("dd", Locale.US);
            int d = Integer.parseInt(sdf.format(date));
            dbhandler.addUser(login.getText().toString(), password.getText().toString(), Integer.parseInt(num_ad.getText().toString()),
                    adress.getText().toString(), cplt_ad.getText().toString(), Integer.parseInt(zip.getText().toString()),
                    city.getText().toString(), name.getText().toString(),fname.getText().toString(), email.getText().toString(),
                    new java.sql.Date(new GregorianCalendar(y,m,d).getTimeInMillis()), spin_gender.getSelectedItem().toString(),profil);
            Intent intent = new Intent(NewUser.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
