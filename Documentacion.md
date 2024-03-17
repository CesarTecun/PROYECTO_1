# Proyecto_1
## Integrantes ##

**Cesar Alberto Tecun Leiva 7690-22-11766**

**Victor Isaias Quino Quino 7690-17-6433**

**Pablo Antonio Ispache Arriaga 7690-17-940**

**Carlos Emanuel Del Cid Corado 7690-22-19541**

### Funcionalidades del codigo
**Ingresar expresion matematica:** El usuario puede ingresar una expresion matematica

**Construir arbol de expresiones:** Ingresado la expresion, se construye el arbol de exresiones

**Imprimir árbol de expresión:** Muestra el árbol de expresión en la consola.

- **Imprimir árbol de expresión gráfico:** Muestra una representación gráfica del árbol de expresión en la consola.
- **Recorrido PreOrden:** Muestra el recorrido en preorden del árbol de expresión.
- **Recorrido InOrden:** Muestra el recorrido inorden del árbol de expresión.
- **Recorrido PosOrden:** Muestra el recorrido en posorden del árbol de expresión.
- **Modificar valores de variables:** Permite modificar los valores de las variables en la expresión.
- **Evaluar expresión:** Evalúa la expresión matemática y muestra su resultado.
## Funciones principales

- `main`: Función principal del programa donde se maneja la lógica principal de interacción con el usuario y se realiza la construcción y manipulación del árbol de expresiones.
- `modificarVariables`: Permite al usuario modificar los valores de las variables en la expresión.
- `esExpresionValida`: Verifica si una expresión matemática ingresada es válida.
### Métodos

- `construirArbol`: Construye el árbol de expresiones a partir de una expresión matemática.
- `imprimirArbol`: Imprime el árbol de expresión.
- `imprimirArbolGrafico`: Imprime una representación gráfica del árbol de expresión.
- `recorridoPreOrden`: Realiza el recorrido en preorden del árbol de expresión.
- `recorridoInOrden`: Realiza el recorrido inorden del árbol de expresión.
- `recorridoPosOrden`: Realiza el recorrido en posorden del árbol de expresión.
- `evaluarExpresion`: Evalúa la expresión matemática representada por el árbol y devuelve su resultado.
## Función `esExpresionValida`

### Descripción
Verifica si una expresión matemática es válida.

### Parámetros
- `expresion`: Expresión matemática a verificar.

### Devuelve
- `boolean`: `true` si la expresión es válida, `false` en caso contrario.

## Función `modificarVariables`

### Descripción
Permite al usuario modificar los valores de las variables en la expresión.

### Parámetros
- `arbol`: Árbol de expresiones al que se aplicarán los cambios.
- `scanner`: Objeto Scanner para leer la entrada del usuario.