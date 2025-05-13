package tc.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;

/**
 * Aspecto que intercepta todas las ejecuciones a métodos del paquete tc.aspects.domain
 * Imprime la firma del método antes de su ejecución y la línea de código fuente después de la ejecución
 */
@Aspect
public class DomainMethodInterceptor {

    /**
     * Pointcut que captura todas las ejecuciones de cualquier método en el paquete tc.aspects.domain
     */
    @Pointcut("execution(* tc.aspects.domain..*.*(..))")
    public void domainMethodExecution() {}
    
    /**
     * Advice que se ejecuta antes de cada método capturado por el pointcut
     * Imprime la firma completa del método
     * 
     * @param joinPoint el punto de unión que representa la ejecución del método
     */
    @Before("domainMethodExecution()")
    public void beforeMethodExecution(JoinPoint joinPoint) {
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
        String[] paramNames = signature.getParameterNames();
        
        for (int i = 0; i < paramTypes.length; i++) {
            if (i > 0) {
                fullSignature.append(", ");
            }
            fullSignature.append(paramTypes[i].getName());
            
            // Añadir nombre del parámetro si está disponible
            if (paramNames != null && paramNames.length > i) {
                fullSignature.append(" ")
                             .append(paramNames[i]);
            }
        }
        
        fullSignature.append(")");
        
        // Imprimir la firma completa
        System.out.println("Ejecutando método: " + fullSignature.toString());
    }
    
    /**
     * Advice que se ejecuta después de cada método capturado por el pointcut
     * Imprime la línea dentro del código fuente donde ocurrió la intercepción
     * 
     * @param joinPoint el punto de unión que representa la ejecución del método
     */
    @After("domainMethodExecution()")
    public void afterMethodExecution(JoinPoint joinPoint) {
        try {
            // Obtener la ubicación en el código fuente
            SourceLocation sourceLocation = joinPoint.getSourceLocation();
            
            // Imprimir información sobre la línea de código
            System.out.println("Interceptado en archivo: " + sourceLocation.getFileName() + 
                               ", línea: " + sourceLocation.getLine());
        } catch (Exception e) {
            System.err.println("No se pudo obtener la ubicación en el código fuente: " + e.getMessage());
        }
    }
}
