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
    private ArrayList<RCB> recursos = new ArrayList<RCB>();

    public String[][] ImportarProgramas(){
        String[][] programas = { {"Pedir impresora#1", "Usar impresora#1", "Devolver impresora#1", "B", "A", "C"}, 
                                 {"A", "A", "D","Pedir impresora#2", "Usar impresora#2", "Devolver impresora#2", "E", "E", "F", "D"}, 
                                 {"P", "P", "L", "F","Pedir impresora#3", "Usar impresora#3", "Devolver impresora#3", "A", "D", "D", "F", "A"}};

        return programas;
    }

    public String[] importarRecursos(){
        String[] recursos = {"impresora#1", "impresora#2", "impresora#3"};
        
        return recursos;
    }
    
    public void crearRecursos() {
        String[] listaRecursos = importarRecursos();
        RCB recurso1 = new RCB(listaRecursos[0], 4);
        RCB recurso2 = new RCB(listaRecursos[1], 2);
        RCB recurso3 = new RCB(listaRecursos[2], 6);
        recursos.add(recurso1);
        recursos.add(recurso2);
        recursos.add(recurso3);
    }

    public void crearProcesos() {
        String[][] programas = ImportarProgramas();

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

    public RCB getRCB (String nombre) {
        RCB rcb = null;
        for(int i = 0; i < recursos.size() ; i++){
            RCB aux = recursos.get(i);
            if(aux.getNombre().equals(nombre)){
                rcb = aux;
            }
        }
        return rcb;
    }

    public PCB getPCB (int id) {
        PCB pcb = null;
        for(int i = 0; i < recursos.size() ; i++){
            PCB aux = procesos.get(i);
            if(aux.getId() == id){
                pcb = aux;
            }
        }
        return pcb;
    }

    public void avanzarRecursos(){
        // Lista RCB + 1 en ese quantum a todos lo que esten siendo utilizados
        // Avanzas en todos los RCBs, si uno termina pasa su Proceso de Bloqueado a Listo y su tiempo actual se settea de nuevo a 0 y su uso en false
        for(int i = 0; i < recursos.size(); i++){
            RCB recurso = recursos.get(i);
            if(!recurso.getDisponibilidad()){
                recurso.avanzar();
                if(recurso.termino()){
                    PCB proceso = recurso.getProceso();
                    System.out.println("Sistema/avanzarRecursos: Se termino de usar el " + recurso);
                    recurso.setTiempoAcutal(0);
                    recurso.setUso(false);
                    proceso.cambiarEstado("Listo");
                }
            }
            
        }
    }
}