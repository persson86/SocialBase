package com.project2016.lfspersson.socialbasetest1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class InscritoDetalhe extends Activity {

    String nome, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrito_detalhe);

        configScreen();
        getDetail();
    }

    public void configScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void getDetail() {
        Bundle b = getIntent().getExtras();

        nome = b.getString("nome");
        email = b.getString("email");

        TextView tvNome = (TextView) findViewById(R.id.tvNome);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);

        tvNome.setText(nome);
        tvEmail.setText(email);
    }
}
