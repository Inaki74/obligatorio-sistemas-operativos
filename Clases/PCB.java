package Clases;
import java.util.Hashtable;
import Clases.Sistema;
import java.util.Random;

public class PCB{
    PCB(int ids, String[] prog){
        System.out.println("Esto es un proceso");
        
        id = ids;
        programa = new String[prog.length];

        Random r = new Random();
        for(int i = 0; i < prog.length; i++){
            programa[i] = prog[i];
            if(!nombresInstrucciones.contains(prog[i])){
                nombresInstrucciones.put(prog[i], r.nextInt(10));
            }
        }
    }

    private enum Estado{
        Listo,
        EnEjecucion,
        Bloqueado,
    }
    

    public int id;
    private int linea = 0;
    private String[] programa;
    private Hashtable<String, Integer> nombresInstrucciones = new Hashtable<String, Integer>();

    public boolean ejecutar(){
        boolean termino = true;
        int ciclos = 0;
        // Para si:
        // - Pide un recurso, esta ocupado <--
        // - Pierde el procesador <--- thiz
        //      Cuando pierdo el procesador?
        // - No tiene permiso.
        while(linea < programa.length && termino){

            // while(ciclos != instruccion.ciclos)
            if(Sistema.Current().pierdeProcesador()){

            }
            

            System.out.println(programa[linea]);

            ciclos++;
            linea++;
        }

        return termino;
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