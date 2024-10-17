package dev._0.mindracers.math;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev._0.mindracers.math.MathPromptingProgram.MathProblem;

@RestController
public class ProblemController {
    
    // return a single instance of a MathProblem with a specified difficulty
    // default difficulty = 1
    @GetMapping("/getProblem")
     public MathProblem getProblem(@RequestParam(value = "difficulty", defaultValue = "1") int difficulty) {
         
        MathProblem mathProblem = MathPromptingProgram.generateProblem(difficulty);
        
        return mathProblem;
     }

     //TODO Implement GetMapping to return an Array of problems

 }