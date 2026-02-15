package com.dosw.reto7;

public class AbrirPuertaCommand implements Command {
    private Puerta puerta;

    public AbrirPuertaCommand(Puerta puerta) {
        this.puerta = puerta;
    }

    @Override
    public void execute() {
        puerta.abrir();
    }

    @Override
    public void undo() {
        puerta.cerrar();
    }
}
