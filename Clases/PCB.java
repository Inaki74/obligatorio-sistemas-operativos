package Clases;
import java.util.ArrayList;
import java.util.Random;

public class PCB{
    PCB(int ids, String[] prog){
        System.out.println("Esto es un proceso");
        
        id = ids;
        programa = new String[prog.length];
        Random r = new Random();
        for(int i = 0; i < prog.length; i++){
            programa[i] = prog[i]; 
            Instruccion aux = new Instruccion(prog[i], 0);
            if(!listaInstrucciones.contains(aux)){
                Instruccion inst = new Instruccion(prog[i], r.nextInt(5) + 1);
                listaInstrucciones.add(inst);
            }
        }
    }

    private enum Estado{
        Listo, // 0
        EnEjecucion, // 1
        Bloqueado, // 2
    }


    public Estado estadoActual;
    public int id;
    private int linea = 0;
    private String[] programa;
    private ArrayList<Instruccion> listaInstrucciones = new ArrayList<Instruccion>();

    public boolean ejecutar(){
        boolean termino = false;
        int quantum = Procesador.Current().getQuantums();
        int quantumActual = 0;
        while(!termino && quantumActual <= quantum){
            String instruccionActual = programa[linea];
            int posicionInstruccion = listaInstrucciones.indexOf(new Instruccion(instruccionActual, 0));
            int quantumInstruccion = listaInstrucciones.get(posicionInstruccion).ciclos;
            
            System.out.println(listaInstrucciones.get(posicionInstruccion).logInstruccion());
            quantumActual += quantumInstruccion;
            linea++;
            if(linea <= programa.length) termino = true;
        }
        return termino;
    }

    public void cambiarEstado(String estado){
        switch(estado){
            case "Listo": estadoActual = Estado.Listo;
            break;
            case "EnEjecucion": estadoActual = Estado.EnEjecucion;
            break;
            case "Bloqueado": estadoActual = Estado.Bloqueado;
            break;
            default: System.out.println("PCB/cambiarEstado: Escribiste mal el estado gei");;
        }
    }

    @Override
    public String toString(){
        return "Proceso " + id;
    }
    // Proceso es programa en ejecucion.
    // El programa ponele que es el txt con el orden de instrucciones.
    // Proceso agarra el programa y lo ejecuta.
    // Proceso va leer
    // Sumar 1 en que linea estoy

}