package Clases;


public class RCB{
    public RCB(String n, int tiempoEspera){
        nombre = n;
        tiempoEsperaMaximo = tiempoEspera;
        procesoAsociado = null;
    }

    public void setTiempoEspera(int tiempo){
        tiempoEsperaMaximo = tiempo;
    }

    public boolean getDisponibilidad(){
        return procesoAsociado == null;
    }

    public String getNombre () {
        return this.nombre;
    }

    public void setProceso (PCB proceso){
        this.procesoAsociado = proceso;
    }

    public void setUso (boolean estado){
        this.enUso = estado;
    }

    public boolean getUso (){
        return this.enUso;
    }

    public void avanzar(){
        tiempoEsperaActual++;
    }

    public void setTiempoAcutal(int tiempo){
        tiempoEsperaActual = tiempo;
    }

    public boolean termino(){
        return tiempoEsperaActual == tiempoEsperaMaximo;
    }

    public PCB getProceso(){
        return procesoAsociado;
    }

    private String nombre;
    private int tiempoEsperaMaximo; // Tiempo en quantums de procesador.
    private int tiempoEsperaActual = 0;
    private PCB procesoAsociado;
    private boolean enUso;

    @Override
    public String toString(){
        return "Recurso " + nombre;
    }
}