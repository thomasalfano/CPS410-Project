
/* Code by Matthew Kolb and Gabriel Buchanan
 * CPS 410
 * September 30th, 2024
 * Branch Test
 */

 import java.util.Random;
 import java.util.Scanner;
 import java.time.Duration;
 import java.time.Instant;
 
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
         int choice = scanner.nextInt();
         return choice;
     }
 
     // Method to generate a random math problem based on difficulty
     public static MathProblem generateProblem(int difficulty) {
         Random random = new Random();
         int num1 = random.nextInt(100) + 1;  // Random number between 1 and 100 for general problems
         int num2 = random.nextInt(100) + 1;
         int operation;
 
         // Adjust operation based on difficulty
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
             // For multiplication, limit numbers to 1 through 12 (standard times table)
             num1 = random.nextInt(12) + 1;  // Number between 1 and 12
             num2 = random.nextInt(12) + 1;  // Number between 1 and 12
             correctAnswer = num1 * num2;
         } else {
             // Ensure division results in whole numbers
             while (num1 % num2 != 0) {
                 num1 = random.nextInt(100) + 1;
                 num2 = random.nextInt(100) + 1;
             }
             correctAnswer = num1 / num2;
         }
 
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
 
     // Method to check the user's response time and give feedback
     public static void evaluateResponseTime(Duration duration) {
         long seconds = duration.getSeconds();
         if (seconds < 5) {
             System.out.println("Excellent! You answered in " + seconds + " seconds.");
         } else if (seconds < 10) {
             System.out.println("Great! You answered in " + seconds + " seconds.");
         } else if (seconds < 15) {
             System.out.println("Cool! You answered in " + seconds + " seconds.");
         } else if (seconds < 20) {
             System.out.println("Ok! You answered in " + seconds + " seconds.");
         } else {
             System.out.println("You took more than 20 seconds.");
         }
     }
 
     // Main method to simulate console interaction (can be replaced with GUI later)
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         String playAgain;
 
         do {
             // Ask the user to select the difficulty
             int difficulty = selectDifficulty(scanner);
 
             // Generate a new problem based on difficulty
             MathProblem problem = generateProblem(difficulty);
 
             // Display the problem and options
             System.out.println("Solve: " + problem.getProblemText());
             System.out.println("Option 1: " + problem.getOptions()[0]);
             System.out.println("Option 2: " + problem.getOptions()[1]);
             System.out.println("Option 3: " + problem.getOptions()[2]);
 
             // Start timing
             Instant startTime = Instant.now();
 
             // Get user's answer
             System.out.print("Your answer: ");
             int userAnswer = scanner.nextInt();
 
             // Stop timing
             Instant endTime = Instant.now();
             Duration timeElapsed = Duration.between(startTime, endTime);
 
             // Evaluate the response time
             evaluateResponseTime(timeElapsed);
 
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
 
     // Method to check the answer
     public static boolean checkAnswer(int userAnswer, MathProblem problem) {
         return userAnswer == problem.getCorrectAnswer();
     }
 }
 
