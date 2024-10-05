package dev._0.mindracers.example;

/**
 * In Spring’s approach to building RESTful web services, 
 * HTTP requests are handled by a controller. 
 * These components are identified by the @RestController annotation, 
 * and the exapmleController shown in the following listing 
 * 
 * exampleController.java handles GET requests for /example by returning a 
 * new instance of the example class:
 */

 import java.util.concurrent.atomic.AtomicLong;

 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;
 
 @RestController
 public class ExampleController {
 
     private static final String template = "Hello, %s!";
     private final AtomicLong counter = new AtomicLong();
 
     @GetMapping("/example")
     public Example example(@RequestParam(value = "name", defaultValue = "World") String name) {
         return new Example(counter.incrementAndGet(), String.format(template, name));
     }
 }