package com.orange_evolution_backend.exception;


import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class ValidationException {


    public void ValidationExceptionList(List<?> returnCourse){
        if (returnCourse.isEmpty()){
            throw new CourseNotFoundException("Course not Found");
        }
    }

    public void ValidationExceptionList(List<?> returnCourse, String msg){
        if (returnCourse.isEmpty()){
            throw new CourseNotFoundException("Course not Found by " + msg);
        }
    }

}
