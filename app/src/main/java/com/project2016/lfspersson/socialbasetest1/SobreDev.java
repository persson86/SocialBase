package com.project2016.lfspersson.socialbasetest1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.client.HttpClient;
import org.shaded.apache.http.client.methods.HttpGet;
import org.shaded.apache.http.util.EntityUtils;

import java.io.IOException;

public class SobreDev extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_dev);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onCLick_Linkedin(View view) {
        ImageView img = (ImageView) findViewById(R.id.imgLinkedin);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.linkedin.com/in/luizfelipepersson"));
                startActivity(intent);
            }
        });
    }

    public void onCLick_Github(View view) {
        ImageView img = (ImageView) findViewById(R.id.imgGithub);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/persson86/SocialBaseTest1"));
                startActivity(intent);
            }
        });
    }

    public void onCLick_Instagram(View view) {
        ImageView img = (ImageView) findViewById(R.id.imgInstagram);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/felipepersson/"));
                startActivity(intent);
            }
        });
    }
}

