/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema2;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Agua {
    private int cantH;
    private int cantO;
    private int recipiente;
    private int capacidad;
    private Semaphore semHidro;
    private Semaphore semOxi;
    private Semaphore semEncuentro;
    
    public Agua(int capacidadDeRecipiente) {
        cantH = 0;
        cantO = 0;
        recipiente = 0;
        capacidad = capacidadDeRecipiente;
        semHidro = new Semaphore(0);
        semOxi = new Semaphore(0);
        semEncuentro = new Semaphore(0);
    }
    
    public void generar() {
        Random aux = new Random();
        int atomo = aux.nextInt(2);
        if (atomo == 0) {
            cantH++;
            System.out.println(Thread.currentThread().getName()+": Creó un átomo de HIDRÓGENO");
            semHidro.release();
        } else {
            cantO++;
            System.out.println(Thread.currentThread().getName()+": Creó un átomo de OXÍGENO");
            semOxi.release();
        }
        System.out.println("Cantidad de átomos: -- Oxígeno: "+cantO+" -- Hidrógeno: "+cantH+" --");
    }
    
    public void Hlisto() {
        try {
            semHidro.acquire(2);
            System.out.println("Dos hidrógenos listos");
            cantH -= 2;
            semEncuentro.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Agua.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Olisto() {
        try {
            semOxi.acquire(1);
            System.out.println("Un oxígeno listo");
            cantO--;
            semEncuentro.acquire();
            System.out.println("Se encontraron dos hidrógenos y un oxígeno");
            hacerAgua();
        } catch (InterruptedException ex) {
            Logger.getLogger(Agua.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void hacerAgua() {
        System.out.println("Se ha formado agua\n"
                +"Poniendo agua en recipiente");
        try {
            Thread.sleep((int) (Math.random()+2)*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Agua.class.getName()).log(Level.SEVERE, null, ex);
        }
        recipiente++;
        System.out.println("El recipiente se ha cargado con agua\n"
                +"Ahora el recipiente se cargó "+recipiente+" veces, su capacidad es de "+capacidad);
        if (recipiente == capacidad) {
            System.out.println("El recipiente se ha llenado, se envasa el agua y se vacía el recipiente");
            recipiente = 0;
        }
    }
}
