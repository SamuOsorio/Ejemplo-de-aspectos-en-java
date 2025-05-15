package tc.aspects.domain;

import tc.aspects.annotations.Logging; // Añadido import para la anotación Logging

/**
 * Clase que gestiona una biblioteca con diferentes operaciones
 * como préstamos, devoluciones y búsquedas de libros.
 *
 * Se han añadido las anotaciones @Logging a dos métodos para la parte 2 del ejercicio
 */
public class GestorBiblioteca {

	// Atributos de la clase
	private String nombreBiblioteca;
	private int cantidadLibros;
	private boolean abierta;
	private double presupuestoAnual;

	/**
	 * Constructor de la clase GestorBiblioteca
	 * @param nombre Nombre de la biblioteca
	 * @param libros Cantidad inicial de libros
	 * @param presupuesto Presupuesto anual asignado
	 */
	public GestorBiblioteca(String nombre, int libros, double presupuesto) {
		this.nombreBiblioteca = nombre;
		this.cantidadLibros = libros;
		this.presupuestoAnual = presupuesto;
		this.abierta = false;
	}

	/**
	 * Método que no recibe parámetros y devuelve un valor booleano
	 * @return true si se pudo abrir la biblioteca, false en caso contrario
	 */
	@Logging  // Añadida anotación @Logging para la parte 2 del ejercicio
	public boolean abrirBiblioteca() {
		if (!abierta) {
			abierta = true;
			System.out.println("La biblioteca " + nombreBiblioteca + " ha sido abierta.");
			return true;
		} else {
			System.out.println("La biblioteca ya está abierta.");
			return false;
		}
	}

	/**
	 * Método que recibe un parámetro y no devuelve ningún valor
	 * @param cantidad La cantidad de libros a agregar al inventario
	 */
	public void agregarLibros(int cantidad) {
		if (cantidad > 0) {
			cantidadLibros += cantidad;
			System.out.println("Se han agregado " + cantidad + " libros. Total actual: " + cantidadLibros);
		} else {
			System.out.println("Error: La cantidad debe ser mayor que cero.");
		}
	}

	/**
	 * Método que recibe múltiples parámetros de diferentes tipos y devuelve un String
	 * @param titulo Título del libro
	 * @param disponible Si el libro está disponible
	 * @param diasPrestamo Días de préstamo permitidos
	 * @return Información sobre el préstamo del libro
	 */
	@Logging  // Añadida anotación @Logging para la parte 2 del ejercicio
	public String prestarLibro(String titulo, boolean disponible, int diasPrestamo) {
		if (abierta) {
			if (disponible && cantidadLibros > 0) {
				cantidadLibros--;
				return "Libro '" + titulo + "' prestado por " + diasPrestamo + " días.";
			} else {
				return "El libro no está disponible para préstamo.";
			}
		} else {
			return "La biblioteca está cerrada. No se pueden realizar préstamos.";
		}
	}

	/**
	 * Método que recibe un array como parámetro y devuelve un valor numérico
	 * @param librosDevueltos Array con los IDs de los libros devueltos
	 * @return La cantidad de libros procesados correctamente
	 */
	public int procesarDevoluciones(int[] librosDevueltos) {
		if (librosDevueltos == null || librosDevueltos.length == 0) {
			return 0;
		}

		int procesados = 0;
		for (int idLibro : librosDevueltos) {
			if (idLibro > 0) {
				cantidadLibros++;
				procesados++;
				System.out.println("Libro ID: " + idLibro + " devuelto correctamente.");
			}
		}

		return procesados;
	}

	/**
	 * Método que recibe parámetros variables y devuelve un valor decimal
	 * @param porcentajes Porcentajes a aplicar al presupuesto para diferentes áreas
	 * @return El presupuesto restante después de aplicar los porcentajes
	 */
	public double calcularDistribucionPresupuesto(double... porcentajes) {
		if (porcentajes == null || porcentajes.length == 0) {
			return presupuestoAnual;
		}

		double presupuestoRestante = presupuestoAnual;
		for (int i = 0; i < porcentajes.length; i++) {
			double monto = presupuestoAnual * porcentajes[i];
			presupuestoRestante -= monto;
			System.out.println("Área " + (i+1) + ": $" + monto);
		}

		return presupuestoRestante > 0 ? presupuestoRestante : 0;
	}

	/**
	 * Método estático que recibe objetos y devuelve un resultado
	 * @param biblioteca1 Primera biblioteca para comparar
	 * @param biblioteca2 Segunda biblioteca para comparar
	 * @return La biblioteca con mayor cantidad de libros
	 */
	public static GestorBiblioteca bibliotecaMasGrande(GestorBiblioteca biblioteca1, GestorBiblioteca biblioteca2) {
		if (biblioteca1 == null) {
			return biblioteca2;
		}
		if (biblioteca2 == null) {
			return biblioteca1;
		}

		return (biblioteca1.cantidadLibros > biblioteca2.cantidadLibros) ? biblioteca1 : biblioteca2;
	}

	/**
	 * Método para obtener información completa de la biblioteca
	 * @return Datos de la biblioteca en formato String
	 */
	@Override
	public String toString() {
		return "Biblioteca: " + nombreBiblioteca +
			"\nCantidad de libros: " + cantidadLibros +
			"\nEstado: " + (abierta ? "Abierta" : "Cerrada") +
			"\nPresupuesto anual: $" + presupuestoAnual;
	}
}
