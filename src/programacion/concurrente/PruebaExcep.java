/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion.concurrente;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Martín
 */
public class PruebaExcep {
    
    public static void menorDeEdad(String edad) throws IOException{
        try {
            if (Integer.parseInt(edad) < 18)
                throw new IOException("es menor");
            else
                System.out.println("No es menor");
        }
        catch (NumberFormatException n) {
            throw n;
        }
        catch (IOException e) {
            throw e;
        }
    }
    
    public static boolean ruleta(String num) throws IOException{
        boolean salida = false;
        try {
            if (jugar() == Integer.parseInt(num))
                salida = true;
            else
                throw new IOException("numero distinto");
        } catch (NumberFormatException n){
            throw n;
        } catch (IOException i){
            throw i;
        }
        return salida;
    }
    private static int jugar(){
        Random num = new Random();
        return num.nextInt(33);
    }
    
    public static void mostrarCol(String[] array){
        try {
            int i;
            for (i=0; i<=7; i++)
                System.out.println(array[i]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        PruebaExcep hola = new PruebaExcep();
        System.out.println("Ingrese una edad");
        String edad = sc.nextLine();
        hola.menorDeEdad(edad);
        System.out.println("Ingrese un numero de la ruleta");
        String num = sc.nextLine();
        hola.ruleta(num);
        String[] numeros = new String[5];
        System.out.println("Ingrese un numero");
        numeros[0] = sc.nextLine();
        System.out.println("Ingrese un numero");
        numeros[1] = sc.nextLine();
        System.out.println("Ingrese un numero");
        numeros[2] = sc.nextLine();
        System.out.println("Ingrese un numero");
        numeros[3] = sc.nextLine();
        System.out.println("Ingrese un numero");
        numeros[4] = sc.nextLine();
        hola.mostrarCol(numeros);
    }
}
