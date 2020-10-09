/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3;

/**
 *
 * @author Martín
 */
public class Vehiculo {
    private String patente;
    private String modelo;
    private String marca;
    private double kmFaltantesParaElService;
    private boolean estaEnReserva;
    
    public Vehiculo(String unaPat, String unMod, String unaMarca, int unaCant){
        patente = unaPat;
        modelo = unMod;
        marca = unaMarca;
        kmFaltantesParaElService = unaCant;
        estaEnReserva = true;
    }

    public boolean getEstaEnReserva(){
        return estaEnReserva;
    }
    public String getPatente() {
        return patente;
    }
    public String getModelo() {
        return modelo;
    }
    public String getMarca() {
        return marca;
    }
    public double getKmFaltantesParaElService() {
        return kmFaltantesParaElService;
    }
    
    public void setEstaEnReserva(boolean unBoolean){
        this.estaEnReserva = unBoolean;
    }
    public void setKmFaltantesParaElService(double kmFaltantesParaElService) {
        this.kmFaltantesParaElService = kmFaltantesParaElService;
    }
}
