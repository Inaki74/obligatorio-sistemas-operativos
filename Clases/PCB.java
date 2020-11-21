package Clases;
import java.util.ArrayList;
import java.util.Random;

public class PCB{
    public PCB(int ids, String[] prog){
        id = ids;
        estadoActual = Estado.Listo;
        recursoUtilizado = null;
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

        System.out.println(this.toString() + " fue creado.");
    }

    private enum Estado{
        Listo, // 0
        EnEjecucion, // 1
        Bloqueado, // 2
    }


    public Estado estadoActual;
    public int id;
    private int linea = 0;
    private RCB recursoUtilizado;
    private String[] programa;
    private ArrayList<Instruccion> listaInstrucciones = new ArrayList<Instruccion>();

    public boolean ejecutar(){
        cambiarEstado("EnEjecucion");
        boolean termino = false;
        boolean noAvanza = false;
        int quantum = Procesador.Current().getQuantum();
        int quantumActual = 0;
        while(!termino && quantumActual < quantum && !noAvanza){
            String instruccionActual = programa[linea];
            int posicionInstruccion = listaInstrucciones.indexOf(new Instruccion(instruccionActual, 0));
            int quantumInstruccion = listaInstrucciones.get(posicionInstruccion).ciclos;
            System.out.println(listaInstrucciones.get(posicionInstruccion).logInstruccion());
            if(this.interaccionRecurso(listaInstrucciones.get(posicionInstruccion))){
                quantumActual += quantumInstruccion;
                linea++;
            }else{
                noAvanza = true;
            }
            if(enEstado("Bloqueado")) noAvanza = true;
            
            
            if(linea == programa.length) termino = true;
            
        }
        return termino;
    }

    public void cambiarEstado(String estado){
        Estado estadoAnterior = estadoActual;
        switch(estado){
            case "Listo": 
                estadoActual = Estado.Listo;
                break;
            case "EnEjecucion": 
                estadoActual = Estado.EnEjecucion;
                break;
            case "Bloqueado": 
                estadoActual = Estado.Bloqueado;
                break;
            default: System.out.println("PCB/cambiarEstado: Escribiste mal el estado gei");;
        }

        System.out.println(imprimirEstado(estadoActual) + razonCambio(estadoActual, estadoAnterior)) ;
    }

    public int getLinea(){
        return linea + 1;
    }

    private String razonCambio(Estado estadoNuevo, Estado estadoAnterior){
        if(estadoNuevo == Estado.EnEjecucion && estadoAnterior == Estado.Listo) {
            return " por despacho.";
        }

        if(estadoNuevo == Estado.Listo && estadoAnterior == Estado.EnEjecucion) {
            return " por timeout.";
        }

        if(estadoNuevo == Estado.Bloqueado && estadoAnterior == Estado.EnEjecucion) {
            return " por uso de recurso (E/S)";
        }

        if(estadoNuevo == Estado.Listo && estadoAnterior == Estado.Bloqueado){
            return " por finalizada ejecucion del recurso ";
        }

        return "PCB/razonCambio/ERROR: Caso no ponderado de cambio de estado. Mira que paso";
    }

    private String imprimirEstado(Estado estado){
        String fin = "";
        switch(estado){
            case EnEjecucion:
                fin = "En Ejecucion";
                break;
            case Listo:
                fin = "Listo";
                break;
            case Bloqueado:
                fin = "Bloqueado";
                break;

        }
        return "El estado de " + this.toString() + " cambio a " + fin;
    }

    private boolean interaccionRecurso (Instruccion ins) {
        String tipo = ins.getTipo();
        switch (tipo) {
            case "Pedir": 
                String nombreRecurso = ins.getIdRecurso();
                return pedirRecurso(nombreRecurso);
            case "Usar": 
                String nombreRecurso1 = ins.getIdRecurso();
                usarRecurso(nombreRecurso1);
                return true;
            case "Devolver": 
                String nombreRecurso2 = ins.getIdRecurso();
                return devolverRecurso(nombreRecurso2);
            default: 
                return true;
        }
    }

    public int getId(){
        return id;
    }

    private boolean pedirRecurso (String nombre) {
        Sistema sistema = Sistema.Current();
        RCB recurso = sistema.getRCB(nombre);
        if(recurso.getDisponibilidad()){
            this.recursoUtilizado = recurso;
            recurso.setProceso(this);
            System.out.println(recurso.getNombre() + " se encuentra disponible, se lo asigna al proceso " + this.id);
            return true;
        }else{
            System.out.println(recurso.getNombre() + " no se encuentra disponible.");
            return false;
        }
        
    }

    private void usarRecurso (String nombre) {
        this.estadoActual = Estado.Bloqueado;
        System.out.println(imprimirEstado(estadoActual) + razonCambio(estadoActual, Estado.EnEjecucion));
        recursoUtilizado.setUso(true);
    }

//PEDIS estaba vacio -> Usar E/S vuelvo -> hago cosas -> paseo el perro -> Devolver?
    private boolean devolverRecurso (String nombre) {
        if(recursoUtilizado.getUso()){
            System.out.println("El " + recursoUtilizado + " sigue en ejecucion");
            return false;
        }
        System.out.println("El " + recursoUtilizado + " fue devuelto");
        recursoUtilizado.setProceso(null);
        recursoUtilizado = null;
        return true;
    }

    public boolean enEstado (String estado) {
        boolean mismoEstado = false;
        switch(estadoActual){
            case Listo: 
                mismoEstado = (estado == "Listo");
                break;
            case EnEjecucion:  
                mismoEstado = (estado == "EnEjecucion");
                break;
            case Bloqueado: 
                mismoEstado = (estado == "Bloqueado");
                break;
            default: System.out.println("PCB/enEstado: Pediste un estado que no existe");
        }
        return mismoEstado;
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