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
public class ControlCorderos implements Runnable {
    private Rio miRio;
    
    public ControlCorderos(Rio unRio) {
        miRio = unRio;
    }
    
    public void run() {
        while (true) {
            miRio.contar();
        }
    }
}
