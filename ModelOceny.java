package com.example.karol.myapplication;

/**
 * Created by Karol on 13.03.2018.
 */

class ModelOceny {
    private String nazwa;
    private int ocena;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public ModelOceny(String nazwa, int ocena) {
        this.nazwa = nazwa;
        this.ocena = ocena;
    }
}
