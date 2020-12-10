/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Control {
    private Semaphore semTrans;
    private Semaphore semIr;
    private Semaphore semVolver;
    private Semaphore semBajar;
    private Semaphore semSubir;
    private Semaphore mutex;
    private int capacidadTrans;
    private int cantidadAutos;
    
    public Control(int unaCapacidad) {
        semTrans = new Semaphore(0);
        semIr = new Semaphore(0);
        semVolver = new Semaphore(0);
        semSubir = new Semaphore(1);
        semBajar = new Semaphore(0);
        mutex = new Semaphore(1);
        capacidadTrans = unaCapacidad;
        cantidadAutos = 0;
    }
    //PARTE DEL AUTO
    public void subir() {
        try {
            semSubir.acquire();
            semTrans.acquire();
            System.out.println(Thread.currentThread().getName()+": subió al transbordador");
            cantidadAutos++;
            if (cantidadAutos == capacidadTrans) {
                semIr.release();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            semSubir.release();
        }
    }
    
    public void bajar() {
        try {
            semBajar.acquire();
            mutex.acquire();
            System.out.println(Thread.currentThread().getName()+": baja del transbordador");
            cantidadAutos--;
            if (cantidadAutos == 0) {
                semVolver.release();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mutex.release();
        }
    }
    //PARTE DEL TRANSBORDADOR
    public void ir() {
        semTrans.release(capacidadTrans);
        try {
            semIr.acquire();
            System.out.println(Thread.currentThread().getName()+": empieza viaje");
        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void volver() {
        semBajar.release(capacidadTrans);
        try {
            semVolver.acquire();
            System.out.println(Thread.currentThread().getName()+": vuelve del viaje");
        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
