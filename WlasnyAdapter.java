package com.example.karol.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol on 13.03.2018.
 */

public class WlasnyAdapter extends ArrayAdapter<ModelOceny> {
    private List<ModelOceny> listaOcen;
    private Activity kontekst;

    public WlasnyAdapter(Activity kontekst, List<ModelOceny> listaOcen) {
        super(kontekst, R.layout.radio_buttons, listaOcen);
        this.listaOcen = listaOcen;
    }

    //tworzenie nowego wiersza
    @Override
    public View getView(final int numerWiersza, View widokDoRecyklingu, ViewGroup parent) {
        ModelOceny model = getItem(numerWiersza);

        if (widokDoRecyklingu == null) {
            widokDoRecyklingu = LayoutInflater.from(getContext()).inflate(R.layout.radio_buttons, parent, false);
        }
        final RadioGroup grupaOceny = (RadioGroup) widokDoRecyklingu.findViewById(R.id.radioGroup);

        RadioButton radio2 = (RadioButton) widokDoRecyklingu.findViewById(R.id.radioButton);
        RadioButton radio3 = (RadioButton) widokDoRecyklingu.findViewById(R.id.radioButton2);
        RadioButton radio4 = (RadioButton) widokDoRecyklingu.findViewById(R.id.radioButton3);
        RadioButton radio5 = (RadioButton) widokDoRecyklingu.findViewById(R.id.radioButton4);

        TextView etykieta = (TextView) widokDoRecyklingu.findViewById(R.id.textView4);
        etykieta.setText(model.getNazwa());
        grupaOceny.setTag(listaOcen.get(numerWiersza));
        switch (model.getOcena()) {
            case 3:
                radio3.setChecked(true);
                break;
            case 4:
                radio4.setChecked(true);
                break;
            case 5:
                radio5.setChecked(true);
                break;
            default:
                radio2.setChecked(true);
                break;
        }

        grupaOceny.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) //referencja do grupy przyciskow
                    {
                        ModelOceny model = (ModelOceny) group.getTag();
                        switch (checkedId) {
                            case R.id.radioButton:
                                model.setOcena(2);
                                break;
                            case R.id.radioButton2:
                                model.setOcena(3);
                                break;
                            case R.id.radioButton3:
                                model.setOcena(4);
                                break;
                            case R.id.radioButton4:
                                model.setOcena(5);
                                break;
                        }
                    }
                }
        );

        return widokDoRecyklingu;
    }
}

