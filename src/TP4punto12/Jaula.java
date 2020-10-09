/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4punto12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 *
 * @author Martín
 */
public class Jaula {
    private Lock lockPlato;
    private Lock lockRueda;
    private Lock lockHamaca;
    private final int tiempoSleep = 3000;
    
    public Jaula() {
        lockPlato = new ReentrantLock();
        lockRueda = new ReentrantLock();
        lockHamaca = new ReentrantLock();
    }
    
    public void comerDelPlato(String unColor) {
        lockPlato.lock();
        try {
            System.out.println(unColor+Thread.currentThread().getName()+": Estoy comiendo");
            Thread.sleep(tiempoSleep);
            System.out.println(unColor+Thread.currentThread().getName()+": Terminé de comer");
        } catch(InterruptedException ex) {
            System.out.println(unColor+Thread.currentThread().getName()+": No pude comer");
        }
    }
    
    public void correrEnLaRueda(String unColor) {
        lockPlato.unlock();
        lockRueda.lock();
        try {
            System.out.println(unColor+Thread.currentThread().getName()+": Estoy corriendo");
            Thread.sleep(tiempoSleep);
            System.out.println(unColor+Thread.currentThread().getName()+": Terminé de correr");
        } catch(InterruptedException ex) {
            System.out.println(unColor+Thread.currentThread().getName()+": No pude correr");
        }
    }
    
    public void descansarEnHamaca(String unColor) {
        lockRueda.unlock();
        lockHamaca.lock();
        try {
            System.out.println(unColor+Thread.currentThread().getName()+": Estoy descansando");
            Thread.sleep(tiempoSleep);
            System.out.println(unColor+Thread.currentThread().getName()+": Terminé de descansar");
        } catch(InterruptedException ex) {
            System.out.println(unColor+Thread.currentThread().getName()+": No pude descansar");
        } finally {
            lockHamaca.unlock();
        }
    }
}
