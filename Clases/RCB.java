package Clases;


public class RCB{
    public RCB(int i, String n, int tiempoEspera){
        id = i;
        nombre = n;
        tiempoEsperaMaximo = tiempoEspera;
        procesoAsociado = null;
    }

    public void setTiempoEspera(int tiempo){
        tiempoEsperaMaximo = tiempo;
    }

    public boolean getDisponibilidad(){
        return procesoAsociado != null;
    }

    private int id;
    private String nombre;
    private int tiempoEsperaMaximo; // Tiempo en ciclos de procesador.
    private int tiempoEsperaActual = 0;
    private PCB procesoAsociado;
}