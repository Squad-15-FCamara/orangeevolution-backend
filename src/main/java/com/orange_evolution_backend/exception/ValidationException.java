//Objeto que confere se a lista esta vazia para disparar uma exception

package com.orange_evolution_backend.exception;


import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class ValidationException {


    // Dispara a exception com uma mensagem padrao
    public void ValidationExceptionList(List<?> returnCourse){
        if (returnCourse.isEmpty()){
            throw new CourseNotFoundException("Course not Found");
        }
    }

    // Dispara a exception com uma mensagem informando qual não foi encontrada
    public void ValidationExceptionList(List<?> returnCourse, String msg){
        if (returnCourse.isEmpty()){
            throw new CourseNotFoundException("Course not Found by " + msg);
        }
    }
}
