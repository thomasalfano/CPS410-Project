//Gabriel Buchanan 

//For CPS 410 

//Sept. 28th, 2024 

 

import java.util.Random; 

import java.util.Scanner; 

 

public class MathPromptingProgram { 

 

    // Method to generate random math problems 

    public static void generateProblem() { 

        Random random = new Random(); 

        Scanner scanner = new Scanner(System.in); 

 

        // Generate two random numbers between 1 and 100 

        int num1 = random.nextInt(100) + 1; 

        int num2 = random.nextInt(100) + 1; 

 

        // Randomly choose between addition (0) or subtraction (1) 

        int operation = random.nextInt(2); 

        int correctAnswer = 0; 

 

        // Store the problem in a string 

        String problem = ""; 

 

        if (operation == 0) { 

            // Addition problem 

            problem = num1 + " + " + num2; 

            correctAnswer = num1 + num2; 

        } else { 

            // Subtraction problem 

            problem = num1 + " - " + num2; 

            correctAnswer = num1 - num2; 

        } 

 

        // Display the problem to the user 

        System.out.println("Solve: " + problem); 

 

        // Generate two incorrect answers 

        int wrongAnswer1 = correctAnswer + random.nextInt(10) + 1; // Slightly off from the correct answer 

        int wrongAnswer2 = correctAnswer - (random.nextInt(10) + 1); // Slightly off in the other direction 

 

        // Ensure wrong answers are distinct and different from the correct answer 

        while (wrongAnswer1 == correctAnswer || wrongAnswer1 == wrongAnswer2) { 

            wrongAnswer1 = correctAnswer + random.nextInt(10) + 1; 

        } 

        while (wrongAnswer2 == correctAnswer || wrongAnswer2 == wrongAnswer1) { 

            wrongAnswer2 = correctAnswer - (random.nextInt(10) + 1); 

        } 

 

        // Randomly shuffle the position of the correct answer in the choices 

        int correctPosition = random.nextInt(3); // Randomly choose position (0, 1, or 2) 

        int[] options = new int[3]; 

 

        // Place the correct answer and wrong answers in the options array 

        options[correctPosition] = correctAnswer; 

        options[(correctPosition + 1) % 3] = wrongAnswer1; 

        options[(correctPosition + 2) % 3] = wrongAnswer2; 

 

        // Display the multiple-choice options 

        System.out.println("Choose the correct answer by typing the correct value:"); 

        System.out.println("Option 1: " + options[0]); 

        System.out.println("Option 2: " + options[1]); 

        System.out.println("Option 3: " + options[2]); 

 

        // Get user's answer (the actual value instead of 1-3) 

        System.out.print("Your answer: "); 

        int userAnswer = scanner.nextInt(); 

 

        // Check if the entered value matches the correct answer 

        if (userAnswer == correctAnswer) { 

            System.out.println("Correct!"); 

        } else { 

            System.out.println("Wrong! The correct answer was: " + correctAnswer); 

        } 

    } 

 

    public static void main(String[] args) { 

        Scanner scanner = new Scanner(System.in); 

        String playAgain; 

 

        // Keep generating problems until the user decides to stop 

        do { 

            generateProblem(); 

            System.out.print("Would you like to solve another problem? (yes/no): "); 

            playAgain = scanner.next(); 

        } while (playAgain.equalsIgnoreCase("yes")); 

 

        System.out.println("Thanks for playing!"); 

    } 

} 