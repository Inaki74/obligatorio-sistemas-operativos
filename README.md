# obligatorio-sistemas-operativos
Obligatorio sistemas operativos, Gaston Landeira, Matias Gonzalez e Iñaki Etchegaray.

## CLASES

Proceso ejecuta programa

Programa = lista de Instrucciones = texto => programa lista de texto

Sistema -> Singleton  <— procesador
Proceso -> Hay que identificar todo lo que tienen los procesos.
Recurso <— Serialmente Reutilizable y No Reutilizable
Procesador extends Recurso
Usuario(permiso) 		En una version mas adelantada, pueden tener contraseña y tienen que ser creados, por ahora ya van a estar cargados.

Sistema -> proceso -> usuario

Sistema:
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
