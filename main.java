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

}