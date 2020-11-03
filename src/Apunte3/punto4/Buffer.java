/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto4;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import lineales.dinamicas.Cola;

/**
 *
 * @author Martín
 */
public class Buffer {
    
    private Cola datos;
    private int cantMaxDatos;
    private int cantDatos;

    //Constructor
    public Buffer(int unaCantMaxDatos) {    
        this.datos = new Cola();
        cantMaxDatos = unaCantMaxDatos;
        cantDatos = 0;
    }

    //Parte Consumidor
    public synchronized void consumir() {
        try {
            while (datos.esVacia()) {
                System.out.println(Thread.currentThread().getName() + " no tiene datos que consumir");
                this.wait();
            }
        } catch (InterruptedException ex) {
        }
        System.out.println(Thread.currentThread().getName()+" consumió un dato");
        datos.sacar();
        cantDatos--;
        this.notifyAll();
    }

    //Parte Productor
    public synchronized void producir() {
        try {
            while (cantMaxDatos <= cantDatos) {
                System.out.println(Thread.currentThread().getName() + " límite de datos existentes alcanzado");
                this.wait();
            }
        } catch (InterruptedException ex) {
            //Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){}
        System.out.println(Thread.currentThread().getName()+" produjo un dato");
        datos.poner("dato");
        cantDatos++;
        this.notifyAll();
    }
}
