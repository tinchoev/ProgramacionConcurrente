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
public class Pasajero implements Runnable{
    private Tren tren;//Recurso compartido
    
    public Pasajero(Tren trencito) {
        tren = trencito;
    }
    
    public void run() {
        System.out.println("Soy el pasajero "+Thread.currentThread().getName());
        tren.subirTren();
    }
}
