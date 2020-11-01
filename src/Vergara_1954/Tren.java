/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vergara_1954;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Tren {
    private Lock tren;
    private Semaphore vendedor;
    private Semaphore pasajero;
    private Lock puertasTren;//Simula la apertura de las puertas del tren
    private int capacidadTren = 6;
    private int cantSubidos = 0;
    
    public Tren() {
        tren = new ReentrantLock();
        vendedor = new Semaphore(1);
        pasajero = new Semaphore(0);
        puertasTren = new ReentrantLock();
    }
    
    private void comprarTicket() {
        try {
            vendedor.acquire();//Se toma al vendedor para que solo le venda a este pasajero
            System.out.println(Thread.currentThread().getName()+" comprando ticket");
            pasajero.release();//Se libera a pasajero para indicar que llama al vendedor
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void venderTicket() {
        try {
            pasajero.acquire();//Busca atender a un vendedor constantemente
            System.out.println("Vendedor vendiendo ticket");
            Thread.sleep(1000);//Simula venta
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            vendedor.release();//Libera al vendedor para que pueda atender a alguien más
        }
    }
    
    public void subirTren() {
        this.comprarTicket();
        System.out.println(Thread.currentThread().getName()+" tratando de subir al tren");
        puertasTren.lock();//Toma las puertas del tren para ingresar
        cantSubidos++;
        System.out.println(Thread.currentThread().getName()+" subiendo al tren");
        puertasTren.unlock();//Libera las puertas del tren una vez ingresado
    }
    
    public void trenRecorre() {
        System.out.println("Tren esperando");
        this.contadorPasajeros();
        this.trenSale();
    }
    
    private void contadorPasajeros() {
        //El tren espera hasta llenarse sus asientos
        while (cantSubidos < capacidadTren) {
            tren.lock();
        }
        //Una vez el tren se llena, se cierran sus puertas y se libera al tren
        if (cantSubidos == capacidadTren){
            puertasTren.lock();
            tren.unlock();
        }
    }
    
    public void trenSale() {
        try {
            System.out.println("Tren inicia recorrido");
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Cuando el tren termina su recorrido, reinicia su contador de pasajeros
            //subidos y vuelve a abrir sus puertas
            System.out.println("Tren terminó recorrido");
            cantSubidos = 0;
            puertasTren.unlock();
        }
    }
}
