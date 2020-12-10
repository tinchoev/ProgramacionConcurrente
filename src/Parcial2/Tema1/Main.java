/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema1;

/**
 *
 * @author Martín
 */
public class Main {
    public static void main(String[] args) {
        //Creación instancias
        Control ctrl = new Control(/*capacidad del transbordador*/ 10);
        Transbordador trans = new Transbordador(ctrl);
        Coche[] colCoches = new Coche[50];
        for (int i = 0; i < colCoches.length; i++) {
            colCoches[i] = new Coche(ctrl);
        }
        //Creación de hilos
        Thread hiloTrans = new Thread(trans, "Transbordador");
        Thread[] hilosCoches = new Thread[colCoches.length];
        for (int i = 0; i < colCoches.length; i++) {
            hilosCoches[i] = new Thread(colCoches[i], "Coche "+i);
        }
        //Start de hilos
        hiloTrans.start();
        for (int i = 0; i < colCoches.length; i++) {
            hilosCoches[i].start();
        }
    }
}
