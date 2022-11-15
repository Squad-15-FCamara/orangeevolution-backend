// Este Objeto é utilizado para aproveitar do JPA com as requisições direto no banco sem precisar criar os 
// comandos em SQL, deixando que o próprio JPA faça cada requisição para os Tipos.

package com.orange_evolution_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orange_evolution_backend.entity.Type;

public interface TypeRepository extends JpaRepository<Type,Long> {
    public Type findByName(String name);
}
