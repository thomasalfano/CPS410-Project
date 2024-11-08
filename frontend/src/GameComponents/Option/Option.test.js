import React from "react";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import Option from "./Option";

test("Answer Text", () => {
  render(<Option text="Test Text" />);
  expect(screen.getByRole("button")).toHaveTextContent(/Test Text/i);
});
