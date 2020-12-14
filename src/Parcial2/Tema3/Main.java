/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema3;

/**
 *
 * @author Martín
 */
public class Main {
    public static void main(String[] args) {
        //Creación de instancias
        Control ctrl = new Control(/*capacidad atracción*/ 5);
        MontañaRusa mt = new MontañaRusa(ctrl, 3);
        Pasajero[] pasajeros = new Pasajero[17];
        for (int i = 0; i < pasajeros.length; i++) {
            pasajeros[i] = new Pasajero(ctrl);
        }
        
        //Creación de hilos
        Thread hiloMt = new Thread(mt, "Montaña Rusa");
        Thread[] hilosPas = new Thread[pasajeros.length];
        for (int i = 0; i < hilosPas.length; i++) {
            hilosPas[i] = new Thread(pasajeros[i], "Pasajero "+i);
        }
        //Start de hilos
        hiloMt.start();
        for (int i = 0; i < hilosPas.length; i++) {
            hilosPas[i].start();
        }
    }
}
