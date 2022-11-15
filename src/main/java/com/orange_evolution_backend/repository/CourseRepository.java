// Este Objeto é utilizado para aproveitar do JPA com as requisições direto no banco sem precisar criar os 
// comandos em SQL, deixando que o próprio JPA faça cada requisição para os Conteúdos.

package com.orange_evolution_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orange_evolution_backend.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public List<Course> findByauthor(String author);
    

}
