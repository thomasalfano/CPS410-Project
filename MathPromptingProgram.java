
/* Code by Matthew Kolb and Gabriel Buchanan
 * CPS 410
 * September 29th, 2024
 * Test
 */

import java.util.Random;
import java.util.Scanner;
public class MathPromptingProgram {

    // Class to encapsulate a math problem
    public static class MathProblem {
        private int num1;
        private int num2;
        private int operation;  // 0 for addition, 1 for subtraction
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
            return (operation == 0) ? num1 + " + " + num2 : num1 + " - " + num2;
        }
    }

    // Method to generate a random math problem
    public static MathProblem generateProblem() {
        Random random = new Random();
        int num1 = random.nextInt(100) + 1;
        int num2 = random.nextInt(100) + 1;
        int operation = random.nextInt(2); // 0 for addition, 1 for subtraction
        int correctAnswer = (operation == 0) ? num1 + num2 : num1 - num2;

        // Generate two incorrect answers
        int wrongAnswer1 = correctAnswer + random.nextInt(10) + 1;
        int wrongAnswer2 = correctAnswer - (random.nextInt(10) + 1);

        // Ensure wrong answers are distinct and different from the correct answer
        while (wrongAnswer1 == correctAnswer || wrongAnswer1 == wrongAnswer2) {
            wrongAnswer1 = correctAnswer + random.nextInt(10) + 1;
        }
        while (wrongAnswer2 == correctAnswer || wrongAnswer2 == wrongAnswer1) {
            wrongAnswer2 = correctAnswer - (random.nextInt(10) + 1);
        }

        // Randomly shuffle the position of the correct answer in the choices
        int correctPosition = random.nextInt(3);
        int[] options = new int[3];
        options[correctPosition] = correctAnswer;
        options[(correctPosition + 1) % 3] = wrongAnswer1;
        options[(correctPosition + 2) % 3] = wrongAnswer2;

        // Return the generated problem
        return new MathProblem(num1, num2, operation, correctAnswer, options);
    }

    // Method to check the answer
    public static boolean checkAnswer(int userAnswer, MathProblem problem) {
        return userAnswer == problem.getCorrectAnswer();
    }

    // Main method to simulate console interaction (can be replaced with GUI later)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            // Generate a new problem
            MathProblem problem = generateProblem();

            // Display the problem and options
            System.out.println("Solve: " + problem.getProblemText());
            System.out.println("Option 1: " + problem.getOptions()[0]);
            System.out.println("Option 2: " + problem.getOptions()[1]);
            System.out.println("Option 3: " + problem.getOptions()[2]);

            // Get user's answer
            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();

            // Check if the answer is correct
            if (checkAnswer(userAnswer, problem)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong! The correct answer was: " + problem.getCorrectAnswer());
            }

            // Ask if the user wants to play again
            System.out.print("Would you like to solve another problem? (yes/no): ");
            playAgain = scanner.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing!");
        scanner.close();  // Scanner closed here after the loop
    }
}
