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

### TERCERA ITERACIÓN

Para esta iteración decidimos añadir manejo de permisos con usuarios. Para ello, agregamos varios usuarios estáticos con roles pre-definidos (Guest, User y Admin) los cuales poseen distintos permisos sobre distintos programas. Guest tiene permisos limitados mientras que Admin posee todos los permisos.

Los usuarios poseen permisos sobre Programas y sobre Recursos. Si se solicita la corrida de un Proceso que ejecuta un Programa que el usuario no posee permiso, ese Proceso se mata. Además, si tuviera permisos sobre un Programa que solicita un recurso al cuál el usuario no tiene permiso, al llegar a la línea de pedido se mata el Proceso.

Además, cabe mencionar que arreglamos un error conceptual que teníamos. El mismo era que cuando un Proceso pedía un Recurso, si el mismo no se encontraba disponible que pasára a Bloqueado, nosotros lo habiamos hecho que pasára a Listo. Esto fue arreglado.

<img src="imagenes_readme/iteracion3_imagen1_explicacion.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Además agregamos el color violeta a nuestro código de colores que representa el manejo de permisos.

<img src="imagenes_readme/iteracion3_imagen2_explicacion.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

#### PRUEBAS:

Los datos de prueba para esta iteración fueron estáticos nuevamente. A continuación se muestran:

<img src="imagenes_readme/iteracion3_imagen3_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/iteracion3_imagen4_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/iteracion3_imagen6_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/iteracion3_imagen5_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Cabe destacar que el Admin tiene permiso para todos los programas, mientras que el User tiene permiso para dos y el Guest solo a un programa. Ademas, tal como lo muestra la imagen anterior, los procesos 0 y 4 fueron corridos por un Usuario Admin, no deberia de haber problema con el Usuario 'Matixatim'. Sin embargo, los procesos 1, 2 y 3 son corridos por un Usuario Guest, 'GL' deberia de poder correr el proceso 2, pero no el 1 y 3, ya que no tiene permisos. Los resultados se muestran a continuación:

<img src="imagenes_readme/iteracion3_imagen7_pruebas.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Las pruebas corren de manera esperada, el Usuario 'Matixatim' corre todos los procesos sin problema. Sin embargo, el Usuario 'GL' no puede correr el proceso 1 y 3, pero si el proceso 2, tal como se esperaba. Nuevamente, una versión completa de las pruebas se encuentra en el Anexo.

### CUARTA ITERACIÓN

Antes de adentrarnos a la proxima iteracion, vale destacar que decidimos probar nuestro sistema contra un deadlock. El deadlock era muy simple:

- Proceso 0: 
     - Pide Impresora 1.
     - Corre otras cosas hasta un timeout.
     - Pide Impresora 2.
- Proceso 1: 
     - Pide Impresora 2.
     - Corre otras cosas hasta un timeout.
     - Pide Impresora 1.

Ejecutamos estos dos procesos y nos dio un deadlock como aparece en la imagen siguiente:

<img src="imagenes_readme/iteracion4_imagen1_pruebaDeadlock.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Esto no termina, como es de esperar de un deadlock:

<img src="imagenes_readme/iteracion4_imagen2_pruebaDeadlock.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Sin embargo, en esta iteracion no quisimos adentrarnos aún en los deadlocks. En vez, quisimos antes lidiar con el tema de la memoria.

Para el manejo de memoria, decidimos simular un sistema de Multiprogramación con Particiones Fijas. Como nuestra cantidad de programas es fija, decidimos hacer una particion por programa. Cada una de ellas posee todos los procesos resultantes de la ejecucion del programa asociado a la particion. Cada una de estas particiones poseen arrays representando el espacio en memoria asignado. Su largo es estatico e igual entre todas las particiones.

El funcionamiento implementado es el siguiente:
- Un trabajo es iniciado en ejecucion como un proceso nuevo.
- El proceso va a la partición asociada en memoria.
- Si hay lugar en memoria para el Proceso:
     - Se agrega a ese lugar y al Round Robin del Procesador.
- De lo contrario
     - Se agrega a una cola localizada en la Partición.

- Si un proceso es removido en memoria por fin de ejecucion (sea por falta de permisos o si realmente termino de hacer su trabajo) se toma un proceso nuevo de la cola y se asigna a la memoria.

Nosotros nos aseguramos de logear esto, así es notable en la simulacóon. Para ello, agregamos el naranja, asociado al manejo de memoria, a nuestro lenguaje de colores.

#### PRUEBAS:

Los datos de prueba de esta iteracion siguen siendo estaticos:

<img src="imagenes_readme/anexo_iteracion4_casosDePruebas1.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/anexo_iteracion4_casosDePruebas2.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

<img src="imagenes_readme/anexo_iteracion4_casosDePruebas3.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Notar que hay tres procesos cero, nuestras particiones tienen tamaño estático en memoria de 2 en esta iteración. Por lo tanto, para que la prueba funcione bien, deben de aparecer mensajes que indiquen el guardado del proceso 2 en la lista de espera. Otra cosa a señalar es que sabemos que los programas 0 y 1 estan seteados para realizar deadlocks, pero el programa 1 no corre ya que todos los procesos asociados a el son corridos por un usuario que no tiene permiso para correrlos.

<img src="imagenes_readme/anexo_iteracion4_pruebas1.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Los resultados de las pruebas son satisfactorios. Tal como debia suceder, el proceso 2 fue alojado en la cola de la particion y no fue añadido a memoria. Mas tarde, cuando se va el proceso 0, el proceso 2 se agrega a memoria y al scheduler:

<img src="imagenes_readme/anexo_iteracion4_pruebas3.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

Nuevamente, el caso completo se encuentra en el anexo.

### N Iteracion

### GUIA DE COLORES FINAL:
- Verde: Manejo de Procesos y Schedulling.
- Cyan: Cambio de estado de los Procesos.
- Blanco: Ejecucion de instrucciones.
- Blanco Fuerte (Bold): Manejo de Recursos.
- Amarillo: Advertencias de procesos no finalizados y recursos aun utilizados.
- Rojo: Errores que no deberían de ocurrir.
- Violeta: Manejo de Permisos de Usuarios.
- Naranja: Manejo de memoria.

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
