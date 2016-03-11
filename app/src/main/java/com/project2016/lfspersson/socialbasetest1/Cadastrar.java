package com.project2016.lfspersson.socialbasetest1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class Cadastrar extends Activity {

    EditText etNome, etEmail;
    Button btnCad;
    String nome, email;

    Firebase inscritosRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Firebase.setAndroidContext(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onClick_btnCadastrar(View view) {
        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnCad = (Button) findViewById(R.id.btnCadastrar);

        nome = etNome.getText().toString();
        email = etEmail.getText().toString();

        String urlInscritos = "https://socialbasetest1.firebaseio.com/inscritos/";
        inscritosRef = new Firebase(urlInscritos);

        Firebase newInscritosRef = inscritosRef.push();

        Map<String, String> inscritosMap = new HashMap<String, String>();
        inscritosMap.put("nome", nome);
        inscritosMap.put("email", email);
        newInscritosRef.setValue(inscritosMap);

        String postInsc = newInscritosRef.getKey();
        if (postInsc != null) {
            Toast.makeText(this, "Cadastro efetuado com successo!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Aconteceu algum erro, por favor tente novamente", Toast.LENGTH_SHORT).show();
        }


    }
}
