import Clases.Sistema;
import Clases.Procesador;


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

        System.out.println("No hay mas procesos por ejecutar...");
        System.out.println("EXIT");
    }

}