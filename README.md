## Integrantes:
* Juan Diego Lora -
* Jeison Lasprilla


## DESCRIPCIÓN GENERAL:
Desarrollar una solución de software que permita simular un videojuego de tuberías, donde un usuario pueda editar las casillas de un tablero de 8x8, colocando o quitando tuberías entre la fuente y el drenaje, siguiendo ciertas reglas, si el agua llega de la fuente al drenaje, este gana. Así mismo se debe permitir al usuario que vea su puntaje obtenido.

## Requerimientos

1. Desarrollar una solución de software que permita simular un tablero 8x8 donde las casillas sean editables por el usuario para la instalación de tuberías a excepción de la fuente y el drenaje ubicados de manera aleatoria.

* 1. Al iniciar la partida se debe pedir por consola un nickname al usuario.

* 2. Existen tres tipos de tuberías. La tubería vertical, horizontal y circular; representadas por el símbolo de igual (=), doble pipe (||) y la letra o (o) respectivamente. Se debe permitir instalar dichas tuberías escribiendo la posición de la casilla a editar, primero la fila y luego la columna y finalmente el símbolo de la tubería a instalar.

2.	La solución debe simular el flujo del agua al finalizar el sistema de tuberías. Si la solución es correcta, el agua puede desplazarse por las tuberías desde la fuente hasta el drenaje; el sistema de tuberías es incorrecto cuando se incumplen las restricciones entre conexiones o el agua no logra desplazarse desde la fuente al drenaje. Para finalizar, se debe informar sobre el estado de la solución, si es correcta el juego finaliza, si no, la partida continúa.

* 1. Restricciones de las tuberías:
<br>La tubería circular no se puede conectar a la fuente, al drenaje ni consigo misma.

3.	El programa debe de almacenar y mostrar el puntaje del usuario para cada partida finalizada. Este se obtendrá mediante una fórmula donde están como variables: El número de tuberías empleadas y el tiempo transcurrido de inicio a fin de la partida

* 1. Cálculo del puntaje:
puntaje = tuberiasUsadas * 100 - (60 - tiempoEnSegundos) * 10

* 2. Se debe de guardar el puntaje por partida finalizada, enlazando al usuario que la jugó

* 3. Se deben eliminar los puntajes una vez se cierre el programa



