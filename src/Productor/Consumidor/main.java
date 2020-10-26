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
public class main {
    public static void main(String [] args) {
        BufferIlimitado bufferIlim= new BufferIlimitado();
        BufferLimitado bufferLimit= new BufferLimitado();
        Productor productor= new Productor(bufferIlim, bufferLimit);
        Consumidor [] consumidores= new Consumidor[5];
        Thread hiloProductor = new Thread(productor);
        Thread [] hiloConsumidor = new Thread[5];
        for (int i = 0; i < consumidores.length; i++) {
                consumidores[i]= new Consumidor(bufferIlim, bufferLimit);
        }
        for (int i = 0; i < hiloConsumidor.length; i++) {
                hiloConsumidor[i]= new Thread(consumidores[i]);
        }
        hiloProductor.start();
        for (int i = 0; i < hiloConsumidor.length; i++) {
                hiloConsumidor[i].start();
        }
    }
}
