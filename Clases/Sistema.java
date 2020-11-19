package Clases;
import java.util.ArrayList;

public class Sistema{
    public Sistema(){
        System.out.println("Esto es un sistema");
        _current = this;
    }

    private static Sistema _current;
    public static Sistema Current(){
        if(_current == null){
            System.out.println("Sistema es vacio.");
        }

        return _current;
    }
    
    private ArrayList<PCB> procesos = new ArrayList<PCB>();
    

    //Pre: Hay al menos un proceso.
    public void DarProcesador(){
        
    }



    public boolean pierdeProcesador(){
        return false;
    }
}