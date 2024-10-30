package dev._0.mindracers.math;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.google.gson.Gson;

public class MathPromptingProgram {

    // Class to encapsulate a math problem
    public static class MathProblem {
        private int num1;
        private int num2;
        private int operation;  // 0 for addition, 1 for subtraction, 2 for multiplication, 3 for division
        private int correctAnswer;
        private int[] options;

        // Constructor
        public MathProblem(int num1, int num2, int operation, int correctAnswer, int[] options) {
            this.num1 = num1;
            this.num2 = num2;
            this.operation = operation;
            this.correctAnswer = correctAnswer;
            this.options = options;
        }

        // Getters for the encapsulated fields
        public int getNum1() { return num1; }
        public int getNum2() { return num2; }
        public int getOperation() { return operation; }
        public int getCorrectAnswer() { return correctAnswer; }
        public int[] getOptions() { return options; }

        // String representation of the problem
        public String getProblemText() {
            switch (operation) {
                case 0: return num1 + " + " + num2;
                case 1: return num1 + " - " + num2;
                case 2: return num1 + " * " + num2;
                case 3: return num1 + " / " + num2;
                default: return "";
            }
        }
    }

    // Method for Difficulty Selection
    public static int selectDifficulty(Scanner scanner) {
        System.out.println("Select difficulty:");
        System.out.println("1. Easy (Addition)");
        System.out.println("2. Medium (Addition and Subtraction)");
        System.out.println("3. Hard (Addition, Subtraction, Multiplication, and Division)");
        System.out.print("Your choice: ");
        return scanner.nextInt();
    }

    // Method to generate a random math problem based on difficulty
    public static MathProblem generateProblem(int difficulty) {
        Random random = new Random();
        int num1 = random.nextInt(100) + 1;
        int num2 = random.nextInt(100) + 1;
        int operation;

        if (difficulty == 1) {
            operation = 0;  // Only addition
        } else if (difficulty == 2) {
            operation = random.nextInt(2);  // Addition or subtraction
        } else {
            operation = random.nextInt(4);  // Addition, subtraction, multiplication, or division
        }

        int correctAnswer;
        if (operation == 0) {
            correctAnswer = num1 + num2;
        } else if (operation == 1) {
            correctAnswer = num1 - num2;
        } else if (operation == 2) {
            num1 = random.nextInt(12) + 1;
            num2 = random.nextInt(12) + 1;
            correctAnswer = num1 * num2;
        } else {
            while (num1 % num2 != 0) {
                num1 = random.nextInt(100) + 1;
                num2 = random.nextInt(100) + 1;
            }
            correctAnswer = num1 / num2;
        }

        int wrongAnswer1 = correctAnswer + random.nextInt(10) + 1;
        int wrongAnswer2 = correctAnswer - (random.nextInt(10) + 1);

        while (wrongAnswer1 == correctAnswer || wrongAnswer1 == wrongAnswer2) {
            wrongAnswer1 = correctAnswer + random.nextInt(10) + 1;
        }
        while (wrongAnswer2 == correctAnswer || wrongAnswer2 == wrongAnswer1) {
            wrongAnswer2 = correctAnswer - (random.nextInt(10) + 1);
        }

        int correctPosition = random.nextInt(3);
        int[] options = new int[3];
        options[correctPosition] = correctAnswer;
        options[(correctPosition + 1) % 3] = wrongAnswer1;
        options[(correctPosition + 2) % 3] = wrongAnswer2;

        return new MathProblem(num1, num2, operation, correctAnswer, options);
    }

    // Method to calculate points based on response time
    public static int calculatePoints(Duration duration) {
        long seconds = duration.getSeconds();
        if (seconds < 5) return 10;
        else if (seconds < 10) return 7;
        else if (seconds < 15) return 5;
        else if (seconds < 20) return 3;
        else return 0;
    }

    // Method to ask a question and time the response
    public static int askTimedQuestion(MathProblem problem, Scanner scanner) {
        System.out.println("Solve: " + problem.getProblemText());
        System.out.println("Options: ");
        int[] options = problem.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your answer: ");

        Instant start = Instant.now();
        int userAnswer = scanner.nextInt();
        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        int points = calculatePoints(duration);

        System.out.println("Time taken: " + duration.getSeconds() + " seconds.");
        System.out.println("Points earned: " + points);
        if (checkAnswer(userAnswer, problem)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer was " + problem.getCorrectAnswer());
        }
        return points;
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int difficulty = selectDifficulty(scanner);

        List<MathProblem> problems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MathProblem problem = generateProblem(difficulty);
            problems.add(problem);
        }

        Gson gson = new Gson();
        String json = gson.toJson(problems);
        System.out.println("Generated Problems:");
        System.out.println(json);

        int totalScore = 0;
        for (MathProblem problem : problems) {
            totalScore += askTimedQuestion(problem, scanner);
        }
        System.out.println("Total Score: " + totalScore);

        scanner.close();
    }

    // Method to check the answer
    public static boolean checkAnswer(int userAnswer, MathProblem problem) {
        return userAnswer == problem.getCorrectAnswer();
    }
}
