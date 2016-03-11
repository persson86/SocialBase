package com.project2016.lfspersson.socialbasetest1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import junit.framework.Test;

public class MainActivity extends Activity {

    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configScreen();

    }

    public void configScreen(){
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onClick_btnConsultarInscritos(View view) {
        it = new Intent(this, ListaInscritos.class);
        startActivity(it);
    }

    public void onClick_btnSobreDev(View view) {
        it = new Intent(this,SobreDev.class);
        startActivity(it);
    }
}
