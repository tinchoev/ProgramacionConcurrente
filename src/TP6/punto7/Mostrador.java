/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto7;
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
    private Lista cinta;
    private int capacidadCinta;
    private Caja unaCaja;
    private Lock lockCinta;
    private Lock lockCaja;
    //private Lock lockHornos;
    private Condition esperaHorno;
    private Condition esperaEmpTomar;
    private Condition esperaEmpSoltar;
    private Condition esperaBrazoRetiro;
    private Condition esperaBrazoRepone;

    public Mostrador(int pesoMaxCaja, int capacidadCinta) {
        this.pesoMaxCaja = pesoMaxCaja;
        this.capacidadCinta = capacidadCinta;
        hayCaja = true;
        requerirRetiro = false;
        cinta = new Lista();
        unaCaja = new Caja();
        lockCinta = new ReentrantLock();
        lockCaja = new ReentrantLock();
        lockCinta = new ReentrantLock();
        esperaHorno = this.lockCinta.newCondition();
        esperaEmpTomar = this.lockCinta.newCondition();
        esperaEmpSoltar = this.lockCaja.newCondition();
        esperaBrazoRetiro = this.lockCaja.newCondition();
        esperaBrazoRepone = this.lockCaja.newCondition();
    }
    
    
    
    public void cocinarPastel(int peso) {
        lockCinta.lock();
        try {
            while (capacidadCinta <= cinta.longitud()) {
                System.out.println(Thread.currentThread().getName()+": cinta llena");
                this.esperaHorno.await();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+": creado pastel de "+peso);        
        cinta.insertar(peso, cinta.longitud()+1);
        this.esperaEmpTomar.signal();
        lockCinta.unlock();
    }
    
    public int tomarPastel() {
        lockCinta.lock();
        try {
            while (cinta.esVacia()) {
                this.esperaEmpTomar.await();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        int pastel = (int) cinta.recuperar(1);
        cinta.eliminar(1);
        this.esperaHorno.signal();
        lockCinta.unlock();
        return pastel;
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
