import "./GamePage.css";
import GameCanvas from "../GameCanvas/GameCanvas";
import Option from "../Option/Option";
import { useState, useEffect } from "react";
import "./GamePage.css";

function GamePage() {
  const [showOutput, setOutput] = useState("");
  const [questions, setQuestions] = useState([]);
  const [activeQuestionIndex, setActiveQuestionIndex] = useState(0);
  const [handleCorrect, setHandleCorrect] = useState(null);

  // use for setting win/loss state
  const [hasWon, setHasWon] = useState(false);

  useEffect(() => {
    fetch("http://localhost:8080/game/getProblems", {
      method: "GET",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        setQuestions(data); // Adjust this if data is not an array
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  const nextQuestion = () => {
    if (activeQuestionIndex < questions.length - 1) {
      setActiveQuestionIndex(activeQuestionIndex + 1);
    }

    if (activeQuestionIndex === questions.length - 1) {
      setHasWon(true);
    }
  };

  // variables used to display problem and choices
  const activeQuestion = questions[activeQuestionIndex];
  const prompt = activeQuestion?.problemText || "Loading...";
  const options = activeQuestion?.options || [];

  // Log questions when they update
  useEffect(() => {
    console.log(questions);
  }, [questions]);

  const handleOptionClick = (value) => {
    if (value === activeQuestion.correctAnswer) {
      setOutput("Correct!");
      handleCorrect(activeQuestionIndex);
      nextQuestion();
    } else {
      setOutput("Incorrect, try again!");
    }
  };

  return (
    <>
      <h2 id="result-output" data-testid="result-output">
        {showOutput}
      </h2>

      <div>
        <GameCanvas correctAnswerTrigger={setHandleCorrect} />
      </div>

      <div>
        <h2 id="prompt-output" data-testid="prompt-output">
          {prompt}
        </h2>
        <div id="option-container">
          {options.map((option, index) => (
            <Option
              key={index}
              text={option}
              onClick={() => handleOptionClick(option)}
            />
          ))}
        </div>
      </div>
      {hasWon && (
        <div id="overlay">
          <div id="result-popup">
            <h2>You Won!</h2>
            <p>Your Score: </p>
            <p>Your total time: </p>

            <button onClick={() => window.location.reload()}>Play Again</button>
          </div>
        </div>
      )}
    </>
  );
}

export default GamePage;
