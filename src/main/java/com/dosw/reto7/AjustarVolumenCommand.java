package com.dosw.reto7;

public class AjustarVolumenCommand implements Command {
    private Musica musica;
    private int nuevoVolumen;
    private int volumenAnterior;

    public AjustarVolumenCommand(Musica musica, int volumen) {
        this.musica = musica;
        this.nuevoVolumen = volumen;
    }

    @Override
    public void execute() {
        volumenAnterior = musica.getVolumen();
        musica.ajustarVolumen(nuevoVolumen);
    }

    @Override
    public void undo() {
        musica.ajustarVolumen(volumenAnterior);
    }
}
