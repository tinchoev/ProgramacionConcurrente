/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4punto13;

/**
 *
 * @author Martín
 */
public class Mozo implements Runnable {
    private Confiteria conf;
    private String color;
    
    public Mozo(Confiteria unaConfiteria, String unColor) {
        conf = unaConfiteria;
        color = unColor;
    }
    
    @Override
    public void run() {
        System.out.println(color+"Soy el mozo");
        while (true) {
            conf.trabajarMozo(color);
        }
    }
}
