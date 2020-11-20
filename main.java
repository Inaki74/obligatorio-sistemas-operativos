import Clases.Sistema;
import Clases.PCB;
import Clases.Procesador;


class main{
    public static void main(String [] args){
        new Sistema();
        new Procesador();

        Sistema.Current().crearProcesos();
        Sistema.Current().cargarTodosProcesos();

        //Loop que use los procesos
        boolean termino = false;
        while(!termino) {
            termino = Procesador.Current().ejecutarProximoProceso();
        }

        System.out.println("No hay mas procesos por ejecutar...");
        System.out.println("EXIT");
    }

}