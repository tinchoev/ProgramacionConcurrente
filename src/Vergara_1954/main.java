/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vergara_1954;

/**
 *
 * @author Martín
 */
public class main {
    public static void main(String [] args) {
        //Creación de instancia de la clase compartida
        Tren tren = new Tren();
        //Creación de instancias de las clases que implementan Runnable
        ControlTren ctrlTren = new ControlTren(tren);
        VendedorTickets vendedor = new VendedorTickets(tren);
        Pasajero[] pasajeros = new Pasajero[12];
        for (int i=0; i<pasajeros.length; i++) {
            pasajeros[i] = new Pasajero(tren);
        }
        //Creación de hilos
        Thread controlTren = new Thread(ctrlTren);
        Thread vendedorTickets = new Thread(vendedor);
        Thread[] hilos = new Thread[14];
        hilos[0] = controlTren;
        hilos[1] = vendedorTickets;
        for (int i=2; i<hilos.length; i++) {
            hilos[i] = new Thread(pasajeros[i-2]);
        }
        //Inicio de hilos
        for (int i=0; i<hilos.length; i++) {
            hilos[i].start();
        }
    }
}
