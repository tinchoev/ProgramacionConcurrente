/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectoresEscritores.Monitores;

/**
 *
 * @author Martín
 */
public class Biblioteca {
    private int cantLeyendo;
    private boolean escribiendo;
    
    public Biblioteca() {
        cantLeyendo = 0;
        escribiendo = false;
    }
    
    public synchronized void empezarLectura() {
        try {
            while (escribiendo) {
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
            while (escribiendo || cantLeyendo>0) {
                this.wait();
            }
        } catch (InterruptedException ex) {}
        escribiendo = true;
    }
    
    public synchronized void terminarEscritura() {
        escribiendo = false;
        this.notifyAll();
    }
}
