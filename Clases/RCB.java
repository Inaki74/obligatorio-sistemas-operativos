package Clases;


public class RCB{
    public RCB(String n, int tiempoEspera){
        nombre = n;
        tiempoEsperaMaximo = tiempoEspera;
        procesoAsociado = null;
    }

    private String nombre;
    private int tiempoEsperaMaximo; // Tiempo en quantums de procesador.
    private int tiempoEsperaActual = 0;
    private PCB procesoAsociado;
    private boolean enUso;

    public void avanzar(){
        tiempoEsperaActual++;
    }

    public boolean termino(){
        return tiempoEsperaActual == tiempoEsperaMaximo;
    }

    public boolean getDisponibilidad(){
        return procesoAsociado == null;
    }

    public String getNombre () {
        return this.nombre;
    }

    public boolean getUso (){
        return this.enUso;
    }

    public PCB getProceso(){
        return procesoAsociado;
    }

    public void setTiempoEspera(int tiempo){
        tiempoEsperaMaximo = tiempo;
    }
    
    public void setProceso (PCB proceso){
        this.procesoAsociado = proceso;
    }

    public void setUso (boolean estado){
        this.enUso = estado;
    }

    public void setTiempoAcutal(int tiempo){
        tiempoEsperaActual = tiempo;
    }

    @Override
    public String toString(){
        return "Recurso " + nombre;
    }
}