/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VergaraFAI1954Parcial2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class Comedor {
    private Lock lockComedor;
    private Condition esperaPerros; //conjunto de espera de perros que quieren comer
    private Condition esperaPlatos; //conjunto de espera de un perro que espera que se llenen los platos
    private Condition esperaEncargado; //conjunto de espera del encargado
    private int cantidadDePlatos; //cantidad de platos totales
    private int platosConComida; //cantidad de platos con alimento
    private int platosEnUso; //cantidad de platos que están siendo usados
    private boolean encargadoAvisado; //confirma si el encargado ya fue avisado o no
    
    public Comedor(int unaCantidad) {
        lockComedor = new ReentrantLock();
        esperaPerros = this.lockComedor.newCondition();
        esperaPlatos = this.lockComedor.newCondition();
        esperaEncargado = this.lockComedor.newCondition();
        cantidadDePlatos = unaCantidad;
        platosConComida = cantidadDePlatos;
        platosEnUso = 0;
        encargadoAvisado = false;
    }
    
    public void comer() {
        lockComedor.lock();
        try {
            while (platosEnUso>=cantidadDePlatos || encargadoAvisado) {
                //Si no hay platos disponibles o el encargado va a llenarlos, espera
                System.out.println(Thread.currentThread().getName()+": no hay lugar, espera");
                this.esperaPerros.await();
            }
            while (platosConComida<=0) {
                //Si no hay platos con comida, se le avisa al encargado y espera
                System.out.println(Thread.currentThread().getName()+": no hay comida, ladra y espera");
                encargadoAvisado = true;
                this.esperaEncargado.signal();
                this.esperaPlatos.await();
            }
        } catch (InterruptedException ex) {
        }
        //Empieza a comer, usa un plato y lo deja sin comida
        System.out.println(Thread.currentThread().getName()+": empieza a comer");
        platosEnUso++;
        platosConComida--;
        lockComedor.unlock();
    }
    
    public void terminarDeComer() {
        //Deja el plato disponible y avisa a los perros que esperan un lugar
        this.lockComedor.lock();
        System.out.println(Thread.currentThread().getName()+": termina de comer");
        platosEnUso--;
        this.esperaPerros.signal();
        this.lockComedor.unlock();
    }
    
    public void trabajar() {
        lockComedor.lock();
        try {
            while (platosConComida>0) {
                //Si no hay platos vacíos, espera
                this.esperaEncargado.await();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Llena los platos, actualiza la cantidad de platos con comida, avisa primero
        //al perro que le avisó a él y después a los demás esperando por un lugar
        System.out.println(Thread.currentThread().getName()+": llena platos");
        this.platosConComida = cantidadDePlatos;
        encargadoAvisado = false;
        this.esperaPlatos.signal();
        this.esperaPerros.signalAll();
        lockComedor.unlock();
    }
}
