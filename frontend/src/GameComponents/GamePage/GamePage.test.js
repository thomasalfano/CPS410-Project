import React from "react";
import { fireEvent, render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import GamePage from "./GamePage";

test("Display Four Options from Question", () => {
  const options = ["1", "2", "3", "4"];

  const question = {
    prompt: "1 + 3",
    options: options,
    correctAnswer: "4",
  };

  render(<GamePage question={question} />);

  // test that the correct number of buttons display
  const buttons = screen.getAllByRole("button");
  expect(buttons.length).toBe(4);

  // test the values
  expect(buttons[0]).toHaveTextContent("1");
  expect(buttons[1]).toHaveTextContent("2");
  expect(buttons[2]).toHaveTextContent("3");
  expect(buttons[3]).toHaveTextContent("4");
});

test("GamePage contains a GameCanvas with a canvas html element", () => {
  const options = ["1", "2", "3", "4"];

  const question = {
    prompt: "1 + 3",
    options: options,
    correctAnswer: "4",
  };

  render(<GamePage question={question} />);

  // Test for the GameCanvas element
  const gameCanvas = screen.getByTestId("GameCanvas");
  expect(gameCanvas).toBeInTheDocument();
  expect(gameCanvas).toHaveAttribute("width", "800");
  expect(gameCanvas).toHaveAttribute("height", "600");
});

test("GamePage Displays question prompt correctly", () => {
  const options = ["1", "2", "3", "4"];

  const question = {
    prompt: "1 + 3",
    options: options,
    correctAnswer: "4",
  };

  render(<GamePage question={question} />);

  // Test for prompt being displayed
  const promptHeading = screen.getByTestId("prompt-output");
  expect(promptHeading).toBeInTheDocument();
  expect(promptHeading).toHaveTextContent(question.prompt);
});

test("GamePage Correctness Heading Displays Properly when Correct Answer selected", () => {
  const options = ["1", "2", "3", "4"];

  const question = {
    prompt: "1 + 3",
    options: options,
    correctAnswer: "4",
  };

  render(<GamePage question={question} />);

  const buttons = screen.getAllByRole("button");

  // mock click event for the option with the correct asnwer
  fireEvent.click(buttons[3]);

  // Test for correctness output
  const resultHeading = screen.getByTestId("result-output");
  expect(resultHeading).toBeInTheDocument();
  expect(resultHeading).toHaveTextContent("Correct!");
});

test("GamePage Correctness Heading Displays Properly when Incorrect Answer selected", () => {
  const options = ["1", "2", "3", "4"];

  const question = {
    prompt: "1 + 3",
    options: options,
    correctAnswer: "4",
  };

  render(<GamePage question={question} />);

  const buttons = screen.getAllByRole("button");

  // mock click event for the option with the correct asnwer
  fireEvent.click(buttons[2]);

  // Test for correctness output
  const resultHeading = screen.getByTestId("result-output");
  expect(resultHeading).toBeInTheDocument();
  expect(resultHeading).toHaveTextContent("Incorrect!");
});
