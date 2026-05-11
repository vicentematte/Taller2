# Taller 2 - Simulacion Pokemon

## Descripcion del proyecto
Este taller consistio en crear un juego de simulacion de Pokemon por consola usando Java. El juego permite al jugador capturar Pokemon, armar su equipo, batallar contra lideres de gimnasio y finalmente desafiar al Alto Mando para convertirse en campeon. Toda la informacion del juego se carga desde archivos de texto y el progreso se puede guardar y cargar entre sesiones.

## Integrantes
| Nombre | RUT | GitHub |
|--------|-----|--------|
| Vicente Augusto Matte Barraza | 21.753.089-K | vicentematte |

## Como se abordo
El proyecto se desarrollo aplicando los conceptos de Programacion Orientada al Objeto (POO). Se crearon clases para representar cada elemento del juego, como los Pokemon, los Gimnasios, los habitats y el Alto Mando. La clase principal Main se encarga de toda la logica del juego, los menus y las interacciones con el usuario.

## Clases utilizadas
- Main.java - logica principal, menus y flujo del juego
- Pokedex.java - representa un Pokemon con todos sus stats (vida, ataque, defensa, etc)
- Gimnasio.java - representa un gimnasio con su lider y sus Pokemon
- altoMando.java - representa un miembro del Alto Mando y sus Pokemon
- Habitat.java - representa una zona donde se pueden capturar Pokemon
- TablaTipos.java - contiene la matriz de efectividad de tipos para los combates

## Instrucciones de ejecucion
1. Abrir el proyecto en Eclipse
2. Asegurarse de que los archivos .txt esten en la raiz del proyecto
3. Ejecutar Main.java
