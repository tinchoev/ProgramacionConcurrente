/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vergara_1954;

/**
 *
 * @author Martín
 */
public class VendedorTickets implements Runnable{
    private Tren tren;//Recurso compartido
    
    public VendedorTickets(Tren trencito) {
        tren = trencito;
    }
    
    public void run() {
        //El vendedor busca constantemente vender
        while (true) {
            tren.venderTicket();
        }
    }
}
