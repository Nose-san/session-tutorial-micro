package com.example.sessiontutorial.apinfra.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ValidationErrorResponse{

    private List<ValidationError> validationErrors;

}