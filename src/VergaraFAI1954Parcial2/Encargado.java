/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VergaraFAI1954Parcial2;

/**
 *
 * @author Martín
 */
public class Encargado implements Runnable{
    private Comedor miComedor;

    public Encargado(Comedor miComedor) {
        this.miComedor = miComedor;
    }
    
    public void run() {
        while (true) {
            miComedor.trabajar();
        }
    }
}
