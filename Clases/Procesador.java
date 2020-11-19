package Clases;
import java.util.Queue;
import java.util.LinkedList;

public class Procesador{
    Procesador(){
        System.out.println("Esto es un procesador");
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
    }

    void ejecutarProceso(){
        if(scheduler.isEmpty()){
            System.out.print("FATAL: El scheduler esta vacio.");
            return;
        }

        PCB proximo = scheduler.remove();
        // -> Sistema busca en la lista y le da el procesador
        // -> Proceso tiene una funcion que usa el procesador.
        // Si necesita recurso y eso.
        // Sacariamos la foto por aca -- version 2.

        proximo.ejecutar();
    }

    public int getQuantums(){
        return quantums;
    }

    public void esperar(RCB recurso){
        int ciclo = 0;
        for(int i = 0; i < recurso.tiempoEspera; i++){
            
        }
    }
}