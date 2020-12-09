/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VergaraFAI1954Parcial2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Taller {
    private Semaphore semCrearManga;
    private Semaphore semCrearCuerpo;
    private Semaphore semCrearSueter;
    private Semaphore semUsarManga;
    private Semaphore semUsarCuerpo;
    private int cajasDeSueters;//cajas llenas de sueters
    private int numMaxSueters;
    
    public Taller(int maxMangas, int maxCuerpos, int maxSueters) {
        semCrearManga = new Semaphore(maxMangas);
        semCrearCuerpo = new Semaphore(maxCuerpos);
        semCrearSueter = new Semaphore(maxSueters);
        semUsarManga = new Semaphore(0);
        semUsarCuerpo = new Semaphore(0);
        numMaxSueters = maxSueters;
    }
    
    private void espera() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearManga() {
        try {
            //Toma un permiso, que simula crear y depositar una manga en el canasto
            semCrearManga.acquire();
            System.out.println(Thread.currentThread().getName()+": crea manga");
            semUsarManga.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearCuerpo() {
        try {
            //Toma un permiso, que simula crear y depositar un cuepo en el canasto
            semCrearCuerpo.acquire();
            System.out.println(Thread.currentThread().getName()+": crea cuerpo");
            semUsarCuerpo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearSueter() {
        try {
            //Toma las dos mangas y el cuerpo
            semUsarManga.acquire(2);
            semCrearManga.release(2);
            semUsarCuerpo.acquire(1);
            semCrearCuerpo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Libera un permiso, que simula un sueter
        if (semCrearSueter.tryAcquire()) {
            System.out.println(Thread.currentThread().getName()+": crea sueter");
        } else {
            chequeoSueters();
        }
    }
    
    private void chequeoSueters() {
        //Si se llena la caja, actualiza el contador de las mismas y la cantidad de permisos
        this.cajasDeSueters++;
        System.out.println("Se actualiza la cantidad de cajas de sueters");
        semCrearSueter.release(numMaxSueters);
        try {
            semCrearSueter.acquire();
            System.out.println(Thread.currentThread().getName()+": crea sueter");
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getCantidadDeCajas() {
        //Retorna la cantidad de cajas llenas de sueters
        return cajasDeSueters;
    }
}
