package tc.aspects;
import tc.aspects.domain.GestorBiblioteca;

 // IMPORTANTE: Recuerde que este código no se ejecutará correctamente si lo hace directamente desde la IDE.
 // En lugar de ello, debe compilar y ejecutar el código usando el comando indicado en el archivo README.md

/**
 * 1. (50%) Construya un aspecto que intercepte todas las ejecuciones (execution) a cualquier método de las clases que se encuentren dentro del paquete `tc.aspects.domain`.
 * - Antes de cada ejecución (execution), imprimir por consola la firma completa del método
 * - Después de cada ejecución (execution), imprimir la línea dentro del código fuente donde ocurrió la intercepción
 * 
 * Para probar dicho aspecto:
 * - Cree una clase, con al menos 5 métodos, en la carpeta `domain`
 * - Cada método debe tener diferentes tipos y cantidad de parámetros y también diferentes tipos de retorno
 * - Instancie dicha clase llame a todos sus métodos desde `Main.java`
 * 
 * 2. (50%) Construya un aspecto que intercepte, usando la anotación `@Around`, todas las llamadas (call) a cualquier método de las clases que se encuentren dentro del paquete `tc.aspects.domain` que cumpla al menos una de estas dos condiciones:
 * - El método esté anotado con `@Logging` (usar la anotación definida dentro de este mismo proyecto, en la carpeta `annotations`)
 * - La clase esté anotada con `@Logging` (usar la anotación definida dentro de este mismo proyecto, en la carpeta `annotations`)
 *
 * Imprima la firma del método interceptado antes de que sea llamado
 * 
 * Para probar dicho aspecto:
 * - Modifique la clase creada para el ejercicio anterior. Coloque la anotación `@Logging` en solo dos de los métodos de dicha clase. No anote la clase con `@Logging`
 * - Cree otra clase con 3 métodos, cada uno con diferentes parámetros y tipos de retorno. Anote con `@Logging` la clase y uno de sus métodos. Instancie dicha clase llame a todos sus métodos desde `Main.java`
 */

public class Main {
	public static void main(String[] args) {
		// Primera parte: Probando la clase GestorBiblioteca (sin anotaciones)
		System.out.println("\n=== PROBANDO PRIMERA PARTE (Interceptor de ejecuciones) ===\n");
		GestorBiblioteca gestor = new GestorBiblioteca("puj", 100, 30000000);
		gestor.abrirBiblioteca();
		gestor.agregarLibros(10);
		gestor.prestarLibro("El libro troll", true, 10);
		gestor.procesarDevoluciones(new int[]{1,2,3,4,5,6});
		gestor.calcularDistribucionPresupuesto(0.5);

		// Segunda parte: Probando CatalogoLibros (anotada a nivel de clase con @Logging)
		System.out.println("\n=== PROBANDO SEGUNDA PARTE (Interceptor con @Logging) ===\n");
		CatalogoLibros catalogo = new CatalogoLibros();
		catalogo.buscarPorAutor("Gabriel García Márquez");
		catalogo.agregarCategoria("Ciencia Ficción", 15);
		catalogo.obtenerEstadisticas();
	}
}
