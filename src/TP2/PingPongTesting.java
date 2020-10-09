/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2;

/**
 *
 * @author Martín
 */
public class PingPongTesting {
    public static void main (String [] args){
        PingPong t1 = new PingPong("PING", 33);
        PingPong t2 = new PingPong("PONG", 10);
        
        t1.start();
        t2.start();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){    
        }
        
        for(int i=1; i<100; i++){
            System.out.println("b");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){
            }
        }
    }
}
