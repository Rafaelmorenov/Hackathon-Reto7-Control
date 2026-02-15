package com.dosw.reto7;

public class RegistroAccion {
    private String usuario;
    private Command comando;
    private String descripcion;
    private int numAccion;
    private boolean deshecha;

    public RegistroAccion(String usuario, Command comando, String descripcion) {
        this.usuario = usuario;
        this.comando = comando;
        this.descripcion = descripcion;
        this.deshecha = false;
    }

    public String getUsuario() {
        return usuario;
    }

    public Command getComando() {
        return comando;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getNumAccion() {
        return numAccion;
    }

    public void setNumAccion(int numAccion) {
        this.numAccion = numAccion;
    }

    public boolean isDeshecha() {
        return deshecha;
    }

    public void setDeshecha(boolean deshecha) {
        this.deshecha = deshecha;
    }
}
