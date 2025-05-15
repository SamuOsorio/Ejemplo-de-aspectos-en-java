package tc.aspects;

import tc.aspects.annotations.Logging;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar el catálogo de libros
 * Esta clase está anotada con @Logging, por lo que todos sus métodos serán interceptados
 */
@Logging
public class CatalogoLibros {

	private Map<String, List<String>> librosPorAutor;
	private Map<String, Integer> librosPorCategoria;

	/**
	 * Constructor de la clase CatalogoLibros
	 */
	public CatalogoLibros() {
		this.librosPorAutor = new HashMap<>();
		this.librosPorCategoria = new HashMap<>();

		// Inicializar con algunos datos de ejemplo
		List<String> librosGarciaMárquez = new ArrayList<>();
		librosGarciaMárquez.add("Cien años de soledad");
		librosGarciaMárquez.add("El amor en los tiempos del cólera");
		librosGarciaMárquez.add("Crónica de una muerte anunciada");

		librosPorAutor.put("Gabriel García Márquez", librosGarciaMárquez);

		librosPorCategoria.put("Literatura Latinoamericana", 10);
		librosPorCategoria.put("Novela", 25);
		librosPorCategoria.put("Ensayo", 5);
	}

	/**
	 * Método que busca libros por autor
	 * @param autor El nombre del autor a buscar
	 * @return Lista de libros del autor
	 */
	public List<String> buscarPorAutor(String autor) {
		List<String> libros = librosPorAutor.get(autor);
		if (libros != null) {
			System.out.println("Encontrados " + libros.size() + " libros del autor " + autor);
			return new ArrayList<>(libros);
		} else {
			System.out.println("No se encontraron libros del autor " + autor);
			return new ArrayList<>();
		}
	}

	/**
	 * Método que agrega una categoría con cierta cantidad de libros
	 * Este método está anotado con @Logging (aunque la clase ya tiene la anotación)
	 * @param categoria Nombre de la categoría
	 * @param cantidad Cantidad de libros en la categoría
	 * @return true si la categoría se agregó correctamente
	 */
	@Logging
	public boolean agregarCategoria(String categoria, int cantidad) {
		if (categoria == null || categoria.isEmpty() || cantidad < 0) {
			System.out.println("Error: datos de categoría inválidos");
			return false;
		}

		librosPorCategoria.put(categoria, cantidad);
		System.out.println("Categoría " + categoria + " agregada con " + cantidad + " libros");
		return true;
	}

	/**
	 * Método que obtiene estadísticas del catálogo
	 * @return Un mapa con las estadísticas
	 */
	public Map<String, Integer> obtenerEstadisticas() {
		Map<String, Integer> estadisticas = new HashMap<>();

		int totalAutores = librosPorAutor.size();
		int totalCategorias = librosPorCategoria.size();

		int totalLibros = 0;
		for (Integer cantidad : librosPorCategoria.values()) {
			totalLibros += cantidad;
		}

		estadisticas.put("totalAutores", totalAutores);
		estadisticas.put("totalCategorias", totalCategorias);
		estadisticas.put("totalLibros", totalLibros);

		System.out.println("Estadísticas del catálogo:");
		System.out.println("- Total de autores: " + totalAutores);
		System.out.println("- Total de categorías: " + totalCategorias);
		System.out.println("- Total de libros: " + totalLibros);

		return estadisticas;
	}
}
