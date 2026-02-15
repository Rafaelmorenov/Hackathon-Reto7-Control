package com.dosw.reto7;

public class Persiana {
    private int posicion = 0;

    public void subir(int nivel) {
        this.posicion = nivel;
    }

    public void bajar(int nivel) {
        this.posicion = nivel;
    }

    public int getPosicion() {
        return posicion;
    }
}
