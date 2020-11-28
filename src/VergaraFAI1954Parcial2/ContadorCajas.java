/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VergaraFAI1954Parcial2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martín
 */
public class ContadorCajas implements Runnable{
    private Taller miTaller;

    public ContadorCajas(Taller miTaller) {
        this.miTaller = miTaller;
    }
    
    public void run() {
        //Cada cierto tiempo, retorna la cantidad de cajas
        try {
            while (true) {
                Thread.sleep(5000);
                System.out.println("Actualmente hay "+miTaller.getCantidadDeCajas()+" cajas");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ContadorCajas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
