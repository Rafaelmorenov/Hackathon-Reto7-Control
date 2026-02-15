package com.dosw.reto7;

public class Musica {
    private int volumen = 50;

    public void reproducir() {
    }

    public void detener() {
    }

    public void ajustarVolumen(int nivel) {
        this.volumen = nivel;
    }

    public int getVolumen() {
        return volumen;
    }
}
