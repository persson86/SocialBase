package com.project2016.lfspersson.socialbasetest1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaInscritos extends Activity {

    ProgressBar progressBar;
    ListView listView;
    Firebase inscritosRef;
    final ArrayList<String> list = new ArrayList<String>();
    final ArrayList<String> listComp = new ArrayList<String>();
    private static final int VERSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_inscritos);

        configScreen();
        Firebase.setAndroidContext(this);
        loadAsyncTask();
    }

    public void configScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void loadAsyncTask() {

        listView = (ListView) findViewById(R.id.listInscritos);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new AsyncTask<String, Integer, Integer>() {
            @Override
            protected Integer doInBackground(String... params) {

               /* try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                getInscritos();

                return 1;
            }

            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
            }

            @Override
            protected void onPostExecute(Integer integer) {
            }

        }.execute();
    }

    public void getInscritos() {

        String urlInscritos = "https://socialbasetest1.firebaseio.com/inscritos/";
        inscritosRef = new Firebase(urlInscritos);

        inscritosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("LF", dataSnapshot.getValue().toString());

                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                    Inscrito inscrito = Snapshot.getValue(Inscrito.class);

                    String nome = inscrito.getNome();
                    String email = inscrito.getEmail();

                    list.add(nome);
                    String line = nome + "-" + email;
                    listComp.add(line);

                }

                progressBar.setVisibility(View.GONE);
                loadListScreen();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void loadListScreen() {

        saveListDB();

        final ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        listView.setVisibility(View.VISIBLE);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String line = listComp.get(position).toString();

                String[] parts = line.split("-");
                String nomeLine = parts[0];
                String emailLine = parts[1];

                Intent it;
                Bundle bundle = new Bundle();
                bundle.putString("nome", nomeLine);
                bundle.putString("email", emailLine);

                it = new Intent(getApplicationContext(), InscritoDetalhe.class);
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }

    public void saveListDB() {

        SQLiteDatabase db =
                new SQLiteHelper(this, "inscritos.db", null, VERSION)
                        .getWritableDatabase();

        deleteDuplicates();

        for (int i = 0; i < listComp.size(); i++) {
            String line = listComp.get(i).toString();

            String[] parts = line.split("-");
            String nomeLine = parts[0];
            String emailLine = parts[1];

            ContentValues values = new ContentValues();
            values.put(InscritosDB.NOME, nomeLine);
            values.put(InscritosDB.EMAIL, emailLine);

            long rowId = db.insert(InscritosDB.TABLENAME, null, values);

        }

        //print(db);


    }

    private void print(SQLiteDatabase db) {
        Cursor c = db.query(InscritosDB.TABLENAME, new String[]{InscritosDB.ID, InscritosDB.NOME, InscritosDB.EMAIL},
                null, null, null, null, null);

        while (c.moveToNext()) {
            InscritosDB a = new InscritosDB(c);

            //Log.i("ECO", a.toString());
        }

        //Log.i("ECO", " ---------------------------------------------------- ");

        db.close();
    }

    public void deleteDuplicates(){
        SQLiteDatabase db =
                new SQLiteHelper(this, "inscritos.db", null, VERSION)
                        .getWritableDatabase();

        db.delete(InscritosDB.TABLENAME, null, null);
        //print(db);
    }

}
