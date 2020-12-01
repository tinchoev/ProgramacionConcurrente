/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrupoTeoria;
import GrupoTeoria.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import lineales.dinamicas.Lista;

/**
 *
 * @author Martín
 */
public class Mostrador {
    private int pesoMaxCaja;
    private boolean hayCaja;
    private boolean requerirRetiro;
    private Caja unaCaja;
    private Lock lockCaja;
    private Condition esperaEmpSoltar;
    private Condition esperaBrazoRetiro;
    private Condition esperaBrazoRepone;
    private BlockingQueue<Integer> cola;

    public Mostrador(int pesoMaxCaja, int capacidadCinta) {
        this.pesoMaxCaja = pesoMaxCaja;
        hayCaja = true;
        requerirRetiro = false;
        unaCaja = new Caja();
        lockCaja = new ReentrantLock();
        esperaEmpSoltar = this.lockCaja.newCondition();
        esperaBrazoRetiro = this.lockCaja.newCondition();
        esperaBrazoRepone = this.lockCaja.newCondition();
        cola = new LinkedBlockingQueue<Integer>();
    }
    
    
    
    public synchronized void cocinarPastel(int peso) {
        try {
            System.out.println(Thread.currentThread().getName()+": creado pastel de "+peso);
            cola.put(peso);
            System.out.println("La cola ahora está formada por: "+cola.toString());
        } catch (InterruptedException ex) {
            Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized int tomarPastel() {
        int salida = -1;
        try {
            //System.out.println("El frente es "+cola.peek());
            salida = cola.take();
            System.out.println(Thread.currentThread().getName()+": Tomé pastel de "+salida+", intento soltarlo");
            //System.out.println("El frente es "+cola.peek());
        } catch (InterruptedException ex) {
            Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }
    
    public void soltarPastel(int peso) {
        lockCaja.lock();
        try {
            while(!hayCaja || pesoMaxCaja <= (unaCaja.getPesoActual()+peso)) {
                this.requerirRetiro = true;
                this.esperaBrazoRetiro.signal();
                this.esperaEmpSoltar.await();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+": soltó pastel de "+peso);
        unaCaja.sumarPeso(peso);
        lockCaja.unlock();
    }
    
    public void retirarCaja() {
        lockCaja.lock();
        try {
            while(!hayCaja || !requerirRetiro) {
                this.esperaBrazoRetiro.await();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+": retirando caja de "+unaCaja.getPesoActual());
        hayCaja = false;
        unaCaja = null;
        this.requerirRetiro = false;
        this.esperaBrazoRepone.signal();
        lockCaja.unlock();
    }
    
    public void reponerCaja() {
        lockCaja.lock();
        try {
            while(hayCaja) {
                this.esperaBrazoRepone.await();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        unaCaja = new Caja();
        hayCaja = true;
        this.esperaEmpSoltar.signalAll();
        lockCaja.unlock();
    }
}
