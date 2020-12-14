/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vergara_FAI1954_recuperatorio;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Rio {
    private Semaphore semEspera;//Espera de compañero para beber
    private Semaphore semBeber;
    private Semaphore semEntrar;//Entran al río
    private Semaphore semSalir;//Salen del río
    private Semaphore mutex;
    private int cantCordEsperando;//Cantidad de corderos esperando a beber
    private int cantCordBebiendo;//Cantidad de corderos bebiendo
    private int cantCordSalir;//Cantidad de corderos queriendo salir
    
    public Rio() {
        semEntrar = new Semaphore(1);
        semEspera = new Semaphore(1);
        semBeber = new Semaphore(0);
        semSalir = new Semaphore(0);
        mutex = new Semaphore(1);
        cantCordEsperando = 0;
        cantCordBebiendo = 0;
        cantCordSalir = 0;
    }
    
    //Lógica cordero
    public void entrar() {
        try {
             //Se asegura la exclusión mutua de modificación de variables con semEspera y semEntrar
             //semBeber indica que ya puede beber porque tiene compañero
            semEspera.acquire();
            System.out.println(Thread.currentThread().getName()+": Espera compañero");
            cantCordEsperando++;
            semEspera.release();
            semEntrar.acquire();
            semBeber.acquire();
            cantCordEsperando--;
            cantCordBebiendo++;
            System.out.println(Thread.currentThread().getName()+": Entra a beber, corderos bebiendo = "+cantCordBebiendo);
            semEntrar.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salir() {
        try {
            //Se asegura la exclusión mutua de modificación de variables con mutex
            //semSalir indica que ya puede salir porque no deja a nadie bebiendo solo
            mutex.acquire();
            cantCordBebiendo--;
            cantCordSalir++;
            mutex.release();
            semSalir.acquire();
            mutex.acquire();
            System.out.println(Thread.currentThread().getName()+": Sale del río");
            cantCordSalir--;
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Lógica control de corderos
    public void contar() {
        //Si hay algún cordero que ya está esperando o bebiendo, deja entrar a beber a la cantidad de corderos esperando
        if (cantCordEsperando > 1 || cantCordBebiendo > 0) {
            semBeber.release(cantCordEsperando);
        }
        //Si hay algún cordero que ya está bebiendo, o no hay ninguno, permite que se vayan del río los que se quieren ir
        if (cantCordBebiendo > 1 || cantCordBebiendo == 0) {
            semSalir.release(cantCordSalir);
        }
    }
    
    //Lógica lobo
    public void beber() {
        //Si hay algún cordero solo, lo come
        if (cantCordBebiendo == 1) {
            System.out.println(": Comiendo cordero");
        }
    }
}
