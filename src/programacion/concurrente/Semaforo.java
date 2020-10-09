/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion.concurrente;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Martín
 */
public class Semaforo {
    public static void main(String [] args) {
        Semaphore mutex = new Semaphore(1);
        System.out.println("cant permisos: "+mutex.availablePermits());
        mutex.release();
        System.out.println("cant permisos: "+mutex.availablePermits());
        mutex.release();
        System.out.println("cant permisos: "+mutex.availablePermits());
    }
}
