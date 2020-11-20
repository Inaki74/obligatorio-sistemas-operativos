package Clases;
import java.util.Queue;
import java.util.LinkedList;

public class Procesador{
    public Procesador(){
        quantums = 10;
        _current = this;
    }

    private static Procesador _current;
    
    public static Procesador Current(){
        if(_current == null){
            System.out.println("Procesador es vacio.");
        }

        return _current;
    }
    
    Queue<PCB> scheduler = new LinkedList<>();
    private int quantums;

    public void addProceso (PCB newProceso){
        scheduler.add(newProceso);
        System.out.println("El " + newProceso.toString() + " fue añadido al scheduler.");
    }

    public boolean ejecutarProximoProceso(){
        if(scheduler.isEmpty()){
            System.out.print("FATAL: El scheduler esta vacio.");
            return true;
        }

        PCB procesoActual = Procesador.Current().getNextProceso();
        boolean terminoProceso = procesoActual.ejecutar();

        if(!terminoProceso) {
            procesoActual.cambiarEstado("Listo");
            System.out.println("El proceso " + procesoActual.toString() + " aun no ha terminado. Quedo en la linea " + procesoActual.getLinea());
            addProceso(procesoActual);
        } else {
            System.out.println("El proceso " + procesoActual.toString() + " ha terminado su ejecucion.");
        }
       
        return scheduler.isEmpty();
    }

    public PCB getNextProceso(){
        PCB ret = scheduler.remove();
        System.out.println("Se le da el Procesador a " + ret.toString());
        return ret;
    }

    public int getQuantums(){
        return quantums;
    }

    public void esperar(RCB recurso){
        
    }
}