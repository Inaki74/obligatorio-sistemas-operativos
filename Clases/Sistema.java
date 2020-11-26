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

    public void casosPruebaDeadlocks1(){
        //Importar programas
        String[][] importarProgs = {{"Pedir impresora#2","B","C","A","B","B", "Pedir impresora#1", "Usar impresora#2", "Usar impresora#1", "Devolver impresora#1", "Devolver impresora#2", "B", "A", "C"}, 
                                {"A","Pedir impresora#1", "A", "D", "B", "A", "Pedir impresora#2","B","C", "Usar impresora#2", "Usar impresora#1", "Devolver impresora#2", "Devolver impresora#1", "B", "A", "C"}};
                              
        programas = importarProgs;
        //importar recursos y anadirlos con su tiempo de espera
        String[] listaRecursos = {"impresora#1", "impresora#2"};
        RCB recurso1 = new RCB(listaRecursos[0], 4);
        RCB recurso2 = new RCB(listaRecursos[1], 2);
        recursos.add(recurso1);
        recursos.add(recurso2);
        // Crear procesos y los asigna a memoria
        PCB proceso0 = new PCB(0, programas[0], 0);
        PCB proceso1 = new PCB(1, programas[1], 1);
        procesos.add(proceso0);
        procesos.add(proceso1);
        crearParticiones();
        asignarAMemoria(proceso0);
        asignarAMemoria(proceso1);
        //importar Usuarios y cargarlos en procesos
        String[] importarUsuarios = {"Matixatim Admin"};
        for(int i=0; i<importarUsuarios.length; i++){
            String stringUsuario[] = importarUsuarios[i].split(" ");
            boolean[] permisosRecursos = devolverPermisosRecursosCasoX(definirPerfil(stringUsuario[1]));
            boolean[] permisosProgramas = devolverPermisosProgramasCasoX(definirPerfil(stringUsuario[1]));
            Usuario newUsuario = new Usuario(stringUsuario[0], permisosRecursos, permisosProgramas);
            usuarios.add(newUsuario);
        }
        procesos.get(0).setUsuario(usuarios.get(0));
        procesos.get(1).setUsuario(usuarios.get(0));
        //grafo 
        inicializarGrafo();
    }

    public void casosPruebaDeadlocks2(){
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
        String[] importarUsuarios = {"Matixatim Admin"};
        for(int i=0; i<importarUsuarios.length; i++){
            String stringUsuario[] = importarUsuarios[i].split(" ");
            boolean[] permisosRecursos = devolverPermisosRecursosCasoX(definirPerfil(stringUsuario[1]));
            boolean[] permisosProgramas = devolverPermisosProgramasCasoX(definirPerfil(stringUsuario[1]));
            Usuario newUsuario = new Usuario(stringUsuario[0], permisosRecursos, permisosProgramas);
            usuarios.add(newUsuario);
        }
        procesos.get(0).setUsuario(usuarios.get(0));
        procesos.get(1).setUsuario(usuarios.get(0));
        procesos.get(2).setUsuario(usuarios.get(0));
        //grafo 
        inicializarGrafo();
    }

    public void casosPruebaDeadlocks3(){
        //Importar programas
        String[][] importarProgs = {{"Pedir impresora#1", "A","B","C","A","A","Pedir variableGlobalY", "Usar variableGlobalY" , "Usar impresora#1","Devolver variableGlobalY", "A","D","B","A","B","Devolver impresora#1"}, 
                                    {"Pedir variableGlobalY","Pedir impresora#3", "Usar impresora#3" , "Devolver impresora#3", "Usar variableGlobalY" , "A","B","C","A","A","B","C","Pedir variableGlobalX", "Usar variableGlobalX" , "Devolver variableGlobalX", "Devolver variableGlobalY"}, 
                                    {"Pedir variableGlobalX", "Usar variableGlobalX" ,  "A","B","C","A","A", "A","B","C","A","A","Pedir impresora#1", "Usar impresora#1", "Devolver impresora#1", "Devolver variableGlobalX"}};
        programas = importarProgs;
        //importar recursos y anadirlos con su tiempo de espera
        String[] listaRecursos = {"impresora#1", "impresora#2", "impresora#3", "variableGlobalX", "variableGlobalY"};
        RCB recurso1 = new RCB(listaRecursos[0], 4);
        RCB recurso2 = new RCB(listaRecursos[1], 2);
        RCB recurso3 = new RCB(listaRecursos[2], 6);
        RCB recurso4 = new RCB(listaRecursos[3], 3);
        RCB recurso5 = new RCB(listaRecursos[4], 2);
        recursos.add(recurso1);
        recursos.add(recurso2);
        recursos.add(recurso3);
        recursos.add(recurso4);
        recursos.add(recurso5);
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
        String[] importarUsuarios = {"Matixatim Admin"};
        for(int i=0; i<importarUsuarios.length; i++){
            String stringUsuario[] = importarUsuarios[i].split(" ");
            boolean[] permisosRecursos = devolverPermisosRecursosCasoX(definirPerfil(stringUsuario[1]));
            boolean[] permisosProgramas = devolverPermisosProgramasCasoX(definirPerfil(stringUsuario[1]));
            Usuario newUsuario = new Usuario(stringUsuario[0], permisosRecursos, permisosProgramas);
            usuarios.add(newUsuario);
        }
        procesos.get(0).setUsuario(usuarios.get(0));
        procesos.get(1).setUsuario(usuarios.get(0));
        procesos.get(2).setUsuario(usuarios.get(0));
        //grafo 
        inicializarGrafo();
    }

    //done
    public void casosPruebaUsuarios1(){
        //Importar programas
        String[][] importarProgs = {{"B","C","A","B","B", "Pedir impresora#1","B", "Usar impresora#1", "Devolver impresora#1", "B", "A", "C"}, 
                                {"A", "A", "D", "B", "A", "Pedir impresora#3","Usar impresora#3", "Devolver impresora#3"}, 
                                {"P", "P","Pedir impresora#2", "L", "F", "Usar impresora#2", "Devolver impresora#2", "A", "D", "D", "F", "A"}};
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
        PCB proceso3 = new PCB(3, programas[1], 1);
        PCB proceso4 = new PCB(4, programas[0], 0);
        PCB proceso5 = new PCB(5, programas[2], 2);
        PCB proceso6 = new PCB(6, programas[2], 2);
        procesos.add(proceso0);
        procesos.add(proceso1);
        procesos.add(proceso2);
        procesos.add(proceso3);
        procesos.add(proceso4);
        procesos.add(proceso5);
        procesos.add(proceso6);
        crearParticiones();
        asignarAMemoria(proceso0);
        asignarAMemoria(proceso1);
        asignarAMemoria(proceso2);
        asignarAMemoria(proceso3);
        asignarAMemoria(proceso4);
        asignarAMemoria(proceso5);
        asignarAMemoria(proceso6);
        //importar Usuarios y cargarlos en procesos
        String[] importarUsuarios = {"Matixatim Admin", "Inaki.exe User", "GL Guest", "Caffa Guest"};
        for(int i=0; i<importarUsuarios.length; i++){
            String stringUsuario[] = importarUsuarios[i].split(" ");
            boolean[] permisosRecursos = devolverPermisosRecursosCasoX(definirPerfil(stringUsuario[1]));
            boolean[] permisosProgramas = devolverPermisosProgramasCasoX(definirPerfil(stringUsuario[1]));
            Usuario newUsuario = new Usuario(stringUsuario[0], permisosRecursos, permisosProgramas);
            usuarios.add(newUsuario);
        }
        procesos.get(0).setUsuario(usuarios.get(0));
        procesos.get(1).setUsuario(usuarios.get(1));
        procesos.get(2).setUsuario(usuarios.get(2));
        procesos.get(3).setUsuario(usuarios.get(2));
        procesos.get(4).setUsuario(usuarios.get(3));
        procesos.get(5).setUsuario(usuarios.get(2));
        procesos.get(6).setUsuario(usuarios.get(2));
        //grafo 
        inicializarGrafo();
    }

    //done
    public void casosPruebaAsignacionDeMemoria1(){
        //Importar programas
        String[][] importarProgs = {{"A","B","C","D"},
                                    {"B", "A", "D"},
                                    {"C", "E", "A"},
                                    {"D", "A", "B"},
                                    {"E", "A", "D"}};
        programas = importarProgs;
        //importar recursos y anadirlos con su tiempo de espera
        //No hay recursos
        // Crear procesos y los asigna a memoria
        PCB proceso0 = new PCB(0, programas[0], 0);
        PCB proceso1 = new PCB(1, programas[1], 1);
        PCB proceso2 = new PCB(2, programas[2], 2);
        PCB proceso3 = new PCB(3, programas[0], 3);
        PCB proceso4 = new PCB(4, programas[1], 2);
        PCB proceso5 = new PCB(5, programas[2], 2);
        PCB proceso6 = new PCB(6, programas[0], 0);
        PCB proceso7 = new PCB(7, programas[1], 1);
        PCB proceso8 = new PCB(8, programas[2], 4);
        PCB proceso9 = new PCB(9, programas[0], 4);
        PCB proceso10 = new PCB(10, programas[1], 2);
        PCB proceso11= new PCB(11, programas[2], 2);
        procesos.add(proceso0);
        procesos.add(proceso1);
        procesos.add(proceso2);
        procesos.add(proceso3);
        procesos.add(proceso4);
        procesos.add(proceso5);
        procesos.add(proceso6);
        procesos.add(proceso7);
        procesos.add(proceso8);
        procesos.add(proceso9);
        procesos.add(proceso10);
        procesos.add(proceso11);
        crearParticiones();
        asignarAMemoria(proceso0);
        asignarAMemoria(proceso1);
        asignarAMemoria(proceso2);
        asignarAMemoria(proceso3);
        asignarAMemoria(proceso4);
        asignarAMemoria(proceso5);
        asignarAMemoria(proceso6);
        asignarAMemoria(proceso7);
        asignarAMemoria(proceso8);
        asignarAMemoria(proceso9);
        asignarAMemoria(proceso10);
        asignarAMemoria(proceso11);
        //importar Usuarios y cargarlos en procesos
        String[] importarUsuarios = {"Matixatim Admin"};
        for(int i=0; i<importarUsuarios.length; i++){
            String stringUsuario[] = importarUsuarios[i].split(" ");
            boolean[] permisosRecursos = devolverPermisosRecursosCasoX(definirPerfil(stringUsuario[1]));
            boolean[] permisosProgramas = devolverPermisosProgramasCasoX(definirPerfil(stringUsuario[1]));
            Usuario newUsuario = new Usuario(stringUsuario[0], permisosRecursos, permisosProgramas);
            usuarios.add(newUsuario);
        }
        procesos.get(0).setUsuario(usuarios.get(0));
        procesos.get(1).setUsuario(usuarios.get(0));
        procesos.get(2).setUsuario(usuarios.get(0));
        procesos.get(3).setUsuario(usuarios.get(0));
        procesos.get(4).setUsuario(usuarios.get(0));
        procesos.get(5).setUsuario(usuarios.get(0));
        procesos.get(6).setUsuario(usuarios.get(0));
        procesos.get(7).setUsuario(usuarios.get(0));
        procesos.get(8).setUsuario(usuarios.get(0));
        procesos.get(9).setUsuario(usuarios.get(0));
        procesos.get(10).setUsuario(usuarios.get(0));
        procesos.get(11).setUsuario(usuarios.get(0));
        //grafo 
        inicializarGrafo();
    }

    //done
    public void casosPruebaAsignacionDeMemoria2(){
        //Importar programas
        String[][] importarProgs = {{"A","B","C","D"}};
        programas = importarProgs;
        //importar recursos y anadirlos con su tiempo de espera
        //no hay recursos
        // Crear procesos y los asigna a memoria
        PCB proceso0 = new PCB(0, programas[0], 0);
        PCB proceso1 = new PCB(1, programas[0], 0);
        PCB proceso2 = new PCB(2, programas[0], 0);
        PCB proceso3 = new PCB(3, programas[0], 0);
        PCB proceso4 = new PCB(4, programas[0], 0);
        PCB proceso5 = new PCB(5, programas[0], 0);
        PCB proceso6 = new PCB(6, programas[0], 0);
        PCB proceso7 = new PCB(7, programas[0], 0);
        PCB proceso8 = new PCB(8, programas[0], 0);
        PCB proceso9 = new PCB(9, programas[0], 0);
        PCB proceso10 = new PCB(10, programas[0], 0);
        procesos.add(proceso0);
        procesos.add(proceso1);
        procesos.add(proceso2);
        procesos.add(proceso3);
        procesos.add(proceso4);
        procesos.add(proceso5);
        procesos.add(proceso6);
        procesos.add(proceso7);
        procesos.add(proceso8);
        procesos.add(proceso9);
        procesos.add(proceso10);
        crearParticiones();
        asignarAMemoria(proceso0);
        asignarAMemoria(proceso1);
        asignarAMemoria(proceso2);
        asignarAMemoria(proceso3);
        asignarAMemoria(proceso4);
        asignarAMemoria(proceso5);
        asignarAMemoria(proceso6);
        asignarAMemoria(proceso7);
        asignarAMemoria(proceso8);
        asignarAMemoria(proceso9);
        asignarAMemoria(proceso10);
        //importar Usuarios y cargarlos en procesos
        String[] importarUsuarios = {"wideMatixatim Admin"};
        for(int i=0; i<importarUsuarios.length; i++){
            String stringUsuario[] = importarUsuarios[i].split(" ");
            boolean[] permisosRecursos = devolverPermisosRecursosCasoX(definirPerfil(stringUsuario[1]));
            boolean[] permisosProgramas = devolverPermisosProgramasCasoX(definirPerfil(stringUsuario[1]));
            Usuario newUsuario = new Usuario(stringUsuario[0], permisosRecursos, permisosProgramas);
            usuarios.add(newUsuario);
        }
        for(int i=0; i<=10; i++){
            procesos.get(i).setUsuario(usuarios.get(0));
        }
        //grafo 
        inicializarGrafo();
    }

    public void casosPruebaGeneral(){
        //Importar programas
        String[][] importarProgs = {{"Pedir impresora#2","B","C","A","B","B", "Pedir impresora#1", "Usar impresora#2", "Usar impresora#1", "Devolver impresora#1", "Devolver impresora#2", "B", "A", "C"}, 
                                {"A","Pedir impresora#1", "A", "D", "B", "A", "Pedir variableGlobalX","Usar variableGlobalX", "Devolver variableGlobalX","C", "B", "A", "C", "B", "A", "C", "Usar impresora#1", "Pedir impresora#2", "Usar impresora#2", "Devolver impresora#2", "Devolver impresora#1", "E"}, 
                                {"P", "P","Pedir variableGlobalX", "L", "F", "Usar variableGlobalX", "Devolver variableGlobalX", "A", "D", "D", "F", "A"}};
        programas = importarProgs;
        //importar recursos y anadirlos con su tiempo de espera
        String[] listaRecursos = {"impresora#1", "impresora#2", "variableGlobalX"};
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
        PCB proceso3 = new PCB(3, programas[1], 1);
        PCB proceso4 = new PCB(4, programas[0], 0);
        PCB proceso5 = new PCB(5, programas[2], 2);
        PCB proceso6 = new PCB(6, programas[2], 2);
        PCB proceso7 = new PCB(7, programas[2], 2);
        PCB proceso8 = new PCB(8, programas[2], 2);
        procesos.add(proceso0);
        procesos.add(proceso1);
        procesos.add(proceso2);
        procesos.add(proceso3);
        procesos.add(proceso4);
        procesos.add(proceso5);
        procesos.add(proceso6);
        procesos.add(proceso7);
        procesos.add(proceso8);
        crearParticiones();
        asignarAMemoria(proceso0);
        asignarAMemoria(proceso1);
        asignarAMemoria(proceso2);
        asignarAMemoria(proceso3);
        asignarAMemoria(proceso4);
        asignarAMemoria(proceso5);
        asignarAMemoria(proceso6);
        asignarAMemoria(proceso7);
        asignarAMemoria(proceso8);
        //importar Usuarios y cargarlos en procesos
        String[] importarUsuarios = {"Matixatim Admin", "Inaki.exe User", "GL Guest", "Caffa Admin"};
        for(int i=0; i<importarUsuarios.length; i++){
            String stringUsuario[] = importarUsuarios[i].split(" ");
            boolean[] permisosRecursos = devolverPermisosRecursosCasoX(definirPerfil(stringUsuario[1]));
            boolean[] permisosProgramas = devolverPermisosProgramasCasoX(definirPerfil(stringUsuario[1]));
            Usuario newUsuario = new Usuario(stringUsuario[0], permisosRecursos, permisosProgramas);
            usuarios.add(newUsuario);
        }
        procesos.get(0).setUsuario(usuarios.get(0));
        procesos.get(1).setUsuario(usuarios.get(1));
        procesos.get(2).setUsuario(usuarios.get(2));
        procesos.get(3).setUsuario(usuarios.get(2));
        procesos.get(4).setUsuario(usuarios.get(3));
        procesos.get(5).setUsuario(usuarios.get(0));
        procesos.get(6).setUsuario(usuarios.get(3));
        procesos.get(7).setUsuario(usuarios.get(3));
        procesos.get(8).setUsuario(usuarios.get(2));
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
        boolean[] permisosRecursos = new boolean[recursos.size()];
        switch(p){
            case Admin:
                for(int i=0; i < recursos.size(); i++){
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
        RCB recurso = new RCB("reset", 1);
        recurso.resetID();
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