package dev._0.mindracers.math;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev._0.mindracers.math.MathPromptingProgram.MathProblem;

@CrossOrigin
@RestController
@RequestMapping(path = "/game")
public class ProblemController {

  // return a List of MathProblem's with a specified difficulty
  // default difficulty = 1
  @GetMapping("/getProblems")
  public ArrayList<MathProblem> getProblems(@RequestParam(value = "difficulty", defaultValue = "1") int difficulty) {

    ArrayList<MathProblem> questionList = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      MathProblem mathProblem = MathPromptingProgram.generateProblem(difficulty);
      questionList.add(mathProblem);
    }

    return questionList;
  }

  // Post to receive the JSON list of MathProblems
  @PostMapping("/submit")
  public String receiveProblems(@RequestBody List<MathProblem> problems) {
    // Print test
    System.out.println("Received problems: " + problems);
    // Return success
    return "Received " + problems.size() + " problems.";
  }
}