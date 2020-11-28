/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP6.punto6;

/**
 *
 * @author Martín
 */
public class main {
    public static void main(String[] args) {
        //Creación instancias
        Observatorio obs = new Observatorio();
        Visitante[] colVis = new Visitante[15];
        Investigador[] colInv = new Investigador[5];
        Personal[] colPer = new Personal[10];
        for (int i = 0; i < colVis.length; i++) {
            colVis[i] = new Visitante(obs);
        }
        for (int i = 0; i < colInv.length; i++) {
            colInv[i] = new Investigador(obs);
        }
        for (int i = 0; i < colPer.length; i++) {
            colPer[i] = new Personal(obs);
        }
        //Creación hilos
        Thread[] hilosVis = new Thread[15];
        Thread[] hilosInv = new Thread[5];
        Thread[] hilosPer = new Thread[10];
        for (int i = 0; i < hilosVis.length; i++) {
            hilosVis[i] = new Thread(colVis[i], "Visitante "+i);
        }
        for (int i = 0; i < hilosInv.length; i++) {
            hilosInv[i] = new Thread(colInv[i], "Investigador "+i);
        }
        for (int i = 0; i < hilosPer.length; i++) {
            hilosPer[i] = new Thread(colPer[i], "Personal "+i);
        }
        //Start hilos
        for (int i = 0; i < hilosVis.length; i++) {
            hilosVis[i].start();
        }
        for (int i = 0; i < hilosInv.length; i++) {
            hilosInv[i].start();
        }
        for (int i = 0; i < hilosPer.length; i++) {
            hilosPer[i].start();
        }
    }
}
