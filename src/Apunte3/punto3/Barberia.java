/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apunte3.punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Barberia {

    private int sillasEspera;
    private int cantClientes;
    private int sillon;
    
    //Constructor

    public Barberia(int sillasEspera) {
        this.sillasEspera = sillasEspera;
        this.cantClientes = 0;
        this.sillon = 0;
    }
    

    //Parte Cliente
    public synchronized void entrarBarberia() {
        try {
            while (sillasEspera <= cantClientes) {
                System.out.println(Thread.currentThread().getName() + " debe esperar, no puede entrar");
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " entró a la barbería");
        cantClientes++;
    }

    public synchronized void solicitarCorte() {
        this.notifyAll();
        try {
            while (sillon <= 0) {
                System.out.println(Thread.currentThread().getName() + " debe esperar, sillon no disponible");
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " es atendido");
        cantClientes--;
        this.notifyAll();
    }

    //Parte Barbero
    public synchronized void esperarCliente() {
        try {
            while (cantClientes <= 0) {
                System.out.println(Thread.currentThread().getName() + " duerme");
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }
        sillon++;
        this.notify();
        System.out.println(Thread.currentThread().getName() + " atiende un cliente");
        sillon--;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " terminó de atender");
    }
}
