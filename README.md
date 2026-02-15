# Control Remoto Mágico - Reto 7

Autores:
Cristian Gonzalez
Rafael Moreno

## Patrón de Diseño

**Categoría:** Patrón de Comportamiento (Behavioral Pattern)

## Patrón Utilizado

**Command Pattern (Patrón Comando)**

## Justificación

El patrón Command es ideal para este problema porque:

1. **Encapsulación de acciones:** Cada acción (encender luz, ajustar volumen, abrir puerta) se encapsula en un objeto comando independiente.

2. **Deshacer operaciones (Undo):** El patrón Command permite implementar fácilmente la funcionalidad de deshacer, ya que cada comando puede almacenar el estado anterior y revertirlo.

3. **Historial de comandos:** Permite mantener un registro completo de todas las acciones ejecutadas, incluyendo quién las ejecutó.

4. **Parametrización:** Los comandos pueden recibir parámetros específicos (volumen, posición de persiana) sin afectar la estructura general.

5. **Desacoplamiento:** Separa el objeto que invoca la operación (ControlRemotoMagico) del objeto que sabe cómo ejecutarla (comandos concretos).

## Cómo Lo Apliqué

### Estructura Implementada:

1. **Command (Interfaz):** Define los métodos `execute()` y `undo()` que todos los comandos deben implementar.

2. **Comandos Concretos:**
   - `EncenderLuzCommand`: Enciende/apaga la luz
   - `AbrirPuertaCommand`: Abre/cierra la puerta
   - `AjustarVolumenCommand`: Ajusta el volumen de la música (con parámetros)
   - `AjustarPersianaCommand`: Ajusta la posición de la persiana (con parámetros)

3. **Receptores (Receivers):**
   - `Luz`: Dispositivo que puede encenderse/apagarse
   - `Puerta`: Dispositivo que puede abrirse/cerrarse
   - `Musica`: Dispositivo con control de volumen
   - `Persiana`: Dispositivo con control de posición

4. **Invoker (ControlRemotoMagico):**
   - Ejecuta comandos y los registra en el historial
   - Permite deshacer acciones individuales
   - Muestra el historial completo con usuarios

5. **RegistroAccion:**
   - Almacena información de cada acción: usuario, comando y descripción
   - Permite rastrear quién desconfiguró cada aparato

### Flujo de Ejecución:

1. Se crean los dispositivos (receptores)
2. Se crean comandos específicos asociados a cada dispositivo
3. El control remoto ejecuta los comandos registrando el usuario
4. Se puede deshacer cualquier acción individual
5. Se muestra un resumen final con todas las acciones y sus responsables

### Cumplimiento de Requisitos:

Acciones con parámetros (volumen, posición de persiana)  
Registro de usuario que ejecutó cada acción  
Historial completo de acciones  
Capacidad de deshacer acciones individuales  
Resumen final con acciones y responsables  
Compatible con Maven (estructura de paquetes estándar)

## Ejecución

### Compilar el proyecto:
```bash
mvn clean compile
```

### Ejecutar el programa:
```bash
mvn exec:java
```

### Compilar y ejecutar en un solo comando:
```bash
mvn clean compile exec:java
```

## Ejemplo de Salida

### Entrada:
```
Numero de acciones a registrar: 4

Accion 1:
Usuario: Andres
Seleccione: 1. Encender luz 2. Abrir puerta 3. Reproducir musica 4. Ajustar volumen
4
Ingrese valor (0-100): 75
Deshacer accion? (si/no): si

Accion 2:
Usuario: Camila
Seleccione: 2
Deshacer accion? (si/no): no

Accion 3:
Usuario: Andres
Seleccione: 3
Deshacer accion? (si/no): si

Accion 4:
Usuario: Laura
Seleccione: 1
Deshacer accion? (si/no): no
```

### Salida:
```
Accion 1 ejecutada por Andres: Volumen ajustado a 75%
Accion 1 deshecha: Volumen regresado a 0%

Accion 2 ejecutada por Camila: Puerta abierta

Accion 3 ejecutada por Andres: Musica reproducida
Accion 3 deshecha: Musica detenida

Accion 4 ejecutada por Laura: Luz encendida

Historial completo
1: Ajustar volumen a 75% - Usuario: Andres (deshecha)
2: Abrir puerta - Usuario: Camila
3: Reproducir musica - Usuario: Andres (deshecha)
4: Encender luz - Usuario: Laura

--- Investigando quien desconfiguro los electrodomesticos
Se detecta que Andres realizo 2 acciones que alteraron la configuracion.
Camila realizo 1 accion.
Laura realizo 1 accion.
```


# RETO #8: El Zoológico de los UML

## Descripción
Sistema de gestión para un zoológico que permite administrar animales, cuidadores y visitantes, aplicando principios SOLID y patrones de diseño.

---

## Aplicación de Principios SOLID

### **S - Single Responsibility Principle (Principio de Responsabilidad Única)**

**Dónde se aplica:**
- **Clase `Animal`**: Solo se encarga de representar y gestionar la información básica de un animal.
- **Clase `Cuidador`**: Solo maneja las responsabilidades de cuidado de animales (alimentar, bañar, limpiar).
- **Clase `Visitante`**: Solo gestiona las interacciones de un visitante (registrar favoritos, tomar fotos, dar propinas).
- **Clase `Fotografia`**: Solo representa una fotografía con su metainformación.
- **Clase `Zoologico`**: Gestiona la administración general del zoológico.

**Justificación:**
Cada clase tiene una única razón para cambiar. Si necesitamos modificar cómo se alimentan los animales, solo modificamos la lógica en `Animal` o en las estrategias de dieta, no en `Cuidador` o `Visitante`.

---

### **O - Open/Closed Principle (Principio Abierto/Cerrado)**

**Dónde se aplica:**
- **Jerarquía de `Animal`**: La clase abstracta `Animal` está cerrada para modificación pero abierta para extensión. Podemos agregar nuevos tipos de animales (Anfibios, Peces) sin modificar la clase base.
- **Estrategias de Dieta**: Podemos agregar nuevas estrategias de alimentación (DietaPiscívora, DietaNecrófaga) sin modificar las existentes.
- **Atributos Dinámicos**: Usamos `Map<String, String>` para permitir agregar atributos como color de pelaje, origen, rareza sin modificar la clase `Animal`.

**Justificación:**
El diseño permite extender funcionalidad agregando nuevas clases sin tocar el código existente.

---

### **L - Liskov Substitution Principle (Principio de Sustitución de Liskov)**

**Dónde se aplica:**
- **Jerarquía de animales**: Cualquier instancia de `Mamifero`, `Reptil` o `Ave` puede sustituir a `Animal` sin romper el comportamiento esperado.
- **Polimorfismo en `hacerSonido()`**: Cada subclase implementa este método de manera específica, pero todas cumplen el contrato de la clase padre.


**Justificación:**
Las subclases mantienen el contrato de la superclase y pueden ser intercambiadas sin efectos secundarios.

---

### **I - Interface Segregation Principle (Principio de Segregación de Interfaces)**

**Dónde se aplica:**
- **Interfaces específicas**:
  - `IAlimentable`: Solo métodos relacionados con alimentación.
  - `ICuidable`: Solo métodos de cuidado (bañar, limpiar).
  - `IObservable`: Solo métodos de observación.

**Justificación:**
En lugar de tener una interfaz gigante `IAnimal` con todos los métodos, segregamos las responsabilidades en interfaces pequeñas. Esto permite que las clases implementen solo lo que necesitan.

Si en el futuro agregamos `Plantas` al zoológico, podrían implementar `ICuidable` pero no `IAlimentable` de la misma forma.

---

### **D - Dependency Inversion Principle (Principio de Inversión de Dependencias)**

**Dónde se aplica:**
- **Estrategia de Dieta**: La clase `Animal` no depende de implementaciones concretas de dietas, sino de la abstracción `EstrategiaDieta`. Esto permite inyectar diferentes comportamientos de alimentación.
- **Cuidador y Animal**: `Cuidador` trabaja con la abstracción `Animal`, no con implementaciones concretas como `Mamifero` o `Reptil`.


**Justificación:**
Los módulos de alto nivel (Animal, Cuidador) no dependen de módulos de bajo nivel, ambos dependen de abstracciones (interfaces).

---

## Patrone de Diseño Utilizado

### **Patrón Strategy (Comportamental)**

**Dónde se aplica:**
- En las estrategias de dieta: `EstrategiaDieta`, `DietaCarnivora`, `DietaHerbivora`, `DietaOmnivora`.

**Justificación:**
Permite cambiar el comportamiento de alimentación de un animal dinámicamente sin modificar su clase. Cada estrategia encapsula un algoritmo específico de procesamiento de alimentos.

---

### **Patrón Template Method (Comportamental) - Implícito**

**Dónde se aplica:**
- En la clase abstracta `Animal` con el método abstracto `hacerSonido()`.

**Justificación:**
Define el esqueleto de operaciones en la clase base, dejando que las subclases implementen pasos específicos.

---

### **Patrón Composite (Estructural) - Implícito**

**Dónde se aplica:**
- En la clase `Zoologico` que gestiona colecciones de `Animal`, `Cuidador` y `Visitante`.

**Justificación:**
Permite tratar objetos individuales y composiciones de objetos de manera uniforme.

---

## Características Adicionales del Diseño

### Encapsulamiento
- Todos los atributos son privados (`-` en UML).
- Se accede a ellos mediante getters y setters públicos (`+` en UML).
- Métodos internos de lógica pueden ser protegidos o privados.

### Polimorfismo
- **Herencia**: `Mamifero`, `Reptil` y `Ave` heredan de `Animal`.
- **Sobrescritura**: Cada subclase implementa `hacerSonido()` de forma específica.
- **Interfaces**: Todas las clases de animales implementan `IAlimentable`, `ICuidable`, `IObservable`.

### Inmutabilidad
- Las enumeraciones (`Dieta`, `EstadoSalud`, `Habitat`, `EspecialidadCuidador`) son inmutables.
- La clase `Fotografia` tiene atributos finales (fecha, animal) que no cambian después de la creación.

### Atributos Dinámicos
- `Map<String, String> atributosDinamicos` en `Animal` permite agregar:
  - Color de pelaje
  - Origen
  - Rareza
  - Historial médico
  - Cualquier otra característica sin modificar la clase.

---

## Asociaciones y Relaciones

### Composición (Diamante negro - fuerte)
- `Visitante` **compone** `Fotografia`: Las fotografías no existen sin el visitante que las tomó.

### Agregación (Diamante blanco - débil)
- `Cuidador` **agrega** `Animal`: Un cuidador tiene animales a cargo, pero los animales existen independientemente.
- `Visitante` **agrega** `Animal` (favoritos): Los visitantes tienen favoritos, pero los animales existen sin ellos.
- `Zoologico` **agrega** `Animal`, `Cuidador`, `Visitante`: El zoológico contiene estos elementos.

### Asociación simple
- `Fotografia` **asocia con** `Animal`: Una foto es de un animal específico.

---

## Ventajas del Diseño

1. **Extensible**: Fácil agregar nuevos tipos de animales, cuidadores especializados o comportamientos.
2. **Mantenible**: Cambios en una parte del sistema no afectan otras partes gracias a la separación de responsabilidades.
3. **Testeable**: Cada clase tiene responsabilidades claras que pueden probarse de forma aislada.
4. **Flexible**: Los patrones Strategy y Template permiten cambiar comportamientos dinámicamente.
5. **Escalable**: El uso de interfaces y abstracciones facilita agregar nuevas funcionalidades.
