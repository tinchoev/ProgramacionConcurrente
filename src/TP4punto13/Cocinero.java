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
public class Cocinero implements Runnable{
    Confiteria conf;
    String color;
    
    public Cocinero(Confiteria unaConf, String unColor) {
        conf = unaConf;
        color = unColor;
    }
    
    public void run() {
        conf.trabajarCocinero(color);
    }
}
