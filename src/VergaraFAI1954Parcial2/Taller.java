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
    private Semaphore semMangas;
    private Semaphore semCuerpos;
    private Semaphore semSueters;
    private int numMaxMangas;//capacidad que soporta el canasto
    private int numMaxCuerpos;//capacidad que soporta el canasto
    private int numMaxSueters;//capacidad que soporta el canasto
    private int cajasDeSueters;//cajas llenas de sueters
    
    public Taller(int maxMangas, int maxCuerpos, int maxSueters) {
        semMangas = new Semaphore(0);
        semCuerpos = new Semaphore(0);
        semSueters = new Semaphore(0);
        numMaxMangas = maxMangas;
        numMaxCuerpos = maxCuerpos;
        numMaxSueters = maxSueters;
    }
    
    private void espera() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearManga() {
        while (semMangas.availablePermits() >= numMaxMangas) {
            //Si el canasto está lleno, espera
            System.out.println(Thread.currentThread().getName()+": canasto lleno, espera");
            this.espera();
        }
        //Libera un permiso, que simula una manga
        System.out.println(Thread.currentThread().getName()+": crea manga");
        semMangas.release();
    }
    
    public void crearCuerpo() {
        while (semCuerpos.availablePermits() >= numMaxCuerpos) {
            //Si el canasto está lleno, espera
            System.out.println(Thread.currentThread().getName()+": canasto lleno, espera");
            this.espera();
        }
        //Libera un permiso, que simula un cuerpo
        System.out.println(Thread.currentThread().getName()+": crea cuerpo");
        semCuerpos.release();
    }
    
    public void crearSueter() {
        while (semMangas.availablePermits() < 2 || semCuerpos.availablePermits() < 1) {
            //Verifica que hayan dos mangas y un cuerpo. Si no hay, espera
            System.out.println(Thread.currentThread().getName()+": no hay material, espera");
            this.espera();
        }
        try {
            //Toma las dos mangas y el cuerpo
            semMangas.acquire(2);
            semCuerpos.acquire(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Libera un permiso, que simula un sueter
        System.out.println(Thread.currentThread().getName()+": crea sueter");
        semSueters.release();
        chequeoSueters();
    }
    
    private void chequeoSueters() {
        //Si se llena la caja, actualiza el contador de las mismas y la cantidad de permisos
        if (semSueters.availablePermits() == this.numMaxSueters) {
            this.cajasDeSueters++;
            System.out.println("Se actualiza la cantidad de cajas de sueters");
            try {
                semSueters.acquire(numMaxSueters);
            } catch (InterruptedException ex) {
                Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int getCantidadDeCajas() {
        //Retorna la cantidad de cajas llenas de sueters
        return cajasDeSueters;
    }
}
