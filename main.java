import Clases.Sistema;
import Clases.Procesador;
import Clases.colores;

class main{
    public static void main (String [] args){
        new Sistema();
        new Procesador();

        Sistema sistema = Sistema.Current();
        sistema.crearProcesos();
        sistema.crearRecursos();
        sistema.cargarTodosProcesos();

        //Loop que use los procesos
        boolean termino = false;
        while(!termino) {
            termino = Procesador.Current().ejecutarProximoProceso();

           sistema.avanzarRecursos();
        }

        System.out.println(colores.ANSI_RED + "No hay mas procesos por ejecutar..." + colores.ANSI_RESET);
        System.out.println(colores.ANSI_RED + "EXIT" + colores.ANSI_RESET);
    }


    /**
     * Logica de usuario:
     * El usuario tiene permisos a procesos y recursos, para esto necesitamos
     * Matriz de bools (True = acceso, False = no) de usuarios y recursos
     * Matriz de bools "" de usuarios y procesos
     * 
     * Uso de las matrices:
     * Cuando vas a ejecutar un proceso ver en la posicion M[u][p] si el usuario tiene permiso, si no tiene printear "No tiene acceso a este proceso" y seguir con el proximo en RR
     * Cuando vas adentro de un proceso vas a PEDIR un recurso fijarse si el usuario tiene permiso para utilizar el recurso, Si lo tiene avanza normal, si no matar proceso
     * 
     * IDEAS:
     * Funcion en sistema permisoRecurso(usuario, recurso)
     * Funcion en sistema permisoProceso(usuario, proceso)
     * Funcion en PCB matarProceso que lo termine y lo saque del scheduller
     * Fijarse si usuario tiene permiso afuera del PCB, si no lo tiene no ejecutar el proceso directamente
     * Fijarse si el usuario tiene permiso para usar el recurso, si no lo tiene, llamar a matarProceso, y sacar proceso de Round Robin 
     */
}