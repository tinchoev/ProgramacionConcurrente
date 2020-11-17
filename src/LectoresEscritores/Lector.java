/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectoresEscritores;

/**
 *
 * @author Martín
 */
public class Lector implements Runnable{
    Biblioteca biblioteca;
    public Lector(Biblioteca biblio) {
            this.biblioteca= biblio;
    }

    public void run() {
            this.biblioteca.empezarLectura();
            System.out.println("El "+Thread.currentThread().getName()+" entra a leer");
            try {
                    Thread.sleep(4000);
            }catch(InterruptedException e) {}
            this.biblioteca.terminarLectura();
            System.out.println("El "+Thread.currentThread().getName()+" deja de leer");
    }
}
