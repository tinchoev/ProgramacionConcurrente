/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Martín
 */
public class Observatorio {
    private int capacidad;
    private int cantidadVisitantes;
    private int cantidadPersonal;
    private int cantidadInvestigadores;
    private Lock lockSala;
    private Condition esperaSala;
    
    public Observatorio() {
        capacidad = 50;
        lockSala = new ReentrantLock();
        esperaSala = this.lockSala.newCondition();
    }
    
    public void entrarVisitante() {
        lockSala.lock();
        try {
            while (cantidadVisitantes>=capacidad || cantidadPersonal>0 || cantidadInvestigadores>0) {
                esperaSala.await();
            }
        } catch (InterruptedException ex) {
            
        }
        System.out.println(Thread.currentThread().getName()+": entra a la sala");
        cantidadVisitantes++;
        lockSala.unlock();
    }
    
    public void entrarVisitanteEnSillaDeRuedas() {
        lockSala.lock();
        try {
            while (cantidadVisitantes>=30 || cantidadPersonal>0 || cantidadInvestigadores>0) {
                esperaSala.await();
            }
        } catch (InterruptedException ex) {
            
        }
        System.out.println(Thread.currentThread().getName()+"(silla de ruedas): entra a la sala");
        this.cambiarCapacidadA30();
        cantidadVisitantes++;
        lockSala.unlock();
    }
    
    public void entrarInvestigador() {
        lockSala.lock();
        try {
            while (cantidadVisitantes>0 || cantidadPersonal>0 || cantidadInvestigadores>0) {
                esperaSala.await();
            }
        } catch (InterruptedException ex) {
            
        }
        System.out.println(Thread.currentThread().getName()+": entra a la sala");
        cantidadInvestigadores++;
        lockSala.unlock();
    }
    
    public void entrarPersonal() {
        lockSala.lock();
        try {
            while (cantidadVisitantes>0 || cantidadInvestigadores>0) {
                esperaSala.await();
            }
        } catch (InterruptedException ex) {
            
        }
        System.out.println(Thread.currentThread().getName()+": entra a la sala");
        cantidadPersonal++;
        lockSala.unlock();
    }
    
    public void salirVisitante() {
        lockSala.lock();
        System.out.println(Thread.currentThread().getName()+": sale de la sala");
        cantidadVisitantes--;
        this.esperaSala.signalAll();
        lockSala.unlock();
    }
    
    public void salirVisitanteEnSillaDeRuedas() {
        lockSala.lock();
        System.out.println(Thread.currentThread().getName()+"(silla de ruedas): sale de la sala");
        cantidadVisitantes--;
        this.cambiarCapacidadA50();
        this.esperaSala.signalAll();
        lockSala.unlock();
    }
    
    public void salirInvestigador() {
        lockSala.lock();
        System.out.println(Thread.currentThread().getName()+": sale de la sala");
        cantidadInvestigadores--;
        this.esperaSala.signalAll();
        lockSala.unlock();
    }
    
    public void salirPersonal() {
        lockSala.lock();
        System.out.println(Thread.currentThread().getName()+": sale de la sala");
        cantidadPersonal--;
        this.esperaSala.signalAll();
        lockSala.unlock();
    }
    
    private void cambiarCapacidadA50() {
        capacidad = 50;
        System.out.println("La capacidad ahora es de "+capacidad+" personas");
    }
    
    private void cambiarCapacidadA30() {
        capacidad = 30;
        System.out.println("La capacidad ahora es de "+capacidad+" personas");
    }
}
