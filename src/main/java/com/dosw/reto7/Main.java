package com.dosw.reto7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Luz luz = new Luz();
        Puerta puerta = new Puerta();
        Musica musica = new Musica();
        Persiana persiana = new Persiana();
        ControlRemotoMagico control = new ControlRemotoMagico();
        
        System.out.println("Numero de acciones a registrar: ");
        int numAcciones = sc.nextInt();
        sc.nextLine();
        System.out.println();
        
        for (int i = 1; i <= numAcciones; i++) {
            System.out.println("Accion " + i + ":");
            System.out.println("Usuario: ");
            String usuario = sc.nextLine();
            
            System.out.println("Seleccione: 1. Encender luz 2. Abrir puerta 3. Reproducir musica 4. Ajustar volumen");
            int opcion = sc.nextInt();
            sc.nextLine();
            
            int valor = 0;
            String descripcion = "";
            Command comando = null;
            
            if (opcion == 4) {
                System.out.println("Ingrese valor (0-100): ");
                valor = sc.nextInt();
                sc.nextLine();
                comando = new AjustarVolumenCommand(musica, valor);
                descripcion = "Ajustar volumen a " + valor + "%";
                System.out.println("Accion " + i + " ejecutada por " + usuario + ": Volumen ajustado a " + valor + "%");
            } else if (opcion == 1) {
                comando = new EncenderLuzCommand(luz);
                descripcion = "Encender luz";
                System.out.println("Accion " + i + " ejecutada por " + usuario + ": Luz encendida");
            } else if (opcion == 2) {
                comando = new AbrirPuertaCommand(puerta);
                descripcion = "Abrir puerta";
                System.out.println("Accion " + i + " ejecutada por " + usuario + ": Puerta abierta");
            } else if (opcion == 3) {
                comando = new ReproducirMusicaCommand(musica);
                descripcion = "Reproducir musica";
                System.out.println("Accion " + i + " ejecutada por " + usuario + ": Musica reproducida");
            }
            
            control.ejecutarAccionSilenciosa(usuario, comando, descripcion, i);
            
            System.out.println("Deshacer accion? (si/no): ");
            String deshacer = sc.nextLine();
            if (deshacer.equalsIgnoreCase("si")) {
                control.deshacerUltimaAccion(i, valor);
            }
            System.out.println();
        }
        
        control.mostrarHistorialCompleto();
        control.mostrarInvestigacion();
        
        sc.close();
    }
}
