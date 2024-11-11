package dev._0.mindracers;

import org.springframework.web.bind.annotation.*;

import dev._0.mindracers.math.MathProblem;

import java.util.List;

@RestController
@RequestMapping("/math")

public class MathController {

    // Post to receive the Json list
    @PostMapping("/submit")
    public String receiveProblems(@RequestBody List<MathProblem> problems) {
        // Print test
        System.out.println("Recieved problems: " + problems);

        // Return success
        return "Recieved " + problems.size() + " problems.";
    }
}
