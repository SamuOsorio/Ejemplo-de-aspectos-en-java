package tc.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import tc.aspects.util.Color;

/**
 * Aspecto que intercepta llamadas a métodos anotados con @Logging o en clases anotadas con @Logging
 * en el paquete tc.aspects.domain
 */
@Aspect
public class LoggingAspect {

	/**
	 * Pointcut para métodos anotados con @Logging
	 */
	@Pointcut("call(* tc.aspects.domain..*.*(..)) && @annotation(tc.aspects.annotations.Logging)")
	public void methodWithLoggingAnnotation() {}

	/**
	 * Pointcut para métodos en clases anotadas con @Logging
	 */
	@Pointcut("call(* tc.aspects.domain..*.*(..)) && @within(tc.aspects.annotations.Logging)")
	public void methodInClassWithLoggingAnnotation() {}

	/**
	 * Around advice que intercepta métodos que cumplen con alguna de las condiciones
	 * (método anotado con @Logging o clase anotada con @Logging)
	 *
	 * @param joinPoint el punto de unión que representa la llamada al método
	 * @return el resultado de la ejecución del método
	 * @throws Throwable si ocurre algún error durante la ejecución
	 */
	@Around("methodWithLoggingAnnotation() || methodInClassWithLoggingAnnotation()")
	public Object aroundLoggingMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		// Obtener la firma del método
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		// Construir y mostrar la firma completa del método
		StringBuilder fullSignature = new StringBuilder();

		// Tipo de retorno
		fullSignature.append(signature.getReturnType().getName())
			.append(" ");

		// Nombre completo del método (clase + método)
		fullSignature.append(signature.getDeclaringType().getName())
			.append(".")
			.append(signature.getName())
			.append("(");

		// Parámetros
		Class<?>[] paramTypes = signature.getParameterTypes();
		for (int i = 0; i < paramTypes.length; i++) {
			if (i > 0) {
				fullSignature.append(", ");
			}
			fullSignature.append(paramTypes[i].getName());
		}

		fullSignature.append(")");

		// Imprimir la firma completa resaltada
		System.out.println(Color.GREEN_BOLD + "[@Logging] Llamando al método: " +
			fullSignature.toString() + Color.RESET);

		// Proceder con la ejecución del método
		Object result = joinPoint.proceed();

		return result;
	}
}
