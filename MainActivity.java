package com.example.karol.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText imie;
    EditText nazwisko;
    EditText oceny;
    Button przycisk;
    String iValue, nValue, oValue;

    boolean isCorrect = false;
    int q, x = 0;
    int suma = 0;
    String a;
    String b;
    String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        przycisk = (Button) findViewById(R.id.button);
        przycisk.setVisibility(View.INVISIBLE);
        imie = (EditText) findViewById(R.id.imieText);
        nazwisko = (EditText) findViewById(R.id.nazwiskoText);
        oceny = (EditText) findViewById(R.id.ocenyText);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                iValue = imie.getText().toString();
                nValue = nazwisko.getText().toString();
                setButtonVisibility();
            }
        };

        TextWatcher ocenyTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    x = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    Toast grzanka = Toast.makeText(MainActivity.this, "Pole nie może być puste!", Toast.LENGTH_SHORT); //długość wyświetlania napisu
                    grzanka.show();
                    x = -1;
                }
                if ((x < 5 || x > 15)) {
                    Toast grzanka = Toast.makeText(MainActivity.this, "Podaj liczby od 5 do 15", Toast.LENGTH_SHORT); //długość wyświetlania napisu
                    grzanka.show();
                }
                if (x > 4 && x < 16) {
                    isCorrect = true;
                    oValue = oceny.getText().toString();
                    setButtonVisibility();
                } else {
                    isCorrect = false;
                    setButtonVisibility();
                }
            }
        };

        imie.addTextChangedListener(textWatcher);

        nazwisko.addTextChangedListener(textWatcher);

        oceny.addTextChangedListener(ocenyTextWatcher);

        przycisk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, MainActivity2.class);
                        i.putExtra("liczba ocen", x);
                        startActivityForResult(i, 1);
                        //finish();
                    }
                }
        );

        imie.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus && imie.getText().toString().trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "Pole nie może być puste!", Toast.LENGTH_SHORT).show(); //długość wyświetlania napisu
                        }
                    }
                }
        );

        nazwisko.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus && nazwisko.getText().toString().trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "Pole nie może być puste!", Toast.LENGTH_SHORT).show(); //długość wyświetlania napisu
                        }
                    }
                }
        );

    }

    private void setButtonVisibility() {
        if (imie.getText().toString().trim().length() > 0 && nazwisko.getText().toString().trim().length() > 0 && isCorrect) {
            przycisk.setVisibility(View.VISIBLE);
        } else {
            przycisk.setVisibility(View.INVISIBLE);
        }
    }

    protected void onActivityResult(int kodZadania, int kodWyniku, Intent dane) {
        super.onActivityResult(kodZadania, kodWyniku, dane);
        if (kodZadania == 1 && kodWyniku == RESULT_OK) {
            q = 1;
            imie.setText(iValue);
            imie.setEnabled(false);
            nazwisko.setText(nValue);
            nazwisko.setEnabled(false);
            oceny.setText(oValue);
            oceny.setEnabled(false);
            Bundle tobolek = dane.getExtras();
            imie.setText(iValue);
            nazwisko.setText(nValue);
            oceny.setText(oValue);
            float suma = tobolek.getInt("wartosc radio");
            suma = suma / x;
            suma *= 100;
            suma = Math.round(suma);
            suma /= 100;
            TextView srednia = (TextView) findViewById(R.id.textView9);
            srednia.setText("Srednia wynosi : " + suma);
            Button button = (Button) findViewById(R.id.button);

            if (suma >= 3) {
                button.setText("Super :)");

                button.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Gratulacje, zaliczyłeś!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                );
            } else {
                button.setText("Coś nie poszło :(");

                button.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Wysyłam podanie o zaliczenie warunkowe", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                );

            }

        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("MyInt", 1);
        savedInstanceState.putString("MyString", "Welcome back to Android");

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (q == 1) {
            imie.setEnabled(false);
            nazwisko.setEnabled(false);
            oceny.setEnabled(false);
        }
        double myDouble = savedInstanceState.getDouble("myDouble");
        int myInt = savedInstanceState.getInt("MyInt");
        String myString = savedInstanceState.getString("MyString");
    }
}
