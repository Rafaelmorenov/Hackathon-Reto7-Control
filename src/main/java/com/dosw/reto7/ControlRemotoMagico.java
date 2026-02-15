package com.dosw.reto7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlRemotoMagico {
    private List<RegistroAccion> historial = new ArrayList<>();

    public void ejecutarAccionSilenciosa(String usuario, Command comando, String descripcion, int numAccion) {
        RegistroAccion registro = new RegistroAccion(usuario, comando, descripcion);
        registro.setNumAccion(numAccion);
        historial.add(registro);
    }

    public void deshacerUltimaAccion(int numAccion, int valorAnterior) {
        if (!historial.isEmpty()) {
            RegistroAccion registro = historial.get(historial.size() - 1);
            registro.getComando().undo();
            registro.setDeshecha(true);
            
            if (registro.getDescripcion().contains("Volumen")) {
                System.out.println("Accion " + numAccion + " deshecha: Volumen regresado a 0%");
            } else if (registro.getDescripcion().contains("Reproducir musica")) {
                System.out.println("Accion " + numAccion + " deshecha: Musica detenida");
            } else if (registro.getDescripcion().contains("Encender luz")) {
                System.out.println("Accion " + numAccion + " deshecha: Luz apagada");
            } else if (registro.getDescripcion().contains("Abrir puerta")) {
                System.out.println("Accion " + numAccion + " deshecha: Puerta cerrada");
            }
        }
    }

    public void mostrarHistorialCompleto() {
        System.out.println("Historial completo");
        for (int i = 0; i < historial.size(); i++) {
            RegistroAccion registro = historial.get(i);
            String estado = registro.isDeshecha() ? " (deshecha)" : "";
            System.out.println((i + 1) + ": " + registro.getDescripcion() + " - Usuario: " + registro.getUsuario() + estado);
        }
        System.out.println();
    }

    public void mostrarInvestigacion() {
        System.out.println("--- Investigando quien desconfiguro los electrodomesticos");
        Map<String, Integer> conteoUsuarios = new HashMap<>();
        
        for (RegistroAccion registro : historial) {
            String usuario = registro.getUsuario();
            conteoUsuarios.put(usuario, conteoUsuarios.getOrDefault(usuario, 0) + 1);
        }
        
        for (Map.Entry<String, Integer> entry : conteoUsuarios.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println("Se detecta que " + entry.getKey() + " realizo " + entry.getValue() + " acciones que alteraron la configuracion.");
            } else {
                System.out.println(entry.getKey() + " realizo " + entry.getValue() + " accion.");
            }
        }
    }
}
