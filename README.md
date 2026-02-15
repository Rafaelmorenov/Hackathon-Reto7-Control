# Control Remoto Mágico - Reto 7

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
