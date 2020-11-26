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


    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private String[][] programas;

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

    public void ImportarProgramas(){
        String[][] progs = { {"Pedir impresora#2", "Usar impresora#2", "Pedir impresora#1", "Usar impresora#1", "Devolver impresora#1", "Devolver impresora#2", "B", "A", "C"}, 
                                 {"A", "A", "D","Pedir impresora#1", "B", "A", "C", "B", "A", "C", "B", "A", "C", "Usar impresora#1", "Pedir impresora#2", "Usar impresora#2", "Devolver impresora#2", "Devolver impresora#1", "E"}, 
                                 {"P", "P", "L", "F","Pedir impresora#3", "Usar impresora#3", "Devolver impresora#3", "A", "D", "D", "F", "A"}};

        programas = progs;
    }

    public String[] importarRecursos(){
        String[] recursos = {"impresora#1", "impresora#2", "impresora#3"};
        
        return recursos;
    }
    
    //usuarios
    public String[] importarUsuarios(){
        String[] usuarios = {"Matixatim Admin", "Inaki.exe User", "GL Guest", "Caffa Admin"};
        
        return usuarios;
    }

    private void cargarUsuariosEnProcesos(){
        procesos.get(0).setUsuario(usuarios.get(0));
        procesos.get(1).setUsuario(usuarios.get(0));
        procesos.get(2).setUsuario(usuarios.get(0));
        procesos.get(3).setUsuario(usuarios.get(0));
        procesos.get(4).setUsuario(usuarios.get(0));
        procesos.get(5).setUsuario(usuarios.get(1));
        // for(int i=0; i < procesos.size(); i++){
        //     procesos.get(i).setUsuario(usuarios.get(2)); //por ahora todos son admin
        // }
    }

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

    public boolean[] devolverPermisosProgramas(Perfiles p){
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

    public boolean[] devolverPermisosRecursos(Perfiles p){
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
        // agregar usuario
        PCB proceso0 = new PCB(0, programas[0], 0);
        PCB proceso1 = new PCB(1, programas[0], 0);
        PCB proceso2 = new PCB(2, programas[0], 0);
        PCB proceso3 = new PCB(3, programas[1], 1);
        PCB proceso4 = new PCB(4, programas[2], 2);
        PCB proceso5 = new PCB(5, programas[2], 2);

        procesos.add(proceso0);
        procesos.add(proceso1);
        procesos.add(proceso2);
        procesos.add(proceso3);
        procesos.add(proceso4);
        procesos.add(proceso5);

        asignarAMemoria(proceso0);
        asignarAMemoria(proceso1);
        asignarAMemoria(proceso2);
        asignarAMemoria(proceso3);
        asignarAMemoria(proceso4);
        asignarAMemoria(proceso5);
    }

    public void crearUsuarios() {
        String[] listaUsuarios = importarUsuarios();

        for(int i=0; i<listaUsuarios.length; i++){
            String usuario[] = listaUsuarios[i].split(" ");
            boolean[] permisosRecursos = devolverPermisosRecursos(definirPerfil(usuario[1]));
            boolean[] permisosProgramas = devolverPermisosProgramas(definirPerfil(usuario[1]));
            Usuario newUsuario = new Usuario(usuario[0], permisosRecursos, permisosProgramas);
            usuarios.add(newUsuario);
        }
        cargarUsuariosEnProcesos();
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
}