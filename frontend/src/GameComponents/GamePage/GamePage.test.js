import React from "react";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import GamePage from "./GamePage";

test("Display Four Options", () => {
  const options = ["1", "2", "3", "4"];

  render(<GamePage options={options} />);

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

  render(<GamePage options={options} />);

  // Test for the GameCanvas element
  const gameCanvas = screen.getByTestId("GameCanvas");
  expect(gameCanvas).toBeInTheDocument();
  expect(gameCanvas).toHaveAttribute("width", "800");
  expect(gameCanvas).toHaveAttribute("height", "600");
});
