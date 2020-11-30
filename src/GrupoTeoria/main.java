/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrupoTeoria;

import GrupoTeoria.*;

/**
 *
 * @author Martín
 */
public class main {
    public static void main(String[] args) {
        //Creación de instancias
        Mostrador unMostrador = new Mostrador(/*pesoMaxCaja*/ 50, /*capacidadCinta*/ 10);
        Horno[] colHornos = new Horno[3];
        Empaquetador[] colEmp = new Empaquetador[2];
        Brazo unBrazo = new Brazo(unMostrador);
        colHornos[0] = new Horno(unMostrador, /*peso del pastel*/ 15);
        colHornos[1] = new Horno(unMostrador, /*peso del pastel*/ 30);
        colHornos[2] = new Horno(unMostrador, /*peso del pastel*/ 10);
        colEmp[0] = new Empaquetador(unMostrador);
        colEmp[1] = new Empaquetador(unMostrador);
        
        //Creación de hilos
        Thread[] hilosHornos = new Thread[colHornos.length];
        for (int i = 0; i < hilosHornos.length; i++) {
            hilosHornos[i] = new Thread(colHornos[i], "Horno "+i);
        }
        Thread[] hilosEmp = new Thread[colEmp.length];
        for (int i = 0; i < hilosEmp.length; i++) {
            hilosEmp[i] = new Thread(colEmp[i], "Empaquetador "+i);
        }
        Thread hiloBrazo = new Thread(unBrazo, "Brazo");
        
        //Start de hilos
        for (int i = 0; i < hilosHornos.length; i++) {
            hilosHornos[i].start();
        }
        for (int i = 0; i < hilosEmp.length; i++) {
            hilosEmp[i].start();
        }
        hiloBrazo.start();
    }
}
