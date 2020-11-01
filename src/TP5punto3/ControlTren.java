/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5punto3;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class ControlTren {
    int capacidadTren;
    private Semaphore semTren;
    private Semaphore semVendedor;
    private Semaphore semPasajero;
    private Semaphore semPaso1;
    private Semaphore semPaso2;
    private Semaphore semPaso3;
    private Semaphore semRecorrer;
    
    public ControlTren(int unaCapacidad) {
        capacidadTren = unaCapacidad;
        semTren = new Semaphore(capacidadTren, true);
        semVendedor = new Semaphore(1, true);
        semPasajero = new Semaphore(0, true);
        semPaso1 = new Semaphore(1, true);
        semPaso2 = new Semaphore(0, true);
        semPaso3 = new Semaphore(0, true);
        semRecorrer = new Semaphore(0, true);
    }
    
    //Parte de pasajeros
    public void comprarTicket() {
        try {
            semPaso1.acquire();
            semVendedor.acquire();
            System.out.println(Thread.currentThread().getName()+": comprando ticket");
            semPasajero.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void subirTren() {
        try {
            semPaso2.acquire();
            System.out.println(Thread.currentThread().getName()+": esperando para subir al tren");
            if (semTren.tryAcquire()) {
                System.out.println(Thread.currentThread().getName()+": me subí al tren");
                if(semTren.availablePermits()==0){
                    System.out.println("Tren lleno");
                    semRecorrer.release();
                }
            } else {
                esperarTren();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void esperarTren(){
        semPaso2.release();
        System.out.println(Thread.currentThread().getName()+": tren no disponible, entonces espero");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTren.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            subirTren();
        }
    }
    
    public void bajarTren() {
        try {
            semPaso3.acquire();
            System.out.println(Thread.currentThread().getName()+": me bajo del tren");
            semTren.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Parte del Vendedor
    public void venderTicket() {
        try {
            semPasajero.acquire();
            System.out.println("Vendedor: atendiendo cliente");
            Thread.sleep(3000);
            System.out.println("Vendedor: terminé de atender");
            semVendedor.release();
            semPaso1.release();
            semPaso2.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Parte del Tren
    public void iniciarRecorrido() {
        try {
            semRecorrer.acquire();
            System.out.println("Tren empieza recorrido");
            Thread.sleep(5000);
            System.out.println("Tren termina recorrido");
            semPaso3.release(capacidadTren);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
