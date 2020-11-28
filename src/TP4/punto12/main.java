/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.punto12;

/**
 *
 * @author Martín
 */
public class main {
    public static void main(String [] args) {
        Jaula unaJaula = new Jaula();
        int cantHamsters = 4;
        Hamster[] misHamsters = new Hamster[cantHamsters];
        Thread[] misHilos = new Thread[cantHamsters];
        misHamsters[0] = new Hamster(unaJaula, "\u001B[34m");
        misHamsters[1] = new Hamster(unaJaula, "\u001B[31m");
        misHamsters[2] = new Hamster(unaJaula, "\u001B[33m");
        misHamsters[3] = new Hamster(unaJaula, "\u001B[32m");
        for (int i=0; i<4; i++) {
            misHilos[i] = new Thread(misHamsters[i]);
        }
        for (int i=0; i<4; i++) {
            misHilos[i].start();
        }
    }
}
