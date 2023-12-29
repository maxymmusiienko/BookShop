package com.example.bookshop.annotations;

import io.swagger.v3.oas.annotations.Parameter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(
        name = "id",
        description = "This is id of a category",
        example = "14",
        required = true
)
public @interface CategoryIdParameter {
}
