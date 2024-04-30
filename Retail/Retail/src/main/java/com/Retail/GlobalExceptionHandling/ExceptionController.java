package com.Retail.GlobalExceptionHandling;

import com.Retail.Model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    /*@ExceptionHandler(value=FileuploadException.class)
    public ResponseEntity<Object> duplicateFile(FileuploadException fileuploadException){
        return new ResponseEntity<>("Image is already exist", HttpStatus.CONFLICT);
    }*/

    @ExceptionHandler(value=ProductException.class)
    public Object productexcep(){
        return new ResponseEntity<>("Price should not be more than 10,000",HttpStatus.OK);
    }
    @ExceptionHandler(value=ActiveProductException.class)
    public Object activeproductexcep(){
        return new ResponseEntity<>("No any Active product",HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value=ProductNotFoundException.class)
    public Object productNotFound(){
        return new ResponseEntity<>("Product does not exist",HttpStatus.NOT_FOUND);
    }
}
