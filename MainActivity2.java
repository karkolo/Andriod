package com.example.karol.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList<ModelOceny> mDane = new ArrayList<ModelOceny>();
    private ListView mLista;
    private ArrayAdapter<ModelOceny> mAdapter;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    int wartosc = 0;
    ModelOceny m;
    public int liczba = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        Bundle tobolek = getIntent().getExtras();
        liczba = tobolek.getInt("liczba ocen");

        mLista = (ListView) findViewById(R.id.lista);
        mAdapter = new WlasnyAdapter(this, mDane);
        mLista.setAdapter(mAdapter);
        Button gotowePrzycisk = (Button) findViewById(R.id.gotowe);

        for (int i = 1; i <= liczba; i++) {
            mDane.add(new ModelOceny("ocena " + i, 2));
        }

        gotowePrzycisk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (ModelOceny m : mDane) {
                            wartosc += m.getOcena();
                        }
                        Intent i = new Intent(MainActivity2.this, MainActivity.class);
                        i.putExtra("wartosc radio", wartosc);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                }
        );

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int oceny[] = savedInstanceState.getIntArray("oceny");

        for (int i = 0; i < liczba; i++) {
            mDane.get(i).setOcena(oceny[i]);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {


        int oceny[] = new int[liczba];
        for (int i = 0; i < liczba; i++) {
            oceny[i] = mDane.get(i).getOcena();
        }
        outState.putIntArray("oceny", oceny);
        super.onSaveInstanceState(outState);
    }
}



