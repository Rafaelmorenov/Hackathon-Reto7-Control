package com.dosw.reto7;

public class AjustarPersianaCommand implements Command {
    private Persiana persiana;
    private int nuevaPosicion;
    private int posicionAnterior;

    public AjustarPersianaCommand(Persiana persiana, int posicion) {
        this.persiana = persiana;
        this.nuevaPosicion = posicion;
    }

    @Override
    public void execute() {
        posicionAnterior = persiana.getPosicion();
        persiana.subir(nuevaPosicion);
    }

    @Override
    public void undo() {
        persiana.subir(posicionAnterior);
    }
}
