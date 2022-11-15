// Objeto que cria a exceção para quando não encontrar o Conteudo

package com.orange_evolution_backend.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message){
        super(message);
    }
    
}
