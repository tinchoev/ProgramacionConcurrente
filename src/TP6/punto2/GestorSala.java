/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class GestorSala {
    private int tUmbral;
    private int maxPersonas;
    private int cantPersonas;
    private int jubiladosEsperando;
    
    public GestorSala(int unUmbral) {
        tUmbral = unUmbral;
        maxPersonas = -1;
        cantPersonas = 0;
        jubiladosEsperando = 0;
    }
    
    public synchronized void entrarSala() {
        try {
            while (cantPersonas >= maxPersonas && jubiladosEsperando > 0) {
                System.out.println(Thread.currentThread().getName()+": Sala no disponible");
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+": Entra a la sala. Cantidad de personas = "+(cantPersonas+1));
        cantPersonas++;
    }
    
    public synchronized void entrarSalaJubilado() {
        try {
            while (cantPersonas >= maxPersonas) {
                System.out.println(Thread.currentThread().getName()+": Sala no disponible");
                jubiladosEsperando++;
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+" (jubilado): Entra a la sala. Cantidad de personas = "+(cantPersonas+1));
        cantPersonas++;
        jubiladosEsperando--;
    }
    
    public synchronized void salirSala() {
        System.out.println(Thread.currentThread().getName()+": Sale de la sala. Cantidad de personas = "+(cantPersonas-1));
        cantPersonas--;
        this.notifyAll();
    }
    
    public synchronized void notificarTemperatura(long temperatura) {
        if (temperatura <= tUmbral) {
            maxPersonas = 5;
        } else {
            maxPersonas = 3;
        }
        System.out.println(Thread.currentThread().getName()+": la temperatura es "
                +temperatura+". El límite de personas es "+ maxPersonas);
    }
}
