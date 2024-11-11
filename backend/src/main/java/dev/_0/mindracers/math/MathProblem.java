package dev._0.mindracers.math;

public class MathProblem {
    private int num1;
    private int num2;
    private int operation;
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

    // Getters and Setters
    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getOperation() {
        return operation;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int[] getOptions() {
        return options;
    }
}