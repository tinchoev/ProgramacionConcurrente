/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectoresEscritores.LocksCondicionales;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Martín
 */
public class Biblioteca {
    private Lock lock;
    private Condition esperanLeer;
    private Condition esperanEscribir;
    private boolean escribiendo = false;
    private int leyendo = 0;
    private int cantEspLeer = 0;
    private int cantEspEsc = 0;

    public Biblioteca() {
        lock = new ReentrantLock(true);
        this.esperanLeer = this.lock.newCondition();
        this.esperanEscribir = this.lock.newCondition();
    }

    public void leer() {
        this.lock.lock();
        cantEspLeer++;
        while (escribiendo) {
            try {
                this.esperanLeer.await();

            } catch (InterruptedException e) {
            }
        }
        cantEspLeer--;
        leyendo++;
        this.lock.unlock();
    }

    public void dejarLeer() {
        this.lock.lock();
        leyendo--;
        if (this.leyendo == 0 && this.cantEspEsc > 0) {// Si no hay lectores y hay escritores esperando les avisa
            this.esperanEscribir.signal();// Libero a uno solo
        }
        this.lock.unlock();
    }

    public void escribir() {
        this.lock.lock();
        this.cantEspEsc++;
        while (escribiendo || leyendo > 0) {
            try {
                this.esperanEscribir.await();
            } catch (InterruptedException e) {
            }
        }
        cantEspEsc--;
        this.escribiendo = true;
        this.lock.unlock();
    }

    public void dejarEscribir() {
        this.lock.lock();
        this.escribiendo = false;
        if (this.cantEspLeer > 0) {//En este momento no hay ni escritores ni lectores
                this.esperanLeer.signalAll();// Si hay lectores esperando los libero a todos para que lean
        } else {
            if (this.cantEspEsc > 0) {
                this.esperanEscribir.signal();// Si no hay lectores libero a un escritor esperando
            }
        }
        this.lock.unlock();
    }
}
