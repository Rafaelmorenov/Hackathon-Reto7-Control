package com.dosw.reto7;

public class DetenerMusicaCommand implements Command {
    private Musica musica;

    public DetenerMusicaCommand(Musica musica) {
        this.musica = musica;
    }

    @Override
    public void execute() {
        musica.detener();
    }

    @Override
    public void undo() {
        musica.reproducir();
    }
}
