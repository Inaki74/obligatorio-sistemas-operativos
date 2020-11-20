# OBLIGATORIO SISTEMAS OPERATIVOS

---------------------------------
---------------------------------
#### Docente: Angel Caffa
#### Grupo: M4D
#### Alumnos:
- #### Matías Gonzalez (219329)
- #### Gastón Landeira (238473)
- #### Iñaki Etchegaray (241072)


## TECNOLOGIA:

Nosotros para realizar la simulacion, utilizamos el lenguaje de programacion JAVA en su última version (buscarversion). Codificamos en VSCode y mantuvimos un versionado por un repositorio en GitHub.

## ACERCAMIENTO AL PROBLEMA

Para realizar la simulacion, nosotros tuvimos un acercamiento Iterativo. Es decir, creabamos distintas versiones de la solucion y la ibamos mejorando por iteracion.

### PRIMERA ITERACION

En la primera iteracion, definimos la base del Simulador. Creamos las clases basicas, como por ejemplo: Sistema, Procesador, PCB, RCB(base nomas), Instruccion y main.

Quisimos simular la dinamica de scheduling y round robin del procesador con procesos estaticos, sin recursos y un solo usuario, respetando los timeouts de los procesos. 

Para ello, definimos el quantum del procesador, insertamos las instrucciones y le asignamos una cantidad de ciclos determinada pero aleatoria, el loop principal, el guardado de los procesos en sistema, sus estados (Listo y En Ejecucion) y el log en consola de los mismos.

### Primer set de Pruebas:
<img src="imagenes_readme/iteracion1_imagen1_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/iteracion1_imagen2_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

El resultado loggeado fue el siguiente:

<img src="imagenes_readme/iteracion1_imagen3_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/iteracion1_imagen4_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/iteracion1_imagen5_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Como una primera iteracion del programa, nos sastiface mucho y cumple los objetivos que nos pusimos. (TEMA INSTRUCCIONES CON DISTINTA DURACION)
### N Iteracion

### ESPECIALIZACION

Nostros decidimos especializar nuestro SO en...


## PRUEBAS:

### CASOS DE PRUEBA PROPORCIONADOS: (Datos cargados)




## CLASES

Proceso ejecuta programa

Programa = lista de Instrucciones = texto => programa lista de texto

Sistema -> Singleton  <— procesador
Proceso -> Hay que identificar todo lo que tienen los procesos.
Recurso <— Serialmente Reutilizable y No Reutilizable
Procesador extends Recurso <--- Singleton
Usuario(permiso) 		En una version mas adelantada, pueden tener contraseña y tienen que ser creados, por ahora ya van a estar cargados.

Sistema -> proceso -> usuario

### Sistema:
- La clase principal.
- Tiene una lista de Usuarios, de Procesos y Recursos.
- Da el procesador (avisa de esto) y avisa que fue devuelto.
- Matriz de permisos

### Proceso
- Comparten el procesador.
- Tienen que ser sincronizados respecto a los recursos.
- Solicitan y “Utilizan” recursos (con un system.log) y después lo “devuelven”.
- Tiene código adentro. (Lista de instrucciones?) Un super string.
- Tiempo de ejecución.
- Si el proceso pide un recurso y se encuentra utilizado, debe de esperar a que se libere.

### Recurso
- Tienen mutua exclusión.
- Son utilizados por los procesos.
- Timeout?
- Pueden estar ocupados.

### Procesador
- Es un recurso concurrente.
- Procesos los toman, lo utilizan por algunos ciclos.

### Usuario
- Existe
- Tiene permisos
- Contraseña y nombre ?

Alice : Proceso
Impresora : Recurso

### PREGUNTAS:

- Tomamos el procesador como un recurso? Tiene mutua exclusion?
- Son los programas un texto largo, un pseudocódigo, o tiene que realmente hacer cosas? Código existente?
- Simulamos tiempos de espera? (Ejemplo, si simulamos uso de una impresora, hacemos un timeout artificial?)
- Mutua exclusion, limita necesariamente a ser utilizado por un solo proceso? Ejemplo de patio
