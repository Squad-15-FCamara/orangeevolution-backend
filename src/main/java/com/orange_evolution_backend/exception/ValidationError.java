package com.orange_evolution_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {
    private final String field;
    private final String message;
    
}
