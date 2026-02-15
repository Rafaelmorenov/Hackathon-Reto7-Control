package com.dosw.reto7;

public class ReproducirMusicaCommand implements Command {
    private Musica musica;

    public ReproducirMusicaCommand(Musica musica) {
        this.musica = musica;
    }

    @Override
    public void execute() {
        musica.reproducir();
    }

    @Override
    public void undo() {
        musica.detener();
    }
}
