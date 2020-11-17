/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectoresEscritores;

/**
 *
 * @author Martín
 */
public class Biblioteca {
    private int escritoresEsperando;
    private int cantLeyendo;
    private boolean escribiendo;
    
    public Biblioteca() {
        escritoresEsperando = 0;
        cantLeyendo = 0;
        escribiendo = false;
    }
    
    public synchronized void empezarLectura() {
        try {
            while (escribiendo || escritoresEsperando > 0) {
                this.wait();
            }
        } catch (InterruptedException ex) {}
        cantLeyendo++;
    }
    
    public synchronized void terminarLectura() {
        cantLeyendo--;
        this.notifyAll();
    }
    
    public synchronized void empezarEscritura() {
        try {
            escritoresEsperando++;
            while (escribiendo || cantLeyendo>0) {
                this.wait();
            }
        } catch (InterruptedException ex) {}
        escritoresEsperando--;
        escribiendo = true;
    }
    
    public synchronized void terminarEscritura() {
        escribiendo = false;
        this.notifyAll();
    }
}
