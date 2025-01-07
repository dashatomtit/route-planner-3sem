package ru.mirea.tomtit.routeplannerbackend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static <T> ResponseEntity<T> createResponseEntity(T entity, HttpStatus status) {
        return ResponseEntity
                .status(status)
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "*")
//                .header("Access-Control-Allow-Headers", "*")
//                .header("Access-Control-Max-Age", "*")
                .body(entity);
    }

}
