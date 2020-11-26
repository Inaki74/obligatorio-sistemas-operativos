import Clases.Sistema;
import java.util.Scanner;
import Clases.Procesador;
import Clases.colores;

class main{
    public static void main (String [] args){
        new Sistema();
        new Procesador();

        Sistema sistema = Sistema.Current();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println(colores.ANSI_WHITE_BOLD + " Simulador de un sistema operativo" + colores.ANSI_RESET);
        System.out.println(colores.ANSI_WHITE + "Bienvenido a la interfaz por consola" + colores.ANSI_RESET);
        System.out.println("Hecho por " + colores.ANSI_WHITE_BOLD + "Iñaki Etchegaray, Matías González y Gastón Landeira" + colores.ANSI_RESET);
        while(!exit){
            System.out.println("-------------------------------------------------------------");
            System.out.println("Inserte el " + colores.ANSI_WHITE_BOLD + "número " + colores.ANSI_RESET + "de opción que desea elegir");
            System.out.println("1 -> Casos de prueba");
            System.out.println("2 -> Descripcion de los Casos de Prueba");
            System.out.println("3 -> Salir del programa");
            System.out.println("");

            int entrada = scanner.nextInt();
            
            switch (entrada){
                case 1:
                    menuPrueba(scanner, sistema);
                break;
                case 2:
                    menuAyuda(scanner);
                break;
                case 3:
                    exit = true;
                break;
            }
        }
        scanner.close();   
    }

    public static void menuPrueba(Scanner scanner, Sistema sistema){
        boolean exitPrueba = false;
        while(!exitPrueba){
            System.out.println("");
            System.out.println("Inserte el " + colores.ANSI_WHITE_BOLD + "número " + colores.ANSI_RESET + "de opción que desea elegir");
            System.out.println("1 -> Prueba deadlocks Básica");
            System.out.println("2 -> Prueba deadlocks Avanzada 1");
            System.out.println("3 -> Prueba deadlocks Avanzada 2");
            System.out.println("4 -> Prueba usuarios");
            System.out.println("5 -> Prueba asignación de memoria Básica");
            System.out.println("6 -> Prueba asignación de memoria Avanzada");
            System.out.println("7 -> Prueba General");
            System.out.println("8 -> Salir de los casos de prueba");
            System.out.println("");

            int entrada = scanner.nextInt();
            System.out.println("");

            if(entrada >= 1 && entrada <= 7){
                System.out.println(colores.ANSI_WHITE_BOLD + "Inicio de ejecución" + colores.ANSI_RESET);
            }
            switch (entrada){
                case 1:
                    sistema.casosPruebaDeadlocks1();
                break;
                case 2:
                    sistema.casosPruebaDeadlocks2();
                break;
                case 3:
                    sistema.casosPruebaDeadlocks3();
                break;
                case 4:
                    sistema.casosPruebaUsuarios1();
                break;
                case 5:
                    sistema.casosPruebaAsignacionDeMemoria1();
                break;
                case 6:
                    sistema.casosPruebaAsignacionDeMemoria2();
                break;
                case 7:
                    sistema.casosPruebaGeneral();
                break;
                case 8:
                    exitPrueba = true;
                break;
            }
            if(!exitPrueba){
                boolean termino = false;
                while(!termino) {
                    termino = Procesador.Current().ejecutarProximoProceso();
        
                    sistema.avanzarRecursos();
                }
                System.out.println(colores.ANSI_RED + "No hay más procesos por ejecutar..." + colores.ANSI_RESET);
                System.out.println(colores.ANSI_RED + "EXIT" + colores.ANSI_RESET);
                sistema.resetSistema();
            }
        }
    }

    public static void menuAyuda(Scanner scanner){
        boolean exitAyuda = false;
        while(!exitAyuda){
            System.out.println("");
            System.out.println("Inserte el " + colores.ANSI_WHITE_BOLD + "número " + colores.ANSI_RESET + "de opción que desea elegir");
            System.out.println("1 -> Prueba deadlocks Básica explicación");
            System.out.println("2 -> Prueba deadlocks Avanzada 1 explicación");
            System.out.println("3 -> Prueba deadlocks Avanzada 2 explicación");
            System.out.println("4 -> Prueba usuarios explicación");
            System.out.println("5 -> Prueba asignación de memoria Básica explicación");
            System.out.println("6 -> Prueba asignación de memoria Avanzada explicación");
            System.out.println("7 -> Prueba General explicación");
            System.out.println("8 -> Salir de la sección de ayuda");
            System.out.println("");

            int entrada = scanner.nextInt();
            System.out.println("");

            switch (entrada){
                case 1:
                    System.out.println("");
                break;
                case 2:
                    System.out.println("");
                break;
                case 3:
                    System.out.println("");
                break;
                case 4:
                    System.out.println("");
                break;
                case 5:
                    System.out.println("");
                break;
                case 6:
                    System.out.println("");
                break;
                case 7:
                    System.out.println("");
                break;
                case 8:
                    exitAyuda = true;
                break;
            }
        }
    }
}