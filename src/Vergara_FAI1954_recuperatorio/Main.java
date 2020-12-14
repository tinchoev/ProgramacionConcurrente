/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vergara_FAI1954_recuperatorio;

/**
 *
 * @author Martín
 */
public class Main {
    public static void main(String[] args) {
        //Creación instancias
        Rio r = new Rio();
        Cordero[] colCor = new Cordero[10];
        Lobo[] colLobos = new Lobo[10];
        ControlCorderos ctrl = new ControlCorderos(r);
        for (int i = 0; i < colCor.length; i++) {
            colCor[i] = new Cordero(r);
        }
        for (int i = 0; i < colLobos.length; i++) {
            colLobos[i] = new Lobo(r);
        }
        
        //Creación hilos
        Thread[] hilosCord = new Thread[colCor.length], hilosLobos = new Thread[colLobos.length];
        for (int i = 0; i < hilosCord.length; i++) {
            hilosCord[i] = new Thread(colCor[i], "Cordero "+i);
        }
        for (int i = 0; i < hilosLobos.length; i++) {
            hilosLobos[i] = new Thread(colLobos[i], "Lobo "+i);
        }
        Thread hiloCtrl = new Thread(ctrl);
        
        //Start hilos
        for (int i = 0; i < hilosCord.length; i++) {
            hilosCord[i].start();
        }
        for (int i = 0; i < hilosLobos.length; i++) {
            hilosLobos[i].start();
        }
        hiloCtrl.start();
    }
}
