package com.example.multiplace.web;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalNotFoundExceptionHandler {
//
//@GetMapping("/notfound/{id}")
//public String notFound(@PathVariable("id") String id){
//    throw new ObjectNotFoundException( "aasdas" + id + "dasda");
//}
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleNotFound(ObjectNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("/error/notfound");
        modelAndView.addObject("id", exception);
        return modelAndView;
    }
}
