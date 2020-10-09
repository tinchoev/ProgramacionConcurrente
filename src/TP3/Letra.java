package TP3;

public class Letra implements Runnable {

    private char letra;
    private int turno;
    private int cant;
    private Turno turnoL;

    public Letra(char l, int t, int cantidad, Turno turn) {
        letra = l;
        turno = t;
        cant = cantidad;
        turnoL = turn;
    }

    public int getTurno() {
        return turno;
    }

    public int getCantidad() {
        return cant;
    }

    public char getLetra() {
        return letra;
    }

    public void run() {
        while (turnoL.getRepeticiones()>0){
            if (this.turno == turnoL.getTurno()) {
                for (int i = 1; i <= this.cant; i++) {
                    System.out.print(letra);
                }
                turnoL.pasarTurno();
            } else {
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
