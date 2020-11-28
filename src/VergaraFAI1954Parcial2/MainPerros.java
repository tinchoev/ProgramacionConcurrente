/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VergaraFAI1954Parcial2;

/**
 *
 * @author Martín
 */
public class MainPerros {
    public static void main(String[] args) {
        //Creación de instancias
        int cantidadDePlatos = 3, cantidadDePerros = 10;
        Comedor com = new Comedor(cantidadDePlatos);
        Encargado enc = new Encargado(com);
        Perro[] colPerros = new Perro[cantidadDePerros];
        for (int i = 0; i < colPerros.length; i++) {
            colPerros[i] = new Perro(com);
        }
        //Creación de hilos
        Thread hiloEnc = new Thread(enc, "Encargado");
        Thread[] hilosPerros = new Thread[cantidadDePerros];
        for (int i = 0; i < hilosPerros.length; i++) {
            hilosPerros[i] = new Thread(colPerros[i], "Perro "+i);
        }
        //Start de hilos
        hiloEnc.start();
        for (int i = 0; i < hilosPerros.length; i++) {
            hilosPerros[i].start();
        }
    }
}
