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

#### PRUEBAS:
<img src="imagenes_readme/iteracion1_imagen1_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/iteracion1_imagen2_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

En la imagen a continuacion se ve parte de lo loggeado. A efectos de simplificar el README, pusimos solo partes de las ejecuciones. Para acceder a los resultados completos, ver el anexo.md.

<img src="imagenes_readme/iteracion1_imagen4_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />


Como una primera iteración del programa, nos sastiface mucho y cumple los objetivos que nos pusimos.

### SEGUNDA ITERACIÓN

En la segunda iteración, decidimos empezar a desarrollar el manejo de Recursos Serialmente Reusables. Para ello, nos ideamos un primer recurso ejemplo y trabajamos desde ahi, pero siempre pensando en la multiplicidad de recursos.

Definimos el RCB, el estado de Bloqueado de un Proceso y sus distintas interacciones con el código ya hecho. Para controlar estas interacciones, definimos los primeros tres comandos de nuestro lenguaje: Pedir, Usar y Devolver.

Ademas, decidimos agregar un código de colores al log, ya que se estaba tornando muy largo, tedioso e intentendible. A continuacion se encuentra una leyenda de los colores definidos:

- Verde: Manejo de Procesos y Schedulling.
- Cyan: Cambio de estado de los Procesos.
- Blanco: Ejecucion de instrucciones.
- Blanco Fuerte (Bold): Manejo de Recursos.
- Amarillo: Advertencias de procesos no finalizados y recursos aun utilizados.
- Rojo: Errores que no deberían de ocurrir.

Una vez comprobado su funcionalidad con un RSR solo, decidimos agregar casos de prueba con varios RSR. Aun no fueron implementados manejos de Deadlocks ni creacion de Procesos y Recursos.

#### PRUEBAS:

Los datos de prueba para esta iteración fueron estaticos. A continuación se muestran:

<img src="imagenes_readme/iteracion2_imagen1_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/iteracion2_imagen2_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Y la imágen siguiente muestra parte de los resultados de la prueba. De nuevo, la prueba entera se encuentra en anexo.md en este mismo repositorio.

<img src="imagenes_readme/iteracion2_imagen3_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

De nuevo, analizando el caso y los resultados, son todos favorables. Incluso un caso borde que aparece funciona como deberia. El mismo es que un Proceso entre en timeout luego de pedir un recurso, el resultado esperado es que se acceda al recurso y quede bloqueado, pero no realizando su función ya que no se le dio el comando de ser utilizado. Sucede lo esperado.


//- Violeta: Manejo de permisos.
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
