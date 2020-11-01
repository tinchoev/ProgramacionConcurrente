/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5punto3;

/**
 *
 * @author Martín
 */
public class VendedorTickets implements Runnable{
    private ControlTren control;
    
    public VendedorTickets(ControlTren unControl) {
        control = unControl;
    }
    
    public void run() {
        while (true) {
            control.venderTicket();
        }
    }
}
