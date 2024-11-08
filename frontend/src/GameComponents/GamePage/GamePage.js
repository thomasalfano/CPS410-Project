import GameCanvas from "../GameCanvas/GameCanvas";
import Option from "../Option/Option";
import { useState } from "react";

function GamePage({ question }) {
  const options = question.options;
  const correctAnswer = question.correctAnswer;
  const prompt = question.prompt;

  const [showOutput, setOutput] = useState("");

  const handleOptionClick = (value) => {
    if (value === correctAnswer) {
      setOutput("Correct!");
    } else {
      setOutput("Incorrect!");
    }
  };

  return (
    <>
      <h2 id="result-output" data-testid="result-output">
        {showOutput}
      </h2>

      <div>
        <GameCanvas />
      </div>

      <div>
        <h2 id="prompt-output" data-testid="prompt-output">
          {prompt}
        </h2>
        <div>
          {options.map((option, index) => (
            <Option
              key={index}
              text={option}
              onClick={() => handleOptionClick(option)}
            />
          ))}
        </div>
      </div>
    </>
  );
}

export default GamePage;
