/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Productor.Consumidor;

/**
 *
 * @author Martín
 */
public class Consumidor implements Runnable{
    private BufferIlimitado bufIlim;
    private BufferLimitado bufLim;
    
    public Consumidor(BufferIlimitado unBufIlim, BufferLimitado unBufLim) {
        bufIlim = unBufIlim;
        bufLim = unBufLim;
    }
    
    public void run() {
        //bufIlim.consumir();
        bufLim.consumir();
    }
}
