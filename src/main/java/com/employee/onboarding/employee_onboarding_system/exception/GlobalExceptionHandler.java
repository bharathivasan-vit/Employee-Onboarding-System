//package com.employee.onboarding.employee_onboarding_system.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    private static final String STATUS = "status";
//    private static final String MESSAGE = "message";
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Map<String,Object>> handleRuntimeException(IllegalArgumentException ex){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(STATUS,400,MESSAGE,ex.getMessage()));
//    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Map<String,Object>> handleRuntimeException(ResourceNotFoundException ex){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(STATUS,404,MESSAGE,ex.getMessage()));
//    }
//    @ExceptionHandler(Exception.class)
//    public  ResponseEntity<Map<String,Object >> handleException(Exception ex){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(STATUS,500,MESSAGE,"something went worng"));
//    }
//}
