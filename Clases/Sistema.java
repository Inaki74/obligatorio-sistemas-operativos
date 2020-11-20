package Clases;
import java.util.ArrayList;

public class Sistema{
    public Sistema(){
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
    
    public String[][] ImportarProgramas(){
        String[][] programas = { {"A", "A", "B", "B", "A", "C"}, 
                                 {"A", "A", "D", "E", "E", "F", "D"}, 
                                 {"P", "P", "L", "F", "A", "D", "D", "F", "A"}};

        return programas;
    }

    public void crearProcesos() {
        String[][] programas = Sistema.Current().ImportarProgramas();

        PCB proceso1 = new PCB(1, programas[0]);
        PCB proceso2 = new PCB(2, programas[1]);
        PCB proceso3 = new PCB(3, programas[0]);
        PCB proceso4 = new PCB(4, programas[1]);
        PCB proceso5 = new PCB(5, programas[2]);
        PCB proceso6 = new PCB(6, programas[2]);

        procesos.add(proceso1);
        procesos.add(proceso2);
        procesos.add(proceso3);
        procesos.add(proceso4);
        procesos.add(proceso5);
        procesos.add(proceso6);
    }

    public void cargarTodosProcesos() {
        for(int i = 0; i < procesos.size(); i++){
            Procesador.Current().addProceso(procesos.get(i));
        }
    }
}