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
public class Hidrogeno implements Runnable{
    private Agua miAgua;
    
    public Hidrogeno(Agua unAgua) {
        miAgua = unAgua;
    }
    
    public void run() {
        while (true) {
            miAgua.Hlisto();
        }
    }
}
