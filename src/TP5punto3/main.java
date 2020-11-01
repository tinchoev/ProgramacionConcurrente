/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class main {
    public static void main(String[] args) {
        //Creación instancias
        int capacidadTren = 6;
        ControlTren unControl = new ControlTren(capacidadTren);
        VendedorTickets unVendedor = new VendedorTickets(unControl);
        Tren unTren = new Tren(unControl);
        Pasajero[] colPasajeros = new Pasajero[capacidadTren*2];
        for (int i = 0; i < colPasajeros.length; i++) {
            colPasajeros[i] = new Pasajero(unControl);
        }
        
        //Creación hilos
        Thread hiloVendedor = new Thread(unVendedor, "Vendedor");
        Thread hiloTren = new Thread(unTren, "Tren");
        Thread[] hilosPasajeros = new Thread[colPasajeros.length];
        for (int i = 0; i < hilosPasajeros.length; i++) {
            hilosPasajeros[i] = new Thread(colPasajeros[i], "Pasajero "+i);
        }
        
        //Start de hilos
        hiloVendedor.start();
        hiloTren.start();
        for (int i = 0; i < hilosPasajeros.length; i++) {
            hilosPasajeros[i].start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
