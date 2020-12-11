/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2.Tema2;

/**
 *
 * @author Martín
 */
public class Main {
    public static void main(String[] args) {
        //Creación de instancias
        Agua unAgua = new Agua(/*capacidad del recipiente*/ 5);
        Generador unGen = new Generador(unAgua);
        Hidrogeno h = new Hidrogeno(unAgua);
        Oxigeno o = new Oxigeno(unAgua);
        //Creación de hilos
        Thread hiloG = new Thread(unGen, "Generador"), hiloH = new Thread(h), hiloO = new Thread(o);
        //Start de hilos
        hiloG.start();
        hiloH.start();
        hiloO.start();
    }
}
