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

    private Particion[] particiones;
    private ArrayList<PCB> procesos = new ArrayList<PCB>();
    private ArrayList<RCB> recursos = new ArrayList<RCB>();
    private Grafo grafoAsignacionRecursos = new Grafo();
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private String[][] programas;

    /**
     * Cada vez que se pide un recurso, linkeamos de proceso a recurso   P -> R
     * Cada vez que se asigna un recurso a un proceso   R -> P
     * Una vez que un proceso empieza a usar un recurso que le fue asignado, se saca la arista     P -/> R;
     * Una vez que un proceso devuelve un recurso, se saca la arista    R -/> P.
     * Antes de asignar un recurso a un proceso, antes se simula en el grafo que pasaria si ocurre
     *  Ahi nos fijamos si hay un ciclo
     *      Si hay, matamos proceso (y logeamos)
     *      Si no, lo dejamos pasar
     */

    public void casosPruebaDeadlocksMinimo(){
        //Importar programas
        String[][] importarProgs = {{"Pedir impresora#2","B","C","A","B","B", "Pedir impresora#1", "Usar impresora#2", "Usar impresora#1", "Devolver impresora#1", "Devolver impresora#2", "B", "A", "C"}, 
                                {"A","Pedir impresora#1", "A", "D", "B", "A", "Pedir impresora#3","Usar impresora#3", "Devolver impresora#3","C", "B", "A", "C", "B", "A", "C", "Usar impresora#1", "Pedir impresora#2", "Usar impresora#2", "Devolver impresora#2", "Devolver impresora#1", "E"}, 
                                {"P", "P","Pedir impresora#3", "L", "F", "Usar impresora#3", "Devolver impresora#3", "A", "D", "D", "F", "A"}};
        programas = importarProgs;
        //importar recursos y anadirlos con su tiempo de espera
        String[] listaRecursos = {"impresora#1", "impresora#2", "impresora#3"};
        RCB recurso1 = new RCB(listaRecursos[0], 4);
        RCB recurso2 = new RCB(listaRecursos[1], 2);
        RCB recurso3 = new RCB(listaRecursos[2], 6);
        recursos.add(recurso1);
        recursos.add(recurso2);
        recursos.add(recurso3);
        // Crear procesos y los asigna a memoria
        PCB proceso0 = new PCB(0, programas[0], 0);
        PCB proceso1 = new PCB(1, programas[1], 1);
        PCB proceso2 = new PCB(2, programas[2], 2);
        procesos.add(proceso0);
        procesos.add(proceso1);
        procesos.add(proceso2);
        crearParticiones();
        asignarAMemoria(proceso0);
        asignarAMemoria(proceso1);
        asignarAMemoria(proceso2);
        //importar Usuarios y cargarlos en procesos
        String[] importarUsuarios = {"Matixatim Admin", "Inaki.exe User", "GL Guest", "Caffa Admin"};
        for(int i=0; i<importarUsuarios.length; i++){
            String usuario[] = importarUsuarios[i].split(" ");
            boolean[] permisosRecursos = devolverPermisosRecursosCasoX(definirPerfil(usuario[1]));
            boolean[] permisosProgramas = devolverPermisosProgramasCasoX(definirPerfil(usuario[1]));
            Usuario newUsuario = new Usuario(usuario[0], permisosRecursos, permisosProgramas);
            usuarios.add(newUsuario);
        }
        procesos.get(0).setUsuario(usuarios.get(0));
        procesos.get(1).setUsuario(usuarios.get(0));
        procesos.get(2).setUsuario(usuarios.get(0));
        //grafo 
        inicializarGrafo();
    }

    public boolean[] devolverPermisosProgramasCasoX(Perfiles p){
        boolean[] permisosProgramas = new boolean[programas.length];
        switch(p){
            case Admin:
                for(int i=0; i<programas.length; i++){
                    permisosProgramas[i] = true; 
                }
            break;
            case User:
                permisosProgramas[0] = true;
                permisosProgramas[1] = false;
                permisosProgramas[2] = true;
            break;
            case Guest:
                permisosProgramas[0] = false;
                permisosProgramas[1] = false;
                permisosProgramas[2] = true;
            break;
        }
        return permisosProgramas;
    }

    public boolean[] devolverPermisosRecursosCasoX(Perfiles p){
        boolean[] permisosRecursos = new boolean[programas.length];
        switch(p){
            case Admin:
                for(int i=0; i < programas.length; i++){
                    permisosRecursos[i] = true; 
                }
            break;
            case User:
                permisosRecursos[0] = true;
                permisosRecursos[1] = false;
                permisosRecursos[2] = true;
            break;
            case Guest:
                permisosRecursos[0] = false;
                permisosRecursos[1] = false;
                permisosRecursos[2] = true;
            break;
        }
        return permisosRecursos;
    }
//

    // Get recurso
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

    //Get proceso
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

    //Se activa despues de cada quantum
    public void avanzarRecursos(){
        // Lista RCB + 1 en ese quantum a todos lo que esten siendo utilizados
        // Avanzas en todos los RCBs, si uno termina pasa su Proceso de Bloqueado a Listo y su tiempo actual se settea de nuevo a 0 y su uso en false
        for(int i = 0; i < recursos.size(); i++){
            RCB recurso = recursos.get(i);
            if(recurso.getUso()){
                recurso.avanzar();
                if(recurso.termino()){
                    PCB proceso = recurso.getProceso();
                    System.out.println(colores.ANSI_WHITE_BOLD + "Sistema/avanzarRecursos: Se termino de usar el " + recurso + colores.ANSI_RESET);
                    recurso.setTiempoAcutal(0);
                    recurso.setUso(false);
                    proceso.cambiarEstado("Listo");
                }
            }
        }
    }

    public void resetSistema(){
        procesos.clear();
        recursos.clear();
        usuarios.clear();
        grafoAsignacionRecursos.vaciarGrafo();
        // String[][] programas;
        // particiones[]
    }
    
    //---------------------Usuarios info -------------------------
    public enum Perfiles{
        Admin,
        User,
        Guest,
    }

    public Perfiles definirPerfil(String perfil){
        Perfiles p = Perfiles.Guest;
        switch(perfil){
            case "Admin": 
                p = Perfiles.Admin;
                break;
            case "User": 
                p = Perfiles.User;
                break;
            case "Guest": 
                p = Perfiles.Guest;
                break;
            default: System.out.println(colores.ANSI_RED + "error al definir perfil" + colores.ANSI_RESET);
        }
        return p;
    }
    
    //----------Funciones grafo (deadlocks) -----------------------
    public void agregarVerticeAGrafo(int id, boolean esProceso){
        Vertex v = new Vertex(id, esProceso);
        grafoAsignacionRecursos.addVertex(v);
    }

    public void agregarAristaAGrafo(int id1, boolean esProceso1, int id2, boolean esProceso2){
        Vertex v1 = new Vertex(id1, esProceso1);
        Vertex v2 = new Vertex(id2, esProceso2);
        grafoAsignacionRecursos.addEdge(v1,v2);
    }

    public void removerArista(int id1, boolean esProceso1, int id2, boolean esProceso2){
        Vertex v1 = new Vertex(id1, esProceso1);
        Vertex v2 = new Vertex(id2, esProceso2);
        grafoAsignacionRecursos.removeEdge(v1, v2);
    }

    public boolean hayArista(int id1, boolean esProceso1, int id2, boolean esProceso2){
        Vertex desde = new Vertex(id1, esProceso1);
        Vertex hasta = new Vertex(id2, esProceso2);
        return grafoAsignacionRecursos.hayArista(desde, hasta);
    }

    public boolean verCiclo(int id, boolean esProceso){
        Vertex ppio = new Vertex(id, esProceso);
        return grafoAsignacionRecursos.esCiclico(ppio);
    }

    public void inicializarGrafo(){
        for(PCB p: procesos){
            grafoAsignacionRecursos.addVertex(new Vertex(p.getId(), true));
        }

        for(RCB r: recursos){
            grafoAsignacionRecursos.addVertex(new Vertex(r.getId(), false));
        }
    }

    //----------Funciones Memoria (particiones fijas) -----------------------
    public void crearParticiones(){
        int cantidadProgramas = programas.length;

        particiones = new Particion[cantidadProgramas];

        for(int i = 0; i < cantidadProgramas; i++){
            Particion p = new Particion(2, i);
            particiones[i] = p;
        }
    }

    public void asignarAMemoria(PCB proceso){
        for(int i = 0; i < programas.length; i++){
            if(particiones[i].getIdPrograma() == proceso.getIdPrograma()){
                particiones[i].agregarAMemoria(proceso);
            }
        }
    }

    public void removerMemoria(PCB proceso){
        for(int i = 0; i < programas.length; i++){
            if(particiones[i].getIdPrograma() == proceso.getIdPrograma()){
                particiones[i].removerDeMemoria(proceso);
            }
        }
    }
}
